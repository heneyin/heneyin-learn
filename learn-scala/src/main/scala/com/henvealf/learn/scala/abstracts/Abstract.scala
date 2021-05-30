package com.henvealf.learn.scala.abstracts

/**
  * 一个 trait, 一共包含四个抽象成员。
  * Created by hongliang.yin/Henvealf on 2017/9/1.
  */
trait Abstract {
  type T
  def transform(x : T) : T
  val init: T
  var current: T
  def notGood: T
}
