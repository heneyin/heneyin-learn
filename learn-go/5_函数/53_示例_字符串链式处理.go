package main

import (
	"fmt"
	"strings"
)

/* 链式处理。将函数作为变量传入 */
func StringProcess(list []string, chain []func(string) string) {
	for index, str := range list {
		for _, fu := range chain {
			str = fu(str)
		}
		list[index] = str
	}
}

func main() {
	strList := []string{
		"zhangbeihai  ",
		"Huo Ji",
	}
	chainList := []func(string) string{
		strings.TrimSpace,
		strings.ToUpper,
	}
	StringProcess(strList, chainList)
	fmt.Println(strList)
}
