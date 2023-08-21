package main

import "fmt"

// 函数的闭包
// 闭包指的是一个作为值的函数，使用了其函数体之外的一个变量.
// 该函数可以访问并赋予其引用的变量的值，换句话说，该函数被这些变量“绑定”在一起。

// 这是一个闭包
func adder() func(int) int {
	sum := 0
	return func(i int) int {
		sum += i
		return sum
	}
}

func main() {
	pos, neg := adder(), adder()
	// 两个闭包都绑定到了各自的 sum 上。
	for i := 0; i < 10; i++ {
		fmt.Println(
			pos(i),
			neg(-i),
		)
	}
}