package _06_struct

import (
	"fmt"
	"testing"
)

/* 模拟构造函数重载 */
func NewUserByName(name string) *User {
	return &User{
		Name: name,
	}
}

func NewUserByLike(like string) *User {
	return &User{
		Like: like,
	}
}

/* 模拟父子关系 */
type Cat struct {
	Color string
	Name  string
}

type BlackCat struct {
	Cat  // 类似派生
	Like string
}

func NewCatByName(name string) *Cat {
	return &Cat{
		Name: name,
	}
}

func NewBlackCat(name string) *BlackCat {
	cat := &BlackCat{} // Cat 也会被初始化
	cat.Name = name
	cat.Like = "black"
	return cat
}

func TestCreateFunction(t *testing.T) {
	user1 := NewUserByLike("play")
	user2 := NewUserByName("hello")
	fmt.Println(user1)
	fmt.Println(user2)

	cat := NewBlackCat("tom")
	fmt.Println(cat)
}
