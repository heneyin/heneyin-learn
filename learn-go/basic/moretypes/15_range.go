package main

import "fmt"

// 使用range遍历切片

var pow = []int{1, 2, 4, 8, 16, 32, 64, 128}

func main() {

	for index, value := range pow {
		fmt.Printf("i %d, v %d\n", index, value)
	}

	// 也可以用 下划线 省略不需要的 index 或者 value
	for _, value := range pow  {
		fmt.Printf("v %d\n", value)
	}

	for index, _ := range pow {
		fmt.Println(index)
	}
	// 值需要索引，也可以只声明索引
	for index := range pow {
		fmt.Println(index)
	}

}