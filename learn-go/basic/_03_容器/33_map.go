package _03_容器

import (
	"fmt"
	"sync"
)

func main() {

	/* 声明与添加数据 */
	amap := make(map[string]int)
	amap["one"] = 1
	amap["two"] = 2

	fmt.Println(amap["one"])

	/* 判断 key 是否存在 */
	v, ok := amap["three"]
	fmt.Println(v)
	fmt.Println(ok)

	v2, ok2 := amap["two"]
	fmt.Println(v2)
	fmt.Println(ok2)

	/* 遍历 map */
	for k, v := range amap {
		fmt.Println(k, v)
	}

	/* 删除 key */
	delete(amap, "one")
	for k, v := range amap {
		fmt.Println(k, v)
	}

	/* 清空 map */
	// 重新建立一个 map

	/* 线程安全 map */
	// 不在乎元素类型。
	var syncMap sync.Map
	// 保存元素
	syncMap.Store("a", 1)
	syncMap.Store(1, 1)

	syncMap.Delete("a")
	// 遍历
	syncMap.Range(func(k, v interface{}) bool {
		fmt.Println(k, v)
		return true // false 表示停止遍历
	})

	// 获取
	fmt.Println(syncMap.Load(1))

}
