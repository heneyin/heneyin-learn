package com.henvealf.learn.flink.cep

import org.apache.flink.cep.functions.{PatternProcessFunction, TimedOutPartialMatchHandler}
import org.apache.flink.cep.nfa.aftermatch.AfterMatchSkipStrategy
import org.apache.flink.cep.scala.CEP
import org.apache.flink.cep.scala.pattern.Pattern
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.streaming.api.scala._
import org.apache.flink.util.Collector

import java.util

/**
  *
  * @author hongliang.yin/Heneyin
  * @date 2022/2/12
  */
abstract class CepAbstractExample extends App {

  def inputText: String = ""

  val env = StreamExecutionEnvironment.getExecutionEnvironment

  def rawInputStream = env.fromCollection(Seq(inputText))

  val inputTextStream = // env.socketTextStream("127.0.0.1", 9800)
    rawInputStream
      .assignAscendingTimestamps(_ => System.currentTimeMillis())
      .filter(_ != "")
      .flatMap(_.split(" "))

  inputTextStream.print("input")

  def skipStrategy: AfterMatchSkipStrategy = AfterMatchSkipStrategy.noSkip()

  def pattern: Pattern[String, String]

  // 事件时间戳相同时排序用的比较器。
  // var comparator : EventComparator[String] =

  val patternStream = CEP.pattern(inputTextStream, pattern)

  // 应用计算
  val result: DataStream[String] = patternStream.process(
    new PatternProcessFunction[String, String]() {
      /**
        *
        * @param matched key：pattern name， value：匹配到的 event，按照时间戳排序。
        * @param context
        * @param collector
        */
      override def processMatch(matched: util.Map[String, util.List[String]],
                                context: PatternProcessFunction.Context,
                                collector: Collector[String]): Unit = {
        println("result" + matched)
        collector.collect(".")
      }
    }
  )

  env.execute(getClass.getName)
}

/**
  * 当使用 within 时，使用 TimedOutPartialMatchHandler 处理超时的部分模式。
  */
class MyPatternProcessFunction extends PatternProcessFunction[String, String] with TimedOutPartialMatchHandler[String] {
  override def processMatch(map: util.Map[String, util.List[String]],
                            context: PatternProcessFunction.Context,
                            collector: Collector[String]): Unit = {
  }

  /**
    * 处理超时事件的函数。
    */
  override def processTimedOutMatch(map: util.Map[String, util.List[String]],
                                    context: PatternProcessFunction.Context): Unit = {

  }
}
