package _05_函数

import (
	"bytes"
	"fmt"
)

/* 遍历可变参数列表 */
func joinStrings(slist ...string) string {
	var b bytes.Buffer
	for _, s := range slist {
		b.WriteString(s)
	}
	return b.String()
}

/* 获得可变参数类型 */
func printTypeValue(slist ...interface{}) string {
	var b bytes.Buffer
	for _, s := range slist {
		str := fmt.Sprintf("%v", s)
		var typeString string
		switch s.(type) {
		case bool:
			typeString = "bool"
		case string:
			typeString = "string"
		case int:
			typeString = "int"
		}
		b.WriteString("value: ")
		b.WriteString(str)
		b.WriteString(" type: ")
		b.WriteString(typeString)
		b.WriteString("\n")
	}
	return b.String()
}

func main() {
	fmt.Println(joinStrings("a", "b", "c", "d"))
	fmt.Println(joinStrings("a", "b", "c", "d"))
	fmt.Println(printTypeValue(1, "haha", "dd", "cc", true))
}
