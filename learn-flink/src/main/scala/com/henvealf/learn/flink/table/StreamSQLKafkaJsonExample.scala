package com.henvealf.learn.flink.table

import org.apache.flink.api.java.utils.ParameterTool
import org.apache.flink.api.scala._
import org.apache.flink.streaming.api.scala.{DataStream, StreamExecutionEnvironment}
import org.apache.flink.table.api._
import org.apache.flink.table.api.bridge.scala._

/**
  * Simple example for demonstrating the use of SQL on a Stream Table in Scala.
  *
  * <p>Usage: <code>StreamSQLExample --planner &lt;blink|flink&gt;</code><br>
  *
  * <p>This example shows how to:
  *  - Convert DataStreams to Tables
  *  - Register a Table under a name
  *  - Run a StreamSQL query on the registered Table
  *
  */
object StreamSQLKafkaJsonExample {

  // *************************************************************************
  //     PROGRAM
  // *************************************************************************

  def main(args: Array[String]): Unit = {

    val params = ParameterTool.fromArgs(args)
    var planner = if (params.has("planner")) params.get("planner") else "blink"

    planner = "blink"
    // set up execution environment
    val env = StreamExecutionEnvironment.getExecutionEnvironment
    val tEnv = if (planner == "blink") {  // use blink planner in streaming mode
      val settings = EnvironmentSettings.newInstance()
        .useBlinkPlanner()
        .inStreamingMode()
        .build()
      StreamTableEnvironment.create(env, settings)
    } else if (planner == "flink") {  // use flink planner in streaming mode
      val settings = EnvironmentSettings.newInstance()
        .useOldPlanner()
        .inStreamingMode()
        .build()
      StreamTableEnvironment.create(env, settings)
    } else {
      System.err.println("The planner is incorrect. Please run 'StreamSQLExample --planner <planner>', " +
        "where planner (it is either flink or blink, and the default is blink) indicates whether the " +
        "example uses flink planner or blink planner.")
      return
    }



    tEnv.executeSql(
      """
        |CREATE TABLE user_behavior (
        |  id BIGINT,
        |  product STRING,
        |  amount BIGINT,
        |  user_action_time AS PROCTIME()
        |) WITH (
        | 'connector' = 'kafka',
        | 'topic' = 'user_behavior',
        | 'properties.bootstrap.servers' = 'localhost:9092',
        | 'properties.group.id' = 'testGroup',
        | 'format' = 'json',
        | 'json.fail-on-missing-field' = 'false',
        | 'json.ignore-parse-errors' = 'true'
        |)
        |""".stripMargin)

    // 带窗口的。
    val result: Table = tEnv.sqlQuery(
      """
        | select
        |   TUMBLE_START(user_action_time, INTERVAL '10' SECOND) as t,
        |   id,
        |   count(1) as c
        |
        | from user_behavior
        | GROUP BY TUMBLE(user_action_time, INTERVAL '10' SECOND), id
        |""".stripMargin)


    result.toAppendStream[Order].print()
    env.execute()
  }

  // *************************************************************************
  //     USER DATA TYPES
  // *************************************************************************

  case class Order(t: java.sql.Timestamp, id: Long, c: Long)

}
