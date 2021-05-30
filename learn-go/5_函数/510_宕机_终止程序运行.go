package main

import "fmt"

func main()  {
	/* 手动触发宕机 */
	// panic("crash")

	// 宕机前触发延时执行语句
	defer fmt.Println("1")
	defer fmt.Println("2")
	panic("aaa")
}