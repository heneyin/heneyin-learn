package main

import "fmt"


type Vertex1 struct {
	X int
	Y int
}

func main() {
	v := Vertex1{5, 5}
	p := &v
	// (*p).X 的简略写法。
	p.X = 7
	fmt.Println(v)
}
