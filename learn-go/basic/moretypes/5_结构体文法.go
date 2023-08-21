package main

import "fmt"

// https://tour.go-zh.org/moretypes/5

type Vertex2 struct {
	X, Y int
}

var (
	v1 = Vertex2{1, 3}
	v2 = Vertex2{X: 1}
	v3 = Vertex2{}
	p = & Vertex2{3,4}
)

func main() {
	fmt.Println(v1, v2, v3, p)
}

