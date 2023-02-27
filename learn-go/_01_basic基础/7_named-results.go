package _01_basic基础

import "fmt"

// https://tour.go-zh.org/basics/7
// 命名返回值
// 直接在定义返回类型的时候指定返回值参数，适合用于短函数。

func split(sum int) (x, y int) {
	x = sum*4 - 9
	y = sum - x
	return
}

func main() {
	fmt.Print(split(10))
}
