package com.henvealf.learn.flink.cep

import org.apache.flink.cep.scala.pattern.Pattern

/**
  * notFollowedBy 邻接的例子。如果不想一个特定事件发生在两个事件之间的任何地方。
  *
  * 测试后得到以下结论，非官方文档描述。
  * 如果再接 next，则表示该 next 与上一个 pattern 之间不能有任何事件。
  * 如果再接 followedBy， 则表示该 followedBy 与 上一个 pattern 之间不能有 notFollowedBy 描述的事件。
  *
  * @author hongliang.yin/Heneyin
  * @date 2022/2/10
  */
object CepExample05NotFollowedBy extends CepAbstractExample {

  // ============
  // 接 next
  //
  // one two two four 1          匹配
  // one two two qweq four 1     不匹配
  // one two two three four 1    不匹配
  // ============
  override def inputText: String = "one two two three four 1"
  def pattern = Pattern.begin[String]("1").where(_ == "one")
    .next("2").where(_ == "two").times(2)
    .notFollowedBy("3").where(_ == "three")
    .next("4").where(_ == "four")

  // ============
  // 接 followedBy
  // one two two qeqw four 1     匹配
  // one two two three four 1    不匹配
  //
  // 1 用于触发
  // ============
//  override def inputText: String = "one two two three four 1"
//  def pattern = Pattern.begin[String]("1").where(_ == "one")
//    .next("2").where(_ == "two").times(2)
//    .notFollowedBy("3").where(_ == "three")
//    .followedBy("4").where(_ == "four")

}
