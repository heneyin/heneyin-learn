package _06_struct

import (
	"fmt"
	"testing"
)

type Command struct {
	Name string
	Var  *int // 指针，指向传入数据
}

func TestVar(t *testing.T) {
	/* 实例化结构体 */
	var p Point
	p.X = 10
	p.Y = 10
	fmt.Println(p)

	/* 获取实例指针 */
	pp := new(Point)
	// 访问成员-语法糖, 实际为 (*pp).X
	pp.X = 11
	pp.Y = 12

	/* 使用取地址符号实例化 */
	comm := &Command{}
	comm.Name = "hello"

	value := 10
	comm.Var = &value

	fmt.Println(*comm.Var)
	value = 11
	fmt.Println(*comm.Var)

	// 当做构造器使用
	comm1 := &Command{
		Name: "world",
		Var:  &value,
	}
	fmt.Println(comm1.Name)
	value = 100
	fmt.Println(*comm1.Var)
}
