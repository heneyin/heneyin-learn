package com.henvealf.learn.scala

/**
  * Created by Henvealf on 2017/6/6.
  * http://git.oschina.net/henvealf
  */
object While {
  def main(args: Array[String]): Unit = {
    var i = 0
    while (i < args.length) {
      println(args(i))
      i += 1
    }

    var a = Array[String]("one", "tow", "three")
    a.foreach(x => println(x))

    // 更简化版本

    a.foreach(println)

    for(i <- 0 to 2)
      println(a(i))
  }
}