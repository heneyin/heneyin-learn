package _08_goroutine

import (
	"fmt"
	"sync"
	"testing"
	"time"
)

/**
管道机制就是将前面程序的输出数据作为输入数据传递给后面的程序
*/
// chapter6/sources/go-concurrency-pattern-8.go
func newNumGenerator(start, count int) <-chan int {
	c := make(chan int)
	go func() {
		for i := start; i < start+count; i++ {
			c <- i
		}
		close(c)
	}()
	return c
}

func filterOdd(in int) (int, bool) {
	if in%2 != 0 {
		return 0, false
	}
	return in, true
}

func square(in int) (int, bool) {
	return in * in, true
}

func spawnPipeline(f func(int) (int, bool), in <-chan int) <-chan int {
	out := make(chan int)

	go func() {
		for v := range in {
			r, ok := f(v)
			if ok {
				out <- r
			}
		}
		close(out)
	}()

	return out
}

func TestPipeline(t *testing.T) {
	in := newNumGenerator(1, 20)
	out := spawnPipeline(square, spawnPipeline(filterOdd, in))

	for v := range out {
		println(v)
	}
}

/**
扩展模式：
- 扇出模式
- 扇入模式
*/

func spawnPipelineGroup(name string, num int, f func(int) (int, bool), in <-chan int) <-chan int {
	groupOut := make(chan int)
	var outSlice []chan int
	for i := 0; i < num; i++ {
		out := make(chan int)
		go func(i int) {
			name := fmt.Sprintf("%s-%d:", name, i)
			fmt.Printf("%s begin to work...\n", name)

			for v := range in {
				r, ok := f(v)
				if ok {
					out <- r
				}
			}
			close(out)
			fmt.Printf("%s work done\n", name)
		}(i)
		outSlice = append(outSlice, out)
	}

	// 扇入模式
	//
	// out --\
	//        \
	// out ---- --> groupOut
	//        /
	// out --/
	//
	go func() {
		var wg sync.WaitGroup
		for _, out := range outSlice {
			wg.Add(1)
			go func(out <-chan int) {
				for v := range out {
					groupOut <- v
				}
				wg.Done()
			}(out)
		}
		wg.Wait()
		close(groupOut)
	}()

	return groupOut
}

func TestPipelineGroup(t *testing.T) {
	in := newNumGenerator(1, 20)
	out := spawnPipelineGroup("square", 2, square, spawnPipelineGroup("filterOdd", 3, filterOdd, in))

	time.Sleep(3 * time.Second) //为了输出更直观的结果，这里等上面的goroutine都就绪

	for v := range out {
		fmt.Println(v)
	}
}
