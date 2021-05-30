package com.henvealf.learn.flink.stream

import org.apache.flink.api.scala._
import org.apache.flink.streaming.api.datastream.DataStreamUtils
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import scala.collection.JavaConverters.asScalaIteratorConverter
/**
  * 不清楚是啥意思
  * iteration 的使用
  * @author hongliang.yin/Henvealf  
  * @date 2019-09-19
  */
object Iterations {
  def main(args: Array[String]): Unit = {
    val env = StreamExecutionEnvironment.createLocalEnvironment()

    val someIntegers = env.generateSequence(0, 10)
    val iteratedIntegers = someIntegers.iterate(
      iteration => {
      val minusOne = iteration.map( v => v - 1)
      (minusOne.filter (_ > 5), minusOne.filter(_ < 5))
      }
    )
    val result = DataStreamUtils.collect(iteratedIntegers.javaStream).asScala
    result.foreach(str => println("result--------:" + str))
  }
}
