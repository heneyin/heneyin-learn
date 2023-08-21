package main

import (
	"fmt"
	"strings"
)

func main() {
	// 第一个参数是 length
	// 第二个参数是 caption
	a := make([]int, 5)
	printSlice1("a", a)

	b := make([]int, 0, 5)
	printSlice1("b", b)

	// length 不能大于 caption
	// c := make([]int, 5, 0)
	// printSlice1("c", c)

	// 切片可以包含任何类型，当然也可以是切片

	board := [][]string {
		[]string{"_", "_", "_"},
		[]string{"_", "_", "_"},
		[]string{"_", "_", "_"},
	}

	// 两个玩家轮流打上 X 和 O
	board[0][0] = "X"
	board[2][2] = "O"
	board[1][2] = "X"
	board[1][0] = "O"
	board[0][2] = "X"

	for i := 0; i < len(board); i++ {
		fmt.Printf("%s\n", strings.Join(board[i], " "))
	}
}

func printSlice1(s string, x []int) {
	fmt.Printf("%s len=%d cap=%d %v\n",
		s, len(x), cap(x), x)
}
