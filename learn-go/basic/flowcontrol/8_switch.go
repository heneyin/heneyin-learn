package main

// https://tour.go-zh.org/flowcontrol/9

import (
	"fmt"
	"runtime"
)

// 自动 break
// switch 的 case 语句从上到下顺次执行，直到匹配成功时停止。
func main() {
	fmt.Print("Go runs on ")
	switch os := runtime.GOOS; os {
	case "darwin":
		fmt.Println("OS X.")
	case "linux":
		fmt.Println("Linux.")
	default:
		// freebsd, openbsd,
		// plan9, windows...
		fmt.Printf("%s.\n", os)
	}
}
