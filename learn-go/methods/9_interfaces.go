package main

import (
	"fmt"
	"math"
)

// 接口类型是一组方法签名定义的集合

// 一个接口类型
type Abser interface {
	Abs() float64
}

type MyFloat float64

func (f MyFloat) Abs() float64 {
	if f < 0 {
		return float64(-f)
	}
	return float64(f)
}

type Vertex2 struct {
	X, Y float64
}

func (v *Vertex2) Abs() float64  {
	return math.Sqrt(v.X*v.X + v.Y*v.Y)
}

func main() {
	var a Abser
	f := MyFloat(-math.Sqrt2)
	v := Vertex2{3, 4}

	a = f  // a MyFloat 实现了 Abser
	a = &v // a *Vertex2 实现了 Abser

	// 下面一行的 v 是一个 Vertex2 而不是 *Vertex2，所以没有实现 Abser
	// a = v
	fmt.Println(a.Abs())
}
