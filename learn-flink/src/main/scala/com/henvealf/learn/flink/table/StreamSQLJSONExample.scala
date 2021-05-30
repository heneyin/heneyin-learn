package com.henvealf.learn.flink.table

import com.alibaba.fastjson.JSONObject
import org.apache.flink.api.common.typeinfo.TypeInformation
import org.apache.flink.api.java.utils.ParameterTool
import org.apache.flink.streaming.api.scala.{DataStream, StreamExecutionEnvironment}
import org.apache.flink.table.api._
import org.apache.flink.table.api.bridge.scala._
import org.apache.flink.types.Row
import org.apache.flink.api.scala.typeutils.Types
import org.apache.flink.api.scala._

import collection.JavaConverters._

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
object StreamSQLJSONExample {

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

    val json1 = new JSONObject();
    json1.putAll(Map( "id" -> 1, "name" -> "beer", "age" -> 12 ).asJava)

    val json2 = new JSONObject();
    json2.putAll(Map( "id" -> 2, "name" -> "diaper", "age" -> 0 ).asJava)

    val json3 = new JSONObject();
    json3.putAll(Map( "id" -> 3, "name" -> "rubber", "age" -> 1 ).asJava)

    val json4 = new JSONObject();
    json4.putAll(Map( "id" -> 4, "name" -> "pen", "age" -> 43 ).asJava)

    val json5 = new JSONObject();
    json5.putAll(Map( "id" -> 5, "name" -> "rubber", "age" -> 12 ).asJava)

    val json6 = new JSONObject();
    json6.putAll(Map( "id" -> 6, "name" -> "beer", "age" -> 32 ).asJava)


    val orderJsonStream: DataStream[JSONObject] = env.fromCollection(Seq(
      json1,
      json2,
      json3))

    val rowStream: DataStream[Row] = orderJsonStream.map((json) => {
      Row.of(json.getLong("id"), json.getString("name"), json.getInteger("age"))
    })(org.apache.flink.api.scala.typeutils.Types.ROW(
      Types.LONG, Types.STRING, Types.INT
    ))
    tEnv.fromDataStream(rowStream, $"id", $"name", $"age")
    tEnv.createTemporaryView("rowStream", rowStream, $"id", $"name", $"age")
    println(rowStream.toString)
    val r = tEnv.sqlQuery(
      s"""
        |select * from rowStream where age > 10
        |""".stripMargin)
    r.toAppendStream[Order].print("r-from-row")

    val orderA: DataStream[Order] = env.fromCollection(Seq(
      json1,
      json2,
      json3))
      .map((json) => Order(json.getLongValue("id"), json.getString("name"), json.getIntValue("age")))

    val orderB: DataStream[Order] = env.fromCollection(Seq(
      json4,
      json5,
      json6))
      .map((json) => Order(json.getLongValue("id"), json.getString("name"), json.getIntValue("age")))


    // convert DataStream to Table
    val tableA = tEnv.fromDataStream(orderA, $"id", $"name", $"age")
    // register DataStream as Table
    tEnv.createTemporaryView("OrderB", orderB, $"id", $"name", $"age")
    println(s"tableA ${tableA}")
    // union the two tables
    val result: Table = tEnv.sqlQuery(
      s"""
         |SELECT * FROM $tableA WHERE age > 2
         |UNION ALL
         |SELECT * FROM OrderB WHERE age < 2
        """.stripMargin)

    result.toAppendStream[Order].print()

    env.execute()
  }

  // *************************************************************************
  //     USER DATA TYPES
  // *************************************************************************

  case class Order(user: Long, product: String, amount: Int)

  // 窗口的测试，
  //    val result: Table = tEnv.sqlQuery(
  //      s"""
  //        | select count(1) c,
  //        | TUMBLE_START(user_action_time, INTERVAL '10' SECOND) as t,
  //        | id, 'a', 'b', 'c'
  //        | from $eventTable
  //        | GROUP BY TUMBLE(user_action_time, INTERVAL '10' SECOND), id
  //        |""".stripMargin)
  //
  //    result.toAppendStream[(Long, Timestamp, Long, String, String, String)].print("count")
  //
  //    val resultAvg: Table = tEnv.sqlQuery(
  //      s"""
  //         | select avg(amount) aavg,
  //         | TUMBLE_START(user_action_time, INTERVAL '10' SECOND) as t,
  //         | id, 'a', 'b', 'c'
  //         | from $eventTable
  //         | GROUP BY TUMBLE(user_action_time, INTERVAL '10' SECOND), id
  //         |""".stripMargin)
  //    resultAvg.toAppendStream[(Double, Timestamp, Long, String, String, String)].print("avg")
  //
  //    val select: Table = tEnv.sqlQuery(
  //      s"""
  //         | select id, product, amount from $eventTable
  //         |""".stripMargin)
  //    // select.toAppendStream[(Long, String, Long)].print("select")
  //
  //    val sum: Table = tEnv.sqlQuery(
  //      s"""
  //         | select sum(amount) aavg,
  //         | TUMBLE_START(user_action_time, INTERVAL '10' SECOND) as t,
  //         | id, 'a', 'b', 'c'
  //         | from $eventTable
  //         | GROUP BY TUMBLE(user_action_time, INTERVAL '10' SECOND), id
  //         |""".stripMargin)
  //    sum.toAppendStream[(Double, Timestamp, Long, String, String, String)].print("sum")
  //
  ////    tEnv.executeSql(
  ////      """
  ////        |CREATE TABLE print_table (
  ////        | c BIGINT
  ////        | ) WITH (
  ////        | 'connector' = 'print'
  ////        |)
  ////        |""".stripMargin)
  ////
  ////    result.executeInsert("print_table")
  //
  //    // count
  //    val afterCount = filteredStream
  //      .map((e) => (e.selectKeyAsJson(insModelSpec.getGroupBy), 1))
  //      .keyBy((t) => t._1)
  //      .window(TumblingEventTimeWindows.of(Time.seconds(insModelSpec.getWindowsTimes) /* size */))
  //      .sum(1)
  //      .map( (cr) => {
  //        val me = new ModelEvent(cr._1)
  //        me.putIndicatorFieldsFromSpec(sttscSpec, cr._2)
  //        me.setSinks(insModelSpec.getSink)
  //        me
  //      })
  //
  //    afterCount.print("count")

}
