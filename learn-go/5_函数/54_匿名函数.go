package main

import (
	"flag"
	"fmt"
)

/* 匿名函数用作回调函数 */
func visit(data []string, f func(string)) {
	for _, value := range data {
		f(value)
	}
}

func main() {
	/* 匿名函数 */
	/* 定义时调用匿名函数 */
	func(data int) {
		fmt.Println("hello", data)
	}(100)

	/* 将匿名函数赋值给变量 */
	f := func(data int) {
		fmt.Println("hello", data)
	}
	f(200)

	visit([]string{"a", "b", "c"}, func(v string) {
		fmt.Println(v)
	})

	/* 一个例子，把 func 作为 map 的值 */
	flag.Parse()
	var skill = map[string]func(){
		"fire": func() {
			fmt.Println("skill fire")
		},
		"run": func() {
			fmt.Println("run")
		},
		"fly": func() {
			fmt.Println("fly")
		},
	}

	if f, ok := skill["run"]; ok {
		f()
	} else {
		fmt.Println("Not found ")
	}

}
