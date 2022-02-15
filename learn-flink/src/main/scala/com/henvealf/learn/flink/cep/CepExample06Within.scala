package com.henvealf.learn.flink.cep

import org.apache.flink.cep.scala.pattern.Pattern
import org.apache.flink.streaming.api.scala.DataStream
import org.apache.flink.streaming.api.windowing.time.Time

/**
  * 控制时间的 within.
  *
  * 先需要开启监听：
  * nc -l 8888
  * 启动该例子后，向 nc 中发送事件。
  *
  * 匹配： end 对应 time pattern
  * one two two end， 1 秒后输入 a
  * 不匹配：
  * one two two， 1 秒后输入 end a
  *
  * @author hongliang.yin/Heneyin
  * @date 2022/2/10
  */
object CepExample06Within extends CepAbstractExample {

  // one two two
  override def rawInputStream: DataStream[String] = {
    env.socketTextStream("127.0.0.1", 8888)
  }

  def pattern = Pattern.begin[String]("1").where(_ == "one")
    .next("2").where(_ == "two").times(2)
    // 在 1秒 内发生完。
    .next("time").within(Time.seconds(1))

}
