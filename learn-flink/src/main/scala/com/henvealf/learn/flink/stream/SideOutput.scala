package com.henvealf.learn.flink.stream

import org.apache.flink.streaming.api.functions.ProcessFunction
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.streaming.api.scala._
import org.apache.flink.util.Collector

/**
  * 测试旁路输出
  * @author hongliang.yin/Heneyin
  * @date 2022/3/11
  */
object SideOutput extends App {

  val env = StreamExecutionEnvironment.createLocalEnvironmentWithWebUI()

  // val rawInput = env.fromCollection(Seq("a1", "a2", "a3", "b1", "b2"))
  val rawInput = env.socketTextStream("localhost", 8900)

  val tobOutputTag = OutputTag[String]("b-side")

  val mainStream = rawInput.process(new ProcessFunction[String, String] {
    override def processElement(ele: String,
                                context: ProcessFunction[String, String]#Context,
                                collector: Collector[String]): Unit = {
      if (ele.startsWith("a")) {
        collector.collect(ele)
      } else {
        context.output(tobOutputTag, ele)
      }
    }
  })

  val tobOutputStream = mainStream.getSideOutput(tobOutputTag)

  mainStream.print("main")
  tobOutputStream.print("tob")

  env.execute("sideOutputExample")
}
