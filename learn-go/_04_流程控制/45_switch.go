package _04_流程控制

import "fmt"

func main() {
	/* switch 的 case 不会往下继续 case */
	var a = "hello"
	switch a {
	case "hello":
		fmt.Println("yes")
	// 多个匹配项
	case "world", "w":
		fmt.Println("a")
	default:
		fmt.Println("no")
	}

	// case 使用条件
	var i int = 11
	switch {
	case i < 9:
		fmt.Println(" < 9 ")
	case i > 10:
		fmt.Println(" > 10")
	}

	// fallthrough
	// 匹配后往下执行
	var j int = 1
	switch j {
	case 1:
		fmt.Println("1")
		fallthrough
	case 2:
		fmt.Println("2")
	}
}
