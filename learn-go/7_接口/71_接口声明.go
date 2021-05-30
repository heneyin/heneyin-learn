package main

// 声明一个接口
// go 接口的方法都不多，比较精简。
type writer interface {
	Write([] byte) error
}
