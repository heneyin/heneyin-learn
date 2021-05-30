package main

import "fmt"

// 2.5.3 展现 go 语言变量逃逸分析
// 运行命令 go run  -gcflags "-m -l" 变量逃逸分析.go
func dummy(b int) int {
	var c int
	c = b
	return c
}


func void() {

}

func main() {
	var a int
	void()
	fmt.Println(a, dummy(0))

}
