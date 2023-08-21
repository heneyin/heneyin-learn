package _04_流程控制

import "fmt"

func main() {
	/* 遍历数组与切片 */
	for key, value := range []int{1, 2, 3, 4} {
		fmt.Println(key, value)
	}

	/* 遍历字符串, 获得字符 */
	for key, value := range "hello  世界" {
		fmt.Println(key, value)
	}

	/* 遍历map， 获得 key， value */
	m := map[string]int{
		"hello": 100,
		"world": 200,
	}
	for key, value := range m {
		fmt.Println(key, value)
	}

	/* 遍历通道 */
	c := make(chan int)

	go func() {
		c <- 1
		c <- 2
		close(c)
	}()
	for v := range c {
		fmt.Println(v)
	}

	/* 下划线省略不需要的变量 */
	for _, value := range m {
		fmt.Println(value)
	}
}
