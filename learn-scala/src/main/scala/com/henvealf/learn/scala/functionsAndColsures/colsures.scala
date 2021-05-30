package com.henvealf.learn.scala.functionsAndColsures

/**
  * 闭包
  * @author hongliang.yin/Henvealf on 2018/3/19
  *
  */
object colsures extends App{

  val more = 10

  def addMore = (x: Int) => x + more

  println(addMore(1))

  // 构建函数内的闭包。函数会优先读取内部闭包的变量。
  def addMoreAndMore(more: Int): (Int) => Int = (x: Int) => x + more

  println(addMoreAndMore(1)(1))

  def inc100: (Int) => Int = addMoreAndMore(100)

  println(inc100(50))

  def add: Int = inc100(100)

  println(add)


}

