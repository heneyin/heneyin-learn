package main

import "fmt"

// & 取出指针
// * 得到指针指向的值。

func main() {
	i, j := 12, 1024

	p := &i   // 指向 i
	fmt.Println(p)

	*p = 13   // 修改 i
	fmt.Println(i)

	p = &j         // 指向 j
	*p = *p / 37   // 通过指针对 j 进行除法运算
	fmt.Println(j) // 查看 j 的值
}
