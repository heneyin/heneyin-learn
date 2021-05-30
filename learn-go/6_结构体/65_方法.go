package main 

import "fmt"

/* 指针类型接收器 - 可修改对象数据 */
type Property struct {
	value int
}

func (p * Property) SetValue(v int) {
	p.value = v
}

func (p * Property) Value() int  {
	return p.value
}

/* 使用非指针类型接受器 - 获得对象的拷贝*/
type Point struct {
	X int
	Y int
}

func (p Point) Add(other Point) Point {
	return Point{p.X + other.X, p.Y + other.Y}
}

func main() {
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