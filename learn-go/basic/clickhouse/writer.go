package main

import (
	"database/sql"
	"fmt"
	"github.com/ClickHouse/clickhouse-go"
	"log"
)

func write() {

	totalCount := 1000_0000 // 一千万
	batchSize := 10000
	batchCount := totalCount / batchSize // 粗略

	clickhouseAddress := "tcp://192.168.97.204:30009?database=asap_platform"

	connect, err := sql.Open("clickhouse", clickhouseAddress)
	if err != nil {
		log.Fatal(err)
	}
	if err := connect.Ping(); err != nil {
		if exception, ok := err.(*clickhouse.Exception); ok {
			fmt.Printf("[%d] %s \n%s\n", exception.Code, exception.Message, exception.StackTrace)
		} else {
			fmt.Println(err)
		}
		return
	}

	// _, err = connect.Exec(`
	// 	CREATE TABLE IF NOT EXISTS example (
	// 		country_code FixedString(2),
	// 		os_id        UInt8,
	// 		browser_id   UInt8,
	// 		categories   Array(Int16),
	// 		action_day   Date,
	// 		action_time  DateTime
	// 	) engine=Memory
	// `)

	// if err != nil {
	// 	log.Fatal(err)
	// }

	var (
		tx, _   = connect.Begin()
		stmt, _ = tx.Prepare("INSERT INTO ip_tag_row (dt, ip_number, tag_name, tag_value) VALUES (?, ?, ?, ?)")
	)
	defer stmt.Close()
	for i := 0; i < batchCount; i++ {
		for j := 0; j < batchSize; j++ {
			if _, err := stmt.Exec(
				"2021-12-10",
				10+i+j*batchSize,
				"非工作时间下载文件",
				"是",
			); err != nil {
				log.Fatal(err)
			}
		}
		if err := tx.Commit(); err != nil {
			log.Fatal(err)
		}
	}

}

func main() {
	write()
}
