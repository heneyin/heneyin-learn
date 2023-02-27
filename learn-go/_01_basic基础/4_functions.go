package _01_basic基础

// https://tour.go-zh.org/basics/4
// https://tour.go-zh.org/basics/5

import (
	"fmt"
)

// 函数的定义
func add(x int, y int) int {
	return x + y
}

// 函数的定义，省略参数列表的类型。
// 如果参数列表的类型相同，除了最后一个参数，其他的参数的类型声明都可以省略。
func add1(x, y int) int {
	return x + y
}

func main() {
	fmt.Println(add(1, 2))
	fmt.Println(add1(1, 2))
}
