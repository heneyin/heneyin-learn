package com.henvealf.learn.flink.cep

import org.apache.flink.cep.scala.pattern.Pattern
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment

/**
  * https://nightlies.apache.org/flink/flink-docs-release-1.14/docs/libs/cep/#individual-patterns
  *
  * @author hongliang.yin/Heneyin
  * @date 2022/2/10
  */
class _01_IndividualPattern extends App {

  val env = StreamExecutionEnvironment.getExecutionEnvironment

  /*
    Pattern 分为 single 和 looping 两种。 single 接受一个 event， loop 能接受超过一个 event。
   */

  /*
    # Quantifiers
      可以指定 loop pattern 的量词。
  */

  // loop pattern 绝对的两个
  val pattern = Pattern.begin("start").times(2)

  // optional 表示可以为 0
  val pattern1 = Pattern.begin("start").times(2).optional

  // 一个或者多个
  val pattern2 = Pattern.begin("start").oneOrMore

  // 10 个到多个
  val pattern3 = Pattern.begin("start").timesOrMore(10)

  // 10 个到多个，贪婪模式，尽可能多的匹配
  val pattern4 = Pattern.begin("start").timesOrMore(10).greedy

  /*
    # Condition 条件
    Event 被接受的条件，可以是与常量比，也可以与历史比。

    - Iterative Conditions 即根据之前以及接受的事件来决定之后的事件是否被接受，或者根据事件子集的统计值。
    - Simple Condition 简单条件，接受或不接受事件。
   */

  // 迭代条件的例子
  Pattern.begin[String]("iterator")
    .oneOrMore
    .where((value, ctx) => {
      // 获取过去已经接受了的 event，并求和
      lazy val totalLength = ctx.getEventsForPattern("iterator").map(_.length).sum
      value.startsWith("foo") && totalLength + value.length > 5
    })

  // 简单条件的例子
  Pattern.begin[String]("simple").where(event => event.startsWith("foo"))

  // 使用类型作为条件
  Pattern.begin[String]("type").subtype(classOf[String])

  // 逻辑表达式, where 之间等同于 and 表达式
  Pattern.begin[String]("simple").where(event => event.startsWith("foo")).or(_.startsWith("eoo"))

  /*
    # Stop 条件, 终止条件
    除上面提到的，还有 until，直到遇见该条件。
  */
  Pattern.begin[String]("until").until(_ == "stop")


}
