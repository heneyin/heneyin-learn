package memmodels

var a string

func f() {
	println(a)
}

// 一定会输出
// goroutine 创建
func helloCreate() {
	a = "hello world"
	go f()
}


// goroutine 销毁
// 执行完就没了
func helloDestroy() {
	go func() {a = "hello"}()
	println(a)
}