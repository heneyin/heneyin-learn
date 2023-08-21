package main

import "fmt"

// https://tour.go-zh.org/moretypes/6

func main() {
	// 数组长度与类型一同定义，
	// 所以数组长度不能变化。
	var a [10]string
	a[0] = "hello"
	a[1] = "world"

	fmt.Println(a[0], a[1])
	fmt.Println(a)

	primes := [6]int {1,3,4,5,6,7}
	fmt.Println(primes)
}