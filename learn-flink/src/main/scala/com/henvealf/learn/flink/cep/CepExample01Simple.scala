package com.henvealf.learn.flink.cep

import org.apache.flink.cep.scala.pattern.Pattern
import org.apache.flink.streaming.api.scala._

/**
  * 易于理解的一个例子。
  *
  * 有 single 、looping pattern
  * next 邻接
  *
  * @author hongliang.yin/Heneyin
  * @date 2022/2/10
  */
object CepExample01Simple extends CepAbstractExample {

  // 1 用于触发
  override def inputText: String = "one two two 3a 3b 1"

  def pattern = Pattern.begin[String]("1").where(_ == "one")
    .next("2").where(_ == "two").times(2)
    .next("3").where(_.contains("3")).oneOrMore.where((value, ctx) => {
      val len = ctx.getEventsForPattern("3").map(_.length).sum + value.length
      len < 10
    })

}
