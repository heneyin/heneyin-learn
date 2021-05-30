package com.henvealf.learn.flink.connector.kafka

import org.apache.flink.table.api.{EnvironmentSettings, TableEnvironment}

/**
  *
  * @author hongliang.yin/Henvealf  
  * @date 2020/7/13
  */
object KafkaTableStreamJob {

//  def main(args: Array[String]): Unit = {
//    val settings = EnvironmentSettings.newInstance()
//      .build()
//    val tEnv = TableEnvironment.create(settings)
//
//    // 注册 source table
//    tEnv.executeSql(
//      """
//        |create table TestKafka (
//        | `name` STRING,
//        | `age` BIGINT
//        |) WITH (
//        | 'connector' = 'kafka',
//        | 'topic' = 'users',
//        | 'properties.bootstrap.servers' = 'kafka:9092',
//        | 'properties.group.id' = 'testGroup1',
//        | 'format' = 'csv',
//        | 'scan.startup.mode' = 'earliest-offset'
//        |)
//        |""".stripMargin)
//
//    // 执行查询
//    val tableResult1 = tEnv.executeSql("select count(1) FROM TestKafka group by name")
//    tableResult1.print()
//  }


}
