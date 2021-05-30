package main

import "fmt"

// 接口定义
type DataWriter interface {
	WriteData(data interface{}) error
}

// 实现 DataWriter 接口
type file struct {
}
// 函数声明要与接口中相同
// 要实现接口中所有的方法。
func (d *file) WriteData(data interface{}) error {
	fmt.Println("Write Data:", data)
	return nil
}

func main() {
	f := new(file)

	var writer DataWriter
	writer = f

	writer.WriteData("data")
}