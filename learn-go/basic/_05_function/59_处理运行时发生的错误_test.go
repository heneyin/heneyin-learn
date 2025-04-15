package _05_function

import (
	"errors"
	"fmt"
	"testing"
)

/* 定义与使用 error */
// 借助 errors 包来定义
var errDivisionByZero = errors.New("division by zero")

func div(a, b int) (int, error) {
	if b == 0 {
		return 0, errDivisionByZero
	}
	return a / b, nil
}

// 自定义 error，包含更多信息
type ParseError struct {
	Filename string
	Line     int
}

// 实现 error 接口，描述错误
func (e *ParseError) Error() string {
	return fmt.Sprintf("%s:%d", e.Filename, e.Line)
}

// 创建  error 的方法。
func newParseError(filename string, line int) error {
	return &ParseError{filename, line}
}

func TestFunctionRuntimeError(t *testing.T) {
	fmt.Println(div(1, 0))

	var e error
	e = newParseError("a.go", 10)
	fmt.Println(e.Error())

	switch detail := e.(type) {
	case *ParseError:
		fmt.Printf("Filenam: %s Line: %d\n", detail.Filename, detail.Line)
	default:
		fmt.Println("other error")
	}
}
