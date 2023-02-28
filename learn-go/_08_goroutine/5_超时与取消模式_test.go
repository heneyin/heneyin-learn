package _08_goroutine

import (
	"context"
	"errors"
	"fmt"
	"io/ioutil"
	"log"
	"net/http"
	"net/http/httptest"
	"testing"
	"time"
)

// chapter6/sources/go-concurrency-pattern-12.go
type result struct {
	value string
}

// 等待多个请求，返回一个结果则成功
// 当发生超时的时候直接返回。
// 超时的时候为了避免资源浪费，使用 context 包取消请求。
func first(servers ...*httptest.Server) (result, error) {
	c := make(chan result)
	// 使用 context 包，取消 goroutine
	ctx, cancel := context.WithCancel(context.Background())
	defer cancel()
	queryFunc := func(i int, server *httptest.Server) {
		url := server.URL
		req, err := http.NewRequest("GET", url, nil)
		if err != nil {
			log.Printf("query goroutine-%d: http NewRequest error: %s\n", i, err)
			return
		}
		req = req.WithContext(ctx)

		log.Printf("query goroutine-%d: send request...\n", i)
		resp, err := http.DefaultClient.Do(req)
		if err != nil {
			log.Printf("query goroutine-%d: get return error: %s\n", i, err)
			return
		}
		log.Printf("query goroutine-%d: get response\n", i)
		defer resp.Body.Close()
		body, _ := ioutil.ReadAll(resp.Body)

		c <- result{
			value: string(body),
		}
		return
	}

	for i, serv := range servers {
		go queryFunc(i, serv)
	}

	select {
	// 收到返回则退出。
	case r := <-c:
		return r, nil
	// 控制超时
	case <-time.After(500 * time.Millisecond):
		return result{}, errors.New("timeout")
	}
}

func fakeWeatherServer(name string, interval int) *httptest.Server {
	return httptest.NewServer(http.HandlerFunc(func(w http.ResponseWriter,
		r *http.Request) {
		log.Printf("%s receive a http request\n", name)
		time.Sleep(time.Duration(interval) * time.Millisecond)
		w.Write([]byte(name + ":ok"))
	}))
}

func TestRequestTimeoutAndCancel(t *testing.T) {
	result, err := first(fakeWeatherServer("open-weather-1", 200),
		fakeWeatherServer("open-weather-2", 1000),
		fakeWeatherServer("open-weather-3", 600))
	if err != nil {
		log.Println("invoke first error:", err)
		return
	}

	fmt.Println(result)
	time.Sleep(10 * time.Second)
}
