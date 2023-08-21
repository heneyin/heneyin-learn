package memmodels

// 使用管道通信来同步 goroutine

var c = make(chan int, 10)
var str string

func f1() {
	str = "hello world"
	// close(c)   // 1
	c <- 0 // 2
}

func helloChan() {
	go f1()
	<-c
	println(str)
}
