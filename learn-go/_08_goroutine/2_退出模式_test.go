package _08_goroutine

/**
goroutine 的退出。
一般无需考虑 goroutine 的退出控制：
1. goroutine 的执行函数返回，意味着 goroutine 退出了。
2. 常驻的后台服务程序，可能有对 goroutine 优雅退出的需求。
下面四几种退出模式：
*/

/**
分离模式：detached
创建它的 goroutine 不需要关心他的退出，这类 goroutine 退出后与其创建者彻底分离。
*/
