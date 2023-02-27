package _04_流程控制

import "fmt"

func main() {
	var breakAgain bool

	for i := 0; i < 10; i++ {
		for j := 0; j < 10; j++ {
			if j == 2 {
				breakAgain = true
				break
			}
		}
		if breakAgain {
			break
		}
	}

	fmt.Println("done")

	/* 使用 沟通 完成上面的逻辑 */
	for i := 0; i < 10; i++ {
		for j := 0; j < 10; j++ {
			if j == 2 {
				goto breakHere
			}
		}
	}
	return
breakHere:
	fmt.Println("done in goto")

}
