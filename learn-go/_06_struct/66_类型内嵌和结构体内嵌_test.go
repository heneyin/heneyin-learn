package _06_struct

import (
	"fmt"
	"testing"
)

/*类型内嵌*/
type Data struct {
	int
	float32
	bool
}

/*结构体内嵌*/
// 基础颜色
type BasicColor struct {
	R, G, B float32
}

// 完整颜色
type Color struct {
	// 传统的方法
	// 使用的时候即 c.Basic.R
	Basic BasicColor
	// 结构体内嵌
	// 使用的时候直接 c.R 就可
	BasicColor
	Alpha float32
}

/*初始化结构体内嵌*/
type Wheel struct {
	Size int
}

type Engine struct {
	Power int
	Type  string
}

type Car struct {
	Wheel
	Engine
}

func initCar() {
	c := Car{
		Wheel: Wheel{
			Size: 10,
		},
		Engine: Engine{
			Type:  "1.4T",
			Power: 143,
		},
	}
	fmt.Printf("%+v\n", c)
}

/*初始化内嵌匿名结构体*/
type CarNoName struct {
	Wheel
	EngineNoName struct {
		Power int
		Type  string
	}
}

func initNoCar() {
	c := CarNoName{
		Wheel: Wheel{
			Size: 18,
		},
		EngineNoName: struct {
			Power int
			Type  string
		}{
			Power: 10,
			Type:  "nb",
		},
	}
	fmt.Printf("%+v\n", c)
}

func TestEmbedded(t *testing.T) {
	// 初始化类型内嵌
	ins := &Data{
		int:     10,
		float32: 100,
		bool:    true,
	}

	fmt.Println(ins.bool)

	// 使用结构体内嵌
	var c Color
	c.Basic.R = 1
	c.Basic.B = 1
	c.Basic.G = 1

	c.Alpha = 1

	c.B = 1

	fmt.Printf("%+v\n", c)

	initCar()
	initNoCar()
}
