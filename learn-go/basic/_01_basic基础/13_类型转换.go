package _01_basic基础

import (
	"fmt"
	"math"
	"reflect"
	"strconv"
)

func main() {
	var x, y int = 3, 4
	var f float64 = math.Sqrt(float64(x*x + y*y))
	var z uint = uint(f)
	fmt.Println(x, y, z)

	// string array 转为 int array
	var t = []string{"1", "2", "3"}
	var t2 = []int64{}

	for _, i := range t {
		// j, err := strconv.Atoi(i)
		j, err := strconv.ParseInt(i, 0, 64)
		if err != nil {
			panic(err)
		}
		t2 = append(t2, j)
	}

	fmt.Println(t2)

	s, e := strconv.ParseInt("10.0", 10, 64)
	fmt.Println(e)
	fmt.Println(s)
}

func type1(args ...interface{}) {
	for _, element := range args {
		fmt.Printf("%T\n", element)
		println(reflect.TypeOf(element).Name())
		i, ok := element.(int)
		if ok {
			println(i)
		} else {
			println("error element")
		}
		i2, ok2 := args[0].(int)
		if ok2 {
			println(i2)
		} else {
			println("error arr[0]")
		}
		switch element.(type) {
		case interface{}:
			println("interface")
		case int:
			println("int")
		default:
			println("default")
		}
	}
}
