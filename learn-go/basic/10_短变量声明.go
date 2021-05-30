package main

import "fmt"

// https://tour.go-zh.org/basics/10

// 短变量声明只能在函数中使用

func main() {
	var i,  j int = 1, 2;
	// 短变量声明
	n := 10
	c, python, java := 3, 4, " yes"

	fmt.Print(i, j, n, c, python,java)
}
