package main

import "io"

// 类型与接口可以一对多，也可以多对一

type Socket struct {

}

// 实现了 io.Writer
func (s * Socket) Write(p []byte) (n int, err error) {
	return 0, nil
}

// 实现了 io.Close
func (s * Socket) Close() error {
	return nil
}

func usingWriter(writer io.Writer) {
	writer.Write(nil)
}

func usingCloser(closer io.Closer) {
	closer.Close()
}

func main()  {
	s := new(Socket)
	usingWriter(s)
	usingCloser(s)
}

