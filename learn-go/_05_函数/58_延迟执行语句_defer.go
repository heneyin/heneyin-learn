package _05_函数

import (
	"fmt"
	"os"
	"sync"
)

// defer 在函数退出前执行，类似于 java 的 finally
// defer 语句以堆栈顺序执行，先放的的后执行
func normal() {
	fmt.Println("defer begin")
	defer fmt.Println(1)
	defer fmt.Println(2)
	defer fmt.Println(3)
	fmt.Println("defer end")
}

/* 加锁与释放锁 */
var (
	valueByKey      = make(map[string]int)
	valueByKeyMutex sync.Mutex
)

func readValue(key string) int {
	valueByKeyMutex.Lock()
	defer valueByKeyMutex.Unlock()
	return valueByKey[key]
}

/* 打开文件与关闭文件句柄 */
func fileSize(filename string) int64 {
	f, err := os.Open(filename)
	if err != nil {
		return 0
	}
	defer f.Close()

	info, err := f.Stat()
	if err != nil {
		return 0
	}

	size := info.Size()
	return size
}

func main() {
	normal()
	valueByKey["hello"] = 10
	fmt.Println(valueByKey["hello"])
	fmt.Println(fileSize("./57_可变参数.go"))
}
