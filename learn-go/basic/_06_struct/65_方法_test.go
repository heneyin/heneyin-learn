package _06_struct

import (
	"fmt"
	"testing"
)

/* 指针类型接收器 - 可修改对象数据 */
type Property struct {
	value int
}

func (p *Property) SetValue(v int) {
	p.value = v
}

func (p *Property) Value() int {
	return p.value
}

func (p Point) Add(other Point) Point {
	return Point{p.X + other.X, p.Y + other.Y}
}

func TestFunction(t *testing.T) {
	// 使用指针类型接受器
	p := new(Property)
	p.SetValue(100)
	fmt.Println(p.Value())

	// 使用非指针类型接受器
	p1 := Point{1, 1}
	p2 := Point{2, 2}

	result := p1.Add(p2)

	fmt.Println(result)
}
