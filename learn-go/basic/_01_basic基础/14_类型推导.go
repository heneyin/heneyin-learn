package _01_basic基础

import (
	"fmt"
	"reflect"
)

func main() {
	v := 42 // 修改这里！
	fmt.Printf("v is of type %T\n", v)
	v1 := 12.3
	fmt.Printf("v1 is of type %T\n", v1)
	v2 := 0.867 + 0.5i
	fmt.Printf("v1 is of type %T\n", v2)

	arrint := []int{1, 2, 3}
	fmt.Printf("arrint is of type %s\n", reflect.TypeOf(arrint).String())

	arrstr := []string{"1", "2", "3"}
	fmt.Printf("arrstr is of type %s\n", reflect.TypeOf(arrstr).String())
}
