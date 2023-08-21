package main

import "fmt"


func main() {
	sum := 0
	for i := 0; i < 100; i ++ {
		sum ++
	}
	fmt.Print(sum)
}

// 初始化语句与后置语句是可选的。
func main1() {
	sum := 0
	for ; sum < 100;{
		sum ++
	}
	fmt.Print(sum)
}

// for 就是 while 了
func main2() {
	sum := 0
	for sum < 100 {
		sum ++
	}
	fmt.Print(sum)
}

func main3()  {
	for {
		fmt.Println()
	}
}