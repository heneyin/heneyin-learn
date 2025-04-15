package _05_function

import (
	"fmt"
	"strings"
	"testing"
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

func TestFunctionChain(t *testing.T) {
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
