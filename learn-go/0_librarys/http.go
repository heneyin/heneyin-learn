package main

import (
	"fmt"
	"io/ioutil"
	"net/http"
	"os"
	"strings"
)

/* http 包的简单使用*/

func main() {
	// 实例化客户端
	client := &http.Client{}

	// 创建一个 POST 请求
	bodyReader := strings.NewReader("key=value")  // 一个字符串读取器
	req, err := http.NewRequest("POST", "http://www.163.com/", bodyReader)

	if err != nil {
		fmt.Println(err)
		os.Exit(1)
		return
	}

	// 向请求中添加 header
	req.Header.Add("User-Agent", "my client")

	// 请求，并获取响应。
	resp, err := client.Do(req)
	defer resp.Body.Close()

	if err != nil {
		fmt.Println(err)
		os.Exit(1)
		return
	}

	// 保存响应结果到 data 中
	data, err := ioutil.ReadAll(resp.Body)
	fmt.Println(string(data))

}
