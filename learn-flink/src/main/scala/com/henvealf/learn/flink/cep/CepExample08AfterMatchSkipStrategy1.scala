package com.henvealf.learn.flink.cep

import org.apache.flink.cep.nfa.aftermatch.{AfterMatchSkipStrategy, NoSkipStrategy}
import org.apache.flink.cep.scala.pattern.Pattern

/**
  * AfterMatchSkipStrategy - skipToNext
  * 丢弃以相同事件开始的所有部分匹配。
  *
  * 当前理解的意思是，第一部分模式匹配上的事件序列如果之前出现过，则会被忽略。
  *
  * @author hongliang.yin/Heneyin
  * @date 2022/2/10
  */
object CepExample08AfterMatchSkipStrategy1 extends CepAbstractExample {

  override def inputText: String = "a1 a2 b1 b2 b3 1"

  /**
      result{a=[a1, a2], b=[b1]}
      result{a=[a1, a2], b=[b1, b2]}
      result{a=[a1, a2], b=[b1, b2, b3]}
    */
  // override def skipStrategy = AfterMatchSkipStrategy.noSkip()

  /**
    *  第一部分为 a1 a2 的结果都会被忽略
    * result{a=[a1, a2], b=[b1]}
    */
  override def skipStrategy = AfterMatchSkipStrategy.skipToNext()

  def pattern = Pattern
    .begin[String]("a", skipStrategy).where(_ startsWith "a").times(2)
    .next("b").where(_ startsWith "b").oneOrMore

}
