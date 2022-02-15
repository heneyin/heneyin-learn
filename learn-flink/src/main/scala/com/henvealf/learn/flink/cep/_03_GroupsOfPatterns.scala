package com.henvealf.learn.flink.cep

import org.apache.flink.cep.scala.pattern.Pattern

/**
  * 对 Pattern 分组。
  * 将 Pattern 序列嵌套在各个邻接函数中。完成对逻辑的包装与嵌套
  *
  * 只有 begin, followedBy, followedByAny and next 支持。
  *
  * @author hongliang.yin/Heneyin
  * @date 2022/2/14
  */
class _03_GroupsOfPatterns {

  val start = Pattern.begin(
    Pattern.begin[String]("start").where(_ == "1").followedBy("middle").where(_ == "2")
  )

  val start1 = Pattern.begin(
    Pattern.begin[String]("start").where(_ == "1").followedBy("middle").where(_ == "2")
  ).times(2)   // 添加 loop pattern 控制。

}
