package _03_容器

import (
	"container/list"
	"fmt"
)

func main() {
	/* 声明 list */
	// 双向 list
	alist := list.New()
	// var aalist list.List

	// 添加
	alist.PushBack("first")
	// 获取元素句柄
	p := alist.PushFront(66)
	// 在句柄后添加
	alist.InsertAfter("after", p)
	// 在句柄前添加
	alist.InsertBefore("before", p)
	// 使用句柄删除
	alist.Remove(p)

	// 遍历
	// Front 为第一个元素
	for i := alist.Front(); i != nil; i = i.Next() {
		// 获取值
		fmt.Println(i.Value)
	}
}
