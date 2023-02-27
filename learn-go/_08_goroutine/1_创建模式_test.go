package _08_goroutine

import (
	"fmt"
	"testing"
)

/*
 使用 go 关键字+函数/方法创建 goroutine
*/
func TestCreateGoroutine(t *testing.T) {
	go fmt.Println("I am goroutine")
}

/*
 使用 channel 建立 goroutine 之间的联系
 下面是使用 channel 建立 自定义 goroutine 与 main goroutine 之间的联系。
*/
func TestCreateGoroutineUseChannel(t *testing.T) {
	c := make(chan string)
	go func() {
		c <- "hello"
		c <- "world"
	}()
}
