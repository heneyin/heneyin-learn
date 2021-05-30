package main

import "fmt"

func main() {

	// 声明数组
	var a [3]int
	// 赋值数组
	a[0] = 1
	a[1] = 4
	a[2] = 10

	fmt.Println(a)

	// 声明时初始化
	var team = [...]string{"hah", "enen", "dede"}

	// 遍历数组
	for index, value := range team {
		fmt.Println(index, value)
	}
}
