package com.henvealf.learn.scala.collect

/**
  *
  * @author hongliang.yin/Henvealf on 2018/4/9
  */
object matchList {

  def main(args: Array[String]): Unit = {
    val testList = List("a", "b", "c", "d", "e", "f")
    val singleList = List("4")
    val result = singleList match {
      case Nil => 0
      case List(x) => x
      case x :: hehe => hehe.length
      case _ :: rest => 1
    }

    print(result)

  }
}
