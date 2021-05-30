package main

import "fmt"

// 和 python 的数组切片很像。
// 切片只是数值的引用，或者叫 view

func main() {
	primes := [6]int {1,2,3,4,5,6}

	var s []int = primes[1:4]
	fmt.Println(primes[1:4])
	fmt.Println(s)

	//切片文法类似于没有长度的数组文法。
	q := []int{2, 3, 5, 7, 11, 13}
	fmt.Println(q)

	r := []bool{true, false, true, true, false, true}
	fmt.Println(r)

	ss := []struct {
		i int
		b bool
	}{
		{2, true},
		{3, false},
		{5, true},
		{7, true},
		{11, false},
		{13, true},
	}
	fmt.Println(ss)

	// 可以利用切片的默认行为来忽略上下界

	s = s[1:4]
	fmt.Println(s)

	s = s[:2]
	fmt.Println(s)

	s = s[1:]
	fmt.Println(s)
}
