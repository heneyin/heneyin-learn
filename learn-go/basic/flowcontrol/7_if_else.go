package main

import (
	"fmt"
	"math"
)

func pow1(x, n, lim float64) float64 {
	if v := math.Pow(x, n); v < lim {
		return v
	} 
	fmt.Printf("%g >= %g\n", v, lim)
	// 这里开始就不能使用 v 了
	return lim
}

func main() {
	fmt.Println(
		pow1(3, 2, 10),
		pow1(3, 3, 20),
	)
}

