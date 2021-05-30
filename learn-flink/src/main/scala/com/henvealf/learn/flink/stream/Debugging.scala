package com.henvealf.learn.flink.stream

import org.apache.flink.streaming.api.datastream.DataStreamUtils
import org.apache.flink.streaming.api.scala._
import scala.collection.JavaConverters.asScalaIteratorConverter

/**
  * 模拟 Debugging
  *
  * @author hongliang.yin/Henvealf  
  * @date 2019-09-19
  */
object Debugging {
  def main(args: Array[String]): Unit = {
    // 使用本地模式
    val env = StreamExecutionEnvironment.createLocalEnvironment()
    // 造数据
    val testStream = env.fromElements("hello","world","hello", "world")
    // 执行逻辑
    val resultStream = testStream.filter(_ == "hello")
    // 查看结果
    val resultCollection = DataStreamUtils.collect(resultStream.javaStream).asScala
    resultCollection.foreach(println)
  }
}
