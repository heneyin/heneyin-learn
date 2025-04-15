package _05_function

import (
	"fmt"
	"testing"
)

/* 用闭包实现一个累加器 */
// 闭包的记忆效应
// 这里会记住 value
func Accumulate(value int) func() int {
	return func() int {
		value++
		return value
	}
}

/* 使用闭包实现生成器 */
// 这里会记住 name
func PlayerGen(name string) func() (string, int) {
	hp := 150
	return func() (string, int) {
		return name, hp
	}
}

func TestFunctionClosure(t *testing.T) {
	/* 在闭包内部修改引用的变量 */
	str := "hello world"

	foo := func() {
		str = "hello 123"
	}

	foo()
	fmt.Println(str)

	/* 闭包累加器 */
	acc := Accumulate(1)
	fmt.Println(acc())
	fmt.Println(acc())

	acc1 := Accumulate(10)
	fmt.Println(acc1())

	gen := PlayerGen("aa")
	name, hp := gen()
	fmt.Println(name, hp)
}
