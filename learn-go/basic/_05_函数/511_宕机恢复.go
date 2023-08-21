package _05_函数

import (
	"fmt"
	"runtime"
)

// 崩溃时需要传递的上下文信息
type panicContext struct {
	function string
}

func ProtectedRun(entry func()) {
	// 注意使用 defer ，在退出前捕获异常
	defer func() {
		err := recover()
		switch err.(type) {
		case runtime.Error:
			fmt.Println("runtime error:", err)
		default:
			fmt.Println("error:", err)
		}
	}()

	entry()
}

// go run src/_05_函数/511_宕机恢复.go
func main() {
	fmt.Println("运行前")
	ProtectedRun(func() {
		fmt.Println("手动宕机前")
		panic(&panicContext{
			"手动触发 panic",
		})
		fmt.Println("手动宕机后")
	})

	ProtectedRun(func() {
		fmt.Println("赋值宕机前")
		var a *int
		*a = 1
		fmt.Println("赋值宕机后")
	})
	fmt.Println("运行后")
}
