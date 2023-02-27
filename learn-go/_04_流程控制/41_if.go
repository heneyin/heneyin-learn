package _04_流程控制

import "fmt"

func main() {
	ten := 10
	if ten == 10 {
		fmt.Println(ten)
	}
	/* 特殊写法 */
	if err := 99; err != 100 {
		fmt.Println(err)
	}
}
