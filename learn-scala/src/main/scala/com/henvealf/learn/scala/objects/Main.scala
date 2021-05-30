package com.henvealf.learn.scala.objects

/**
  *
  * @author hongliang.yin/Henvealf
  */
object Main extends App{
//  val r = new Rational(1, 0)
//  println(r)

  // add
  val oneHalf = new Rational(1, 2)
  println("oneHalf: " + oneHalf)

  val twoThirds = new Rational(2,3)
  println("towHalf: " + twoThirds)

  val addResult = oneHalf + oneHalf
  println("addResult: " + addResult)

  val gcdRational = new Rational(66, 42)
  println("gcd rational: " + gcdRational)

  val mutilRational = oneHalf * twoThirds
  println("muti rational: " + mutilRational)

  val calculateOrder = oneHalf + oneHalf * mutilRational
  println("calculate order test: " + calculateOrder)

  // $ 是 scala 保留标识符，尽量不要使用
  //val $number = "what is your name"

  val testFactory = new Rational(1,1)
  println("testFactory: " + testFactory)

  // Thread.yield(); will product a error
  // Thread.`yield`()

  val overLoading = testFactory * 2
  println("overLoading: " + overLoading)

  // 放在 Rational 类中是不管用的
  implicit def intToRational(x: Int): Rational = new Rational(x)

  val testImplicit = 2 * overLoading
  println("test implicit: " + testImplicit)

}
