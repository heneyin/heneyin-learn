package com.henvealf.learn.flink.cep

import org.apache.flink.cep.scala.pattern.Pattern

/**
  * 全世界 Pattern 组合起来。组合到一个完整的 Pattern 序列。
  * https://nightlies.apache.org/flink/flink-docs-release-1.14/zh/docs/libs/cep/#%E7%BB%84%E5%90%88%E6%A8%A1%E5%BC%8F
  *
  * @author hongliang.yin/Heneyin
  * @date 2022/2/10
  */
class _02_CombiningPatterns extends App {

  /**
    * contiguity conditions，接续条件，支持三种：
    * 1. Strict Contiguity 严格接续：期望所有匹配到事件一个接着一个出现，事件中间不存在没有匹配上的。
    * 2. Relaxed Contiguity 松弛接续：忽略匹配到的事件之间的未匹配到事件。
    * 3. Non-Deterministic Relaxed Contiguity，非确定性的松弛接续：进一步放宽了松弛接续，允许忽略掉一些匹配事件的额外匹配。
    *
    * 分别对应
    * 1. next
    * 2. followedBy()，指定松散连续，
    * 3. followedByAny()，指定不确定的松散连续。
    * 或者
    * 1. notNext()，如果不想后面直接连着一个特定事件
    * 2. notFollowedBy()，如果不想一个特定事件发生在两个事件之间的任何地方。
    *
    * 对于 loop 模式，例如 oneOrMore 或者 times，可以设置邻接规则，consecutive（只展示连续） 或者 allowCombinations （展示所有组合）
   q*/

  val patternStart = Pattern.begin[String]("start")

  patternStart.next("middle").where(_.length == 10)

}
