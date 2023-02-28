package _08_goroutine

import (
	"errors"
	"sync"
	"testing"
	"time"
)

/**
一个 goroutine 超时等待退出框架。
使 goroutine 在程序退出时得到退出的通知与调用，有机会完成最后的清理工作。
*/

// GracefullyShutdowner
// 实现了该接口的类型均可在程序退出时得到退出的通知和调用，从而有机会做退出前的最后清理工作
type GracefullyShutdowner interface {
	Shutdown(waitTimeout time.Duration) error
}

// chapter6/sources/go-concurrency-pattern-7.go
type ShutdownerFunc func(time.Duration) error

func (f ShutdownerFunc) Shutdown(waitTimeout time.Duration) error {
	return f(waitTimeout)
}

// ConcurrentShutdown
// 并发退出
func ConcurrentShutdown(waitTimeout time.Duration, shutdowners ...GracefullyShutdowner) error {
	c := make(chan struct{})

	go func() {
		var wg sync.WaitGroup
		for _, g := range shutdowners {
			wg.Add(1)
			go func(shutdowner GracefullyShutdowner) {
				defer wg.Done()
				shutdowner.Shutdown(waitTimeout)
			}(g)
		}
		wg.Wait()
		c <- struct{}{}
	}()

	timer := time.NewTimer(waitTimeout)
	defer timer.Stop()

	select {
	case <-c:
		return nil
	case <-timer.C:
		return errors.New("wait timeout")
	}
}

// shutdownMaker 测试用函数
func shutdownMaker(processTm int) func(time.Duration) error {
	return func(time.Duration) error {
		time.Sleep(time.Second * time.Duration(processTm))
		return nil
	}
}

func TestConcurrentShutdown(t *testing.T) {
	f1 := shutdownMaker(2)
	f2 := shutdownMaker(6)

	err := ConcurrentShutdown(10*time.Second, ShutdownerFunc(f1), ShutdownerFunc(f2))
	if err != nil {
		t.Errorf("want nil, actual: %s", err)
		return
	}

	err = ConcurrentShutdown(4*time.Second, ShutdownerFunc(f1), ShutdownerFunc(f2))
	if err == nil {
		t.Error("want timeout, actual nil")
		return
	}
}

// 串行退出
// 超时时间是所有goroutine的退出时间之和
func SequentialShutdown(waitTimeout time.Duration, shutdowners ...GracefullyShutdowner) error {
	start := time.Now()
	var left time.Duration
	timer := time.NewTimer(waitTimeout)

	for _, g := range shutdowners {
		elapsed := time.Since(start)
		left = waitTimeout - elapsed

		c := make(chan struct{})
		go func(shutdowner GracefullyShutdowner) {
			shutdowner.Shutdown(left)
			c <- struct{}{}
		}(g)

		timer.Reset(left)
		select {
		case <-c:
			// 继续执行
		case <-timer.C:
			return errors.New("wait timeout")
		}
	}
	return nil
}
