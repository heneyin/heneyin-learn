package main


import (
	"fmt"
	"heneyin.com/go_module_greetings"
)

func main() {
	// Get a greeting message and print it.
	message := go_module_greetings.Hello("Gladys")
	fmt.Println(message)
}