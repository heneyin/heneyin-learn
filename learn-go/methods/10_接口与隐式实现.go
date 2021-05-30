package main

import "fmt"

// 通知实现接口的的所有方法来完成对接口的实现，而无需显示声明实现了哪一个接口。

type I interface {
	M()
}

type II interface {
	M()
}

type M1 float64

func (m M1) M() {
	fmt.Println("1231")
}

func main() {
	// var m1 M1 = M1{123}
}


