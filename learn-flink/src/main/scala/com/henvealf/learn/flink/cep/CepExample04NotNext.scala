package com.henvealf.learn.flink.cep

import org.apache.flink.cep.scala.pattern.Pattern

/**
  * notNext 邻接的例子, 下一个不为，且 notNext 条件不会展示到匹配结果中。
  *
  * @author hongliang.yin/Heneyin
  * @date 2022/2/10
  */
object CepExample04NotNext extends CepAbstractExample {

  // 1 用于触发
  override def inputText: String = "one two two notThree 1"
  // override def inputText: String = "one two two three 1"

  def pattern = Pattern.begin[String]("1").where(_ == "one")
    .next("2").where(_ == "two").times(2)
    .notNext("3").where(_ == "three")

}
