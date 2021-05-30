package main

import (
	"fmt"
	"math"
)

func pow(x, n, lim float64) float64 {
	// 可以在表达式之前执行一条简单语句
	// 且声明的变量作用域在 if 内。
	if v := math.Pow(x, n); v < lim {
		return v
	}
	return lim
}

func main() {
	fmt.Println(
		pow(3, 2, 10),
		pow(3, 3, 20),
	)
}
