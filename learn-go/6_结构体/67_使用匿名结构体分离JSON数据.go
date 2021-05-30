package main

import (
	"encoding/json"
	"fmt"
)

type Screen struct {
	Size float32
	ResX, ResY int
}

type Battery struct {
	Capacity int
}

func genJsonData() []byte {
	raw := &struct {
		Screen
		Battery
		HasTouchId bool
	}{
		Screen: Screen{
			Size: 10,
			ResX: 100,
			ResY: 200,
		},
		Battery: Battery{
			10,
		},
		HasTouchId: true,
	}
	jsonData, _ := json.Marshal(raw)

	return jsonData
}
func main() {
	jsonData := genJsonData()
	fmt.Println(string(jsonData))

	//分离 json 的各个部分
	screenAndTouch := struct {
		Screen
		HasTouchId bool
	}{}

	json.Unmarshal(jsonData, &screenAndTouch)

	fmt.Printf("%+v", screenAndTouch)
}