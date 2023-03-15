package _09_channel

import (
	"fmt"
	"sync"
	"testing"
	"time"
)

/**
用作信号传递
*/

// 一对一通知信号
type signal struct{}

func worker() {
	println("worker is working...")
	time.Sleep(1 * time.Second)
}

func spawn(f func()) <-chan signal {
	c := make(chan signal)
	go func() {
		println("worker start to work...")
		f()
		c <- signal(struct{}{})
	}()
	return c
}

func TestOneToOne(t *testing.T) {
	println("start a worker...")
	c := spawn(worker)
	<-c
	fmt.Println("worker work done!")
}

// 一对多，通过 close(channel) 完成通知
// chapter6/sources/go-channel-case-3.go

func workerWithId(i int) {
	fmt.Printf("worker %d: is working...\n", i)
	time.Sleep(1 * time.Second)
	fmt.Printf("worker %d: works done\n", i)
}

func spawnGroup(f func(i int), num int, groupSignal <-chan signal) <-chan signal {
	c := make(chan signal)
	var wg sync.WaitGroup

	for i := 0; i < num; i++ {
		wg.Add(1)
		go func(i int) {
			<-groupSignal
			fmt.Printf("worker %d: start to work...\n", i)
			f(i)
			wg.Done()
		}(i + 1)
	}

	go func() {
		wg.Wait()
		c <- signal{}
	}()
	return c
}

func TestOneToSome(t *testing.T) {
	fmt.Println("start a group of workers...")
	groupSignal := make(chan signal)
	c := spawnGroup(workerWithId, 5, groupSignal)
	time.Sleep(5 * time.Second)
	fmt.Println("the group of workers start to work...")
	close(groupSignal)
	<-c
	fmt.Println("the group of workers work done!")
}

//  通知一组 worker goroutine 的退出
// chapter6/sources/go-channel-case-4.go

func workerWithSignal(i int, quit <-chan signal) {
	fmt.Printf("worker %d: is working...\n", i)
LOOP:
	for {
		select {
		default:
			// 模拟worker工作
			time.Sleep(1 * time.Second)
		case <-quit:
			break LOOP
		}
	}
	fmt.Printf("worker %d: works done\n", i)
}

func spawnGroupWithSignal(f func(int, <-chan signal), num int, groupSignal <-chan signal) <-chan signal {
	c := make(chan signal)
	var wg sync.WaitGroup

	for i := 0; i < num; i++ {
		wg.Add(1)
		go func(i int) {
			fmt.Printf("worker %d: start to work...\n", i)
			f(i, groupSignal)
			wg.Done()
		}(i + 1)
	}

	go func() {
		wg.Wait()
		c <- signal{}
	}()
	return c
}

func TestOneToStopSome(t *testing.T) {
	fmt.Println("start a group of workers...")
	groupSignal := make(chan signal)
	c := spawnGroupWithSignal(workerWithSignal, 5, groupSignal)
	fmt.Println("the group of workers start to work...")

	time.Sleep(5 * time.Second)

	// 通知workers退出
	fmt.Println("notify the group of workers to exit...")
	close(groupSignal)
	<-c
	fmt.Println("the group of workers work done!")
}

/**
替代锁机制，利用channel同步阻塞机制。
*/
// chapter6/sources/go-channel-case-6.go
type counter struct {
	c chan int
	i int
}

var cter counter

func InitCounter() {
	cter = counter{
		c: make(chan int),
	}

	go func() {
		for {
			cter.i++
			cter.c <- cter.i
		}
	}()
	fmt.Println("counter init ok")
}

func Increase() int {
	return <-cter.c
}

func init() {
	InitCounter()
}

func TestAsLock(t *testing.T) {
	for i := 0; i < 10; i++ {
		go func(i int) {
			v := Increase()
			fmt.Printf("goroutine-%d: current counter value is %d\n", i, v)
		}(i)
	}
	time.Sleep(5 * time.Second)
}
