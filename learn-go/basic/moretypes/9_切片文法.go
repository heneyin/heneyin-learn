package main

import "fmt"

// 切片类似于没有长度的数组的文法，在 [] 中不指定长度就是切片了。

func main() {
	q := []int{1,2,3,4,5,6,7}
	fmt.Println(q)

	r := []bool{true, false, true, true, false, true}
	fmt.Println(r)

	s := [] struct {
		x int
		y bool
	}{
		{2, true},
		{3, false},
		{5, true},
		{7, true},
		{11, false},
		{13, true},
	}
	fmt.Println(s)
}


