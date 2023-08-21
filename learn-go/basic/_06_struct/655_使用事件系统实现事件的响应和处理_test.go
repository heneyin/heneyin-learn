package _06_struct

import (
	"fmt"
	"testing"
)

// 一个结构体
type class struct {
}

// 为结构体添加方法
func (c *class) Do(v int) {
	fmt.Println("call method do: ", v)
}

// 普通的函数方法
func funcDo(v int) {
	fmt.Println("call function do: ", v)
}

/* 事件注册 */
// key 事件名称  value 多个事件执行器
var eventByName = make(map[string][]func(interface{}))

func RegisterEvent(name string, callback func(interface{})) {
	list := eventByName[name]
	list = append(list, callback)
	eventByName[name] = list
}

/* 事件调用 */
func CallEvent(name string, param interface{}) {
	list := eventByName[name]

	for _, callback := range list {
		callback(param)
	}
}

/* 事件执行的角色 */
type Actor struct {
}

func (*Actor) onEvent(param interface{}) {
	fmt.Println("actor event:", param)
}

func GlobalEvent(param interface{}) {
	fmt.Println("global event:", param)
}

func TestEvent(t *testing.T) {
	// 一个函数或方法的代理。
	// 函数式编程中低方法的定义
	// 可以将结构体的方法或者普通函数赋值给它。
	var delegate func(int)

	c := new(class)

	delegate = c.Do

	delegate(100)

	delegate = funcDo

	delegate(100)

	// 事件注册
	a := new(Actor)

	RegisterEvent("onSkill", a.onEvent)
	RegisterEvent("onSkill", GlobalEvent)

	CallEvent("onSkill", 100)
}
