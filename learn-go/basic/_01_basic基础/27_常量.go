package _01_basic基础

import "fmt"

/* 枚举 */
// 一组常量值
// 使用 iota 模拟枚举，iota 会自动填充常量。
type Killer int

const (
	A Killer = 1 << iota
	B
	C
)

// 实现获取枚举字符串
func (k Killer) String() string {
	switch k {
	case A:
		return "A"
	case B:
		return "B"
	case C:
		return "C"
	}
	return "N/A"
}

func main() {
	/* 声明常量 */
	const a = 1234

	const (
		b = 123
		c = "ad"
	)

	/* 枚举 */
	var aKiller Killer = A
	fmt.Println(A, B, C)
	fmt.Println(aKiller)

	fmt.Printf("%d %s\n", A, A)

}
