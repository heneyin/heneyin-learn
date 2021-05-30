package main

import "fmt"

// https://tour.go-zh.org/basics/6

// 函数可以返回任意数量的返回值。
func swap(x, y string) (string , string) {
	return y, x
}

func main() {
	a, b := swap("world", "hello")
	fmt.Println(a, b)
}