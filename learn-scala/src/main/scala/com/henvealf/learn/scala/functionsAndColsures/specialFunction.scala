package com.henvealf.learn.scala.functionsAndColsures

/**
  * 特殊的方法
  * @author hongliang.yin/Henvealf on 2018/3/19
  */
object specialFunction extends App{
  // 多参数* 类似于 java 的 ...
  // args 内部实际上是一个 Array。
  def echo(args: String*) =
    for (arg <- args) println(arg)

  echo("hello", "world", "fuck")

  val argsArray = Array("1","2","4")
  // 但不能讲Array直接赋给args
  //  echo(argsArray)
  // 需要在array跟上 _* , 编译器会将 args 分解成一个个的元素给 echo，而不是一个 Array 类型的参数。
  echo(argsArray: _*)



  // 调用方法时指定参数名称
  def doYourBest(name: String, age: Int): Unit = {
    println(s"name: $name, age: $age.")
  }

  doYourBest(age = 123, name = "hene")

  // 为方法指定默认值
  def howAboutYou(name: String = "hehe", sex: String = "nan"): Unit = println(name + " " + sex)
  howAboutYou()
  howAboutYou("good")
  howAboutYou(sex = "nv")



}
