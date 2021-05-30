package com.henvealf.learn.scala.abstracts

/**
  *
  * Created by hongliang.yin/Henvealf on 2017/9/1.
  */
class Concrete extends Abstract{
  // An abstract type in Scala is always a member of some class or trait
  type T = String

  def transform(x: T): String = x + "hello"

  val init: String = "hello"

  var current: String = init

  override def notGood: String = "哈哈"
}
