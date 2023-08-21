package main

import "fmt"

type Vertex struct {
	X int
	Y int
}

func main() {
	fmt.Println(Vertex{1, 3})

	vertex := Vertex{3,4}
	vertex.X = 4

	fmt.Println(vertex.X)

}
