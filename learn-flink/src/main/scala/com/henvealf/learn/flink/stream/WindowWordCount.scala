package com.henvealf.learn.flink.stream

// 不 import _ 会抛出类型异常。
import org.apache.flink.streaming.api.TimeCharacteristic
import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.api.windowing.assigners.TumblingEventTimeWindows
import org.apache.flink.streaming.api.windowing.time.Time

/**
  * 窗口的 word count 数据源是 socket，sink 是标准输出
  *
  * nc -lk 4567
  *
  * ip for docker: host.docker.internal
  *
  * @author hongliang.yin/Henvealf  
  * @date 2019-09-19
  */
object WindowWordCount {
  def main(args: Array[String]): Unit = {
    if (args.length !=2 ) {
      print("need hostname and port")
      System.exit(1);
    }
    val hostname: String = args(0)
    val port: Int = args(1).toInt

    val env = StreamExecutionEnvironment.getExecutionEnvironment

    val text = env.socketTextStream(hostname, port)
      .assignAscendingTimestamps((_) => System.currentTimeMillis())

    val count = text.flatMap(text => text.split("\\W+")).filter(_.nonEmpty)
      .map((_, 1))
      .keyBy((t) => t._1)
      .window(TumblingEventTimeWindows.of(Time.seconds(5)))
      .sum(1)
      .name("word count")

    count.print()
    env.execute("First streaming word count job")
  }
}
