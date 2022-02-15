package com.henvealf.learn.flink.cep

import org.apache.flink.cep.scala.pattern.Pattern

/**
  * followedByAny 邻接的例子
  *
  * 类似于分支结构, 也会忽略不支持的元素。
  *
  *        followedByAny
  * a -> b -------------> c1
  *        -------------> c2
  *
  * 匹配到的结果为
  * a b c1 与
  * a b c2
  * @author hongliang.yin/Heneyin
  * @date 2022/2/10
  */
object CepExample03FollowedByAny extends CepAbstractExample {

  // 1 用于触发
  override def inputText: String = "one two two tmp followed1 followed2 end1 end2 1 1"

  def pattern = Pattern.begin[String]("1").where(_ == "one")
    .next("2").where(_ == "two").times(2)
    .followedByAny("3").where(_.startsWith("followed"))
    .followedByAny("4").where(_.startsWith("end"))
    .next("5").where(_ == "1")


}
