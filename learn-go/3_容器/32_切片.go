package main

import (
	"fmt"
)

func main() {
	/* 从指定范围中生成切片 */
	var highRiseBuilding [30]int

	for i := 0; i < 30; i ++ {
		highRiseBuilding[i]  = i + 1
	}

	// 生成新的切片，指向旧内存
	fmt.Println(highRiseBuilding[10:15])
	fmt.Println(highRiseBuilding[20:])
	fmt.Println(highRiseBuilding[:2])

	/* 表示原有切片 */
	a := []int {1, 2, 3, 4}
	fmt.Println(a[:])

	/* 重置切片 */
	b := []int {1, 2, 3, 4}
	fmt.Println(b[0:0])

	/* 声明切片 */
	var strList []string
	var intList []int
	var intListEmpty []int = []int{}

	fmt.Println(strList, intList, intListEmpty)
	fmt.Println(strList == nil)   // true
	fmt.Println(intList == nil)   // true
	fmt.Println(intListEmpty == nil)  // false

	/* 动态构造切片 */
	a1 := make([]int, 2)
	// 类型 元素个数 容量
	a2 := make([]int, 2, 10)
	fmt.Println(a1, a2)
	fmt.Println(len(a1), len(a2))

	/* append 添加元素 */
	// 切片扩充，按照当前的容量的 2 倍扩充, 每次扩充都会拷贝一次所有元素。
	var nums []int
	for i := 0; i < 10; i++ {
		nums = append(nums, i)
		fmt.Printf("len: %d, cap: %d, pointer: %p\n", len(nums), cap(nums), nums)
	} 
	// 添加
	nums = append(nums, 33, 44, 55)
	// 添加切片
	nums = append(nums, []int{88,99}...)
	fmt.Println(nums)

	/* 复制切片元素 */
	const eleCount = 10
	srcData := make([]int, eleCount)
	for i := 0; i < eleCount; i ++ {
		srcData[i] = i;
	}
	copyData := make([]int, eleCount)

	copy(copyData, srcData)
	fmt.Println(copyData)
	fmt.Println(srcData)

	// 删除元素
	seq := []int{1,2,3,4,5,6}
	index := 3
	fmt.Println(seq[:index])
	fmt.Println(seq[index + 1:])
	seq = append(seq[:index], seq[index + 1:]...)
	fmt.Println(seq)
}