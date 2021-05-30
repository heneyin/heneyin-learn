package main

import "fmt"

// 向切片中追加元素

func main() {

	var s []int

	s = append(s, 0)

	printSlice2(s)

	s = append(s, 1)
	printSlice2(s)

	s = append(s, 1,2,3,4,5)
	printSlice2(s)
}

func printSlice2(s []int) {
	fmt.Printf("len=%d cap=%d %v\n", len(s), cap(s), s)
}