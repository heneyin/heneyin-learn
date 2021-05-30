package com.henvealf.learn.scala

/**
  * Created by Henvealf on 2017/6/7.
  * http://git.oschina.net/henvealf
  */
class ChecksumAccumulator {
  // 默认为 default
  private var sum = 0

  // 参数都是不可变得。
  def add(b : Byte):Unit = {
    sum += b
  }

  def checkNum() : Int = {
    return ~(sum & 0xFF) + 1
  }
}

object ChecksumAccumulator {
  val static = 1
  val name = "name"
}
