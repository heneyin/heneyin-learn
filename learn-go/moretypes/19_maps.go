package main

import "fmt"

// 声明 map
// var m map[string]string

func main() {
	// make map
	var m = make(map[string]string)

	// 填值
	m["hello"] = "world"
	fmt.Println(m["hello"])
	fmt.Println(m["not hello"])

	// 若顶级类型只是一个类型名，你可以在文法的元素中省略它。
	var m1 = map[string]string {
		"hello": "world",
		"你好": "世界",
	}
	fmt.Println(m1)

	// 修改元素
	m["hello"] = "www"
	fmt.Println(m["hello"])
	// 删除元素
	delete(m, "hello")
	// 使用双赋值查看元素是否存在
	value, ok := m["hello"]
	fmt.Println("The value:", value, "Present?", ok)
}
