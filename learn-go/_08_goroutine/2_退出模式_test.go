package _08_goroutine

import (
	"errors"
	"fmt"
	"sync"
	"testing"
	"time"
)

/**
goroutine 的退出。
一般无需考虑 goroutine 的退出控制：
1. goroutine 的执行函数返回，意味着 goroutine 退出了。
2. 常驻的后台服务程序，可能有对 goroutine 优雅退出的需求。
下面四几种退出模式：
*/

/**
分离模式：detached
创建它的 goroutine 不需要关心他的退出，这类 goroutine 退出后与其创建者彻底分离。
*/

/**
一次性任务
goroutine 执行一个简单任务，执行后即退出。
*/
func TestExitOnce(t *testing.T) {
	go fmt.Println("hello")
}

/**
1. 常驻性任务，常驻执行的任务。
*/

/**
2. join 模式
*/

// 2.1 一个 goroutine 等待另一个 goroutine 退出。
// 如果输入存在 int 数据，则等待对应描述。
// 否则直接退出
func worker(args ...interface{}) {
	if len(args) == 0 {
		return
	}
	interval, ok := args[0].(int)
	if !ok {
		return
	}
	time.Sleep(time.Second * (time.Duration(interval)))
}

func spawn(f func(args ...interface{}), args ...interface{}) chan struct{} {
	c := make(chan struct{})
	go func() {
		// 执行函数
		f(args...)
		// 发送退出标志
		c <- struct{}{}
	}()
	return c
}

func TestJoinMode(t *testing.T) {
	done := spawn(worker, 3)
	println("spawn a worker goroutine")
	// 等待 struct{}{} 终止标志
	<-done
	println("worker done")
}

// 2.2 获取 goroutine 的退出状态
// 如果需要精准的获取 goroutine 的结束状态、
// 可以使用自定义类型的 channel 类实现精准结束状态。
var OK = errors.New("ok")

func worker2(args ...interface{}) error {
	if len(args) == 0 {
		return errors.New("invalid args")
	}
	interval, ok := args[0].(int)
	if !ok {
		return errors.New("invalid interval arg")
	}
	time.Sleep(time.Second * (time.Duration(interval)))
	return OK
}

func spawn2(f func(args ...interface{}) error, args ...interface{}) chan error {
	c := make(chan error)
	go func() {
		// 处理，并发送结束标志
		c <- f(args...)
	}()
	return c
}

func TestGoroutineStatus(t *testing.T) {
	// 这里获取自定义的结束标识。
	done := spawn2(worker2, 5)
	println("spawn worker2_1")
	err := <-done
	fmt.Println("worker2_1 done:", err)
	done = spawn2(worker2)
	println("spawn worker2_2")
	err = <-done
	fmt.Println("worker2_2 done", err)
}

/**
2.3 等待多个 goroutine 退出
当有多个 goroutine，并需要等待全部 goroutine 的退出。
可以使用 go 语言提供的  sync.WaitGroup 实现多个 goroutine 的退出模式。
*/
func spawnGroup(n int, f func(args ...interface{}), args ...interface{}) chan struct{} {
	c := make(chan struct{})
	var wg sync.WaitGroup

	for i := 0; i < n; i++ {
		wg.Add(1)
		go func(i int) {
			name := fmt.Sprintf("worker-%d", i)
			f(args...)
			println(name, "done")
			// -1
			wg.Done()
		}(i)
	}
	go func() {
		// 等待全部完成，发送停止标志。
		wg.Wait()
		c <- struct{}{}
	}()

	return c
}

func TestWaitGoroutineGroup(t *testing.T) {
	done := spawnGroup(5, worker, 3)
	println("spawn a group of workers")
	<-done
	println("group workers done")
}

/**
2.4 支持超时机制的等待
当 goroutine 在指定时间内没有退出，创建者会继续向下执行或主动退出。
*/
func TestTimeoutGoroutine(t *testing.T) {
	done := spawnGroup(5, worker, 10)
	println("spawn a group of workers")

	timer := time.NewTimer(time.Second * 3)
	defer timer.Stop()
	// 用于监听多个 channel
	select {
	// timer.C 是一个 channel，当 timer 到达预定时间，会发送标志。
	case <-timer.C:
		println("wait group workers exit timeout!")
	case <-done:
		println("group workers done")
	}
	// 主 goroutine 退出后，spawnGroup 就退出中断了
}

/**
# 3. notify and wait 模式
创建者主动通知 goroutine 退出，等待 goroutine 退出后，创建者再退出。
减少 goroutine 因粗暴退出导致的损失。
*/

/**
3.1 通知并等待一个 goroutine 退出
*/
func workerSleep(j int) {
	time.Sleep(time.Second * time.Duration(j))
}

func spawnNotifyAnfWaitOne(f func(int)) chan string {
	quit := make(chan string)
	go func() {
		var job chan int // 模拟 job channel，这里一直是空 channel
		for {
			select {
			case j := <-job:
				f(j)
			case <-quit:
				quit <- "ok"
			}
		}
	}()
	return quit
}

func TestNotifyAndWaitOne(t *testing.T) {
	quit := spawnNotifyAnfWaitOne(workerSleep)
	println("spawn a worker goroutine")

	time.Sleep(5 * time.Second)

	println("notify the worker to exit...")
	quit <- "exit"

	// 如果超时，就直接退出
	timer := time.NewTimer(time.Second * 10)
	defer timer.Stop()

	select {
	case status := <-quit:
		println("worker done:", status)
	case <-timer.C:
		println("wait worker exit timeout")
	}
}

/**
3.1 通知并等待多个 goroutine 退出
*/
func spawnNotifyAnfWaitGroup(n int, f func(int)) chan struct{} {
	quit := make(chan struct{})
	job := make(chan int)

	var wg sync.WaitGroup

	for i := 0; i < n; i++ {
		wg.Add(1)
		go func(i int) {
			defer wg.Done() // 保证 wg.Done 在 goroutine 退出前被执行
			name := fmt.Sprintf("worker-%d", i)
			for {
				// 等待关闭
				j, ok := <-job
				if !ok {
					println(name, "done")
					return
				}
				workerSleep(j)
			}
		}(i)
	}

	go func() {
		<-quit
		close(job)         // 告诉所有 goroutine 该关闭了。
		wg.Wait()          // 等待关闭完成
		quit <- struct{}{} // 通知创建者关闭完成
	}()
	return quit
}

func TestNotifyAndWaitGroup(t *testing.T) {
	quit := spawnNotifyAnfWaitGroup(5, workerSleep)
	println("spawn a group of workers")

	time.Sleep(5 * time.Second)
	// 通知 worker goroutine 组退出
	println("notify the worker group to exit...")
	quit <- struct{}{}

	timer := time.NewTimer(time.Second * 5)
	defer timer.Stop()
	select {
	case <-timer.C:
		println("wait group workers exit timeout!")
	case <-quit:
		println("group workers done")
	}
}
