package com.henvealf.learn.flink.cep

import org.apache.flink.cep.scala.pattern.Pattern

/**
  * followedBy 邻接的例子
  * 类似于只需要最后满足的条件，跳过中间。
  * a b followedBy c
  * a b (----) c
  *
  * @author hongliang.yin/Heneyin
  * @date 2022/2/10
  */
object CepExample02FollowedBy extends CepAbstractExample {

  // 1 用于触发
  override def inputText: String = "one two two other followed1 followed2 1"

  def pattern = Pattern.begin[String]("1").where(_ == "one")
    .next("2").where(_ == "two").times(2)
    .followedBy("3").where(_.startsWith("followed"))

  // 因为出现了多个 followed 所以该序列不会匹配上。
  // override def inputText: String = "one two two other followed1 followed2 end 1"

}
