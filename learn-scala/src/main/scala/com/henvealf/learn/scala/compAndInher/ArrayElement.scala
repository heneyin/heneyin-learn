package com.henvealf.learn.scala.compAndInher

/**
  * 10.2
  * @author hongliang.yin/Henvealf 
  * @date 2018/3/20
  */
class ArrayElement(override val contents: Array[String]) extends Element{

  val same = 1
  //  def same(s: Int) = 1
  // 10.10 修饰 final 意味着该方法无法被子类重写
  // final 修饰在类上就说明该类无法再被继承
  final override def demo: Unit = println("I am a ArrayElement")

}