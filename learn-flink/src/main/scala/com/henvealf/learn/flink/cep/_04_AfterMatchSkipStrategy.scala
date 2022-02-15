package com.henvealf.learn.flink.cep

import org.apache.flink.cep.nfa.aftermatch.AfterMatchSkipStrategy
import org.apache.flink.cep.scala.pattern.Pattern

/**
  * 匹配后忽略规则。
  *
  * 一些 pattern 在匹配后会得到多个结果，可以通过 skip strategy 忽略一些不希望展示的结果。
  * - NO_SKIP 不忽略
  * - SKIP_TO_NEXT 忽略开始事件相同的匹配结果。
  *
  * @author hongliang.yin/Heneyin
  * @date 2022/2/14
  */
class _04_AfterMatchSkipStrategy {

  val skipStrategy: AfterMatchSkipStrategy = AfterMatchSkipStrategy.noSkip();

  val start = Pattern.begin[String]("start", skipStrategy)
}
