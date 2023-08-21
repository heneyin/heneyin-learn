package main

import "fmt"

// 函数作为值

func compute(fn func(float64, float64 ) float64) float64 {
	return fn(3, 4);
}
func main() {

	add := func(a float64, b float64) float64 {
		return a + b
	}

	result := compute(add)
	fmt.Println(result)
}