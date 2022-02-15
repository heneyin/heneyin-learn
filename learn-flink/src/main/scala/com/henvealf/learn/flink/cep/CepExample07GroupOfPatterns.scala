package com.henvealf.learn.flink.cep

import org.apache.flink.cep.scala.pattern.Pattern
import org.apache.flink.streaming.api.scala.DataStream

/**
  * pattern 分组的例子。
  *
  * @author hongliang.yin/Heneyin
  * @date 2022/2/10
  */
object CepExample07GroupOfPatterns extends CepAbstractExample {

  override def inputText: String = "a b b a b b a b b 1"

  def pattern = Pattern.begin(
    Pattern.begin[String]("group1").where(_ == "a").next("b").times(2)
  ).times(3)

}
