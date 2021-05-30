package main

// 不知道什么玩意，用法好奇怪

import (
	"fmt"
)

// 一个接口
type Invoker interface {
	// interface{} 在用法上相当于 java 的 Object。
	// 可以接受任何类型的变量
	Call(interface{})
}

/* 使用结构体实现接口 */
type SimpleStruct struct {
}

// 实现接口
// 方法定义与接口方法定义相同
func (ss *SimpleStruct) Call(p interface{}) {
	fmt.Println("from struct", p)
}

/* 使用函数实现接口 */
// 函数定义为类型
type FuncCaller func(interface{})

// 使用函数实现接口
func (fc FuncCaller) Call(p interface{}) {
	fc(p)
}

func main() {
	/* 使用结构体实现接口 */
	var invoker Invoker

	ss := new(SimpleStruct)

	invoker = ss

	invoker.Call("hello")

	/* 使用函数实现接口 */
	var invoker1 Invoker
	invoker1 = FuncCaller(func(v interface{}) {
		fmt.Println("from function", v)
	})
	invoker1.Call("hello")
}
