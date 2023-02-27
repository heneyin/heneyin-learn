package _04_流程控制

import "fmt"

func main() {
	/* 初始化 */
	for i := 0; i < 10; i++ {
		fmt.Println(i)
	}

	/* 初始化语句在外面 */
	j := 0
	for ; j < 5; j++ {
		fmt.Println(j)
	}

	/* 控制循环 */
	var k int
	for ; ; k++ {
		if k < 2 {
			break
		}
	}

	/* 无限循环 */
	var l int
	for {
		if l > 2 {
			break
		}
		l++
	}

	/* 只有循环判断条件 */
	var o int
	for o < 2 {
		o++
	}

	/* 99 乘法表*/

	for x := 1; x <= 9; x++ {
		for y := 1; y <= x; y++ {
			fmt.Printf("%d*%d=%d ", x, y, x*y)
		}
		fmt.Println()
	}

}
