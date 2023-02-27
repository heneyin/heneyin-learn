package _01_basic基础

import (
	"bufio"
	"bytes"
	"encoding/base64"
	"fmt"
	"os"
	"strings"
	"unicode/utf8"
)

func main() {
	// 字符串字节长度 len()
	str1 := "hello world"
	fmt.Printf("%s len: %d \n", str1, len(str1))

	str2 := "你好"
	// 你好 len: 6
	fmt.Printf("%s len: %d\n", str2, len(str2))
	// 计算Unicode字符串长度
	fmt.Println(utf8.RuneCountInString("你好"))

	/* 遍历字符串 */
	//Unicode字符串遍历用for range
	theme := "你好 Hello"
	for _, s := range theme {
		fmt.Printf("Unicode: %c, %d \n", s, s)
	}

	/* 获取子串 */
	rootStr := "ab次,defg"
	comma := strings.Index(rootStr, ",")
	first := rootStr[0:comma]
	last := rootStr[comma+1:]
	fmt.Println(first, last)

	/* 修改字符串 */
	// 字符串是不可变的。先复制为 byte 数组，修改数组后转为字符串。
	angle := "what is it"
	change := []byte(angle)
	for i := 5; i < len(angle); i++ {
		change[i] = '-'
	}
	fmt.Println(string(change))

	/*连接字符串*/
	// 加号 性能差
	// bytes.Buffer 利用字节缓冲区，性能好
	head := "我的天~"
	end := "太尴尬了"
	var stringBuilder bytes.Buffer
	stringBuilder.WriteString(head)
	stringBuilder.WriteString(end)
	fmt.Println(stringBuilder.String())

	/*格式化*/
	// printf

	/* base64 编码 */
	rawMessage := "I am your father"
	encodedMessage := base64.StdEncoding.EncodeToString([]byte(rawMessage))
	fmt.Println(string(encodedMessage))
	data, err := base64.StdEncoding.DecodeString(encodedMessage)

	if err != nil {
		fmt.Println(err)
	} else {
		fmt.Println(string(data))
	}

	/* 读取 INI 配置文件 */

	/* 读取文件 */
	// 底层是读取为 bytes
	filename := "text.txt"

	// 打开文件
	file, err := os.Open(filename)
	if err != nil {
		fmt.Println("打开文件失败")
		return
	}

	reader := bufio.NewReader(file)

	for {
		linestr, err := reader.ReadString('\n')

		if err != nil {
			break
		}

		fmt.Println(linestr)
	}
	file.Close()

}
