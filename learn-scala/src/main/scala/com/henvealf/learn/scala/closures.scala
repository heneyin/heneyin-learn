package com.henvealf.learn.scala

/**
  * Created by Henvealf on 2017/6/12.
  */
object closures {

  def main(args: Array[String]): Unit = {

    var more = 10
    val fun = (x: Int) => x + more
    println(fun(1))
    more = 99
    println(fun(1))
//    val list = List(1,33,3,5,6,6)
//    println(list.drop(1))

    val numList = List(1,4,3,2,1,4,5,6)
    var sum = 0
    numList.foreach(sum += _)
    println("sum: " + sum)

    def inc(incNum: Int) = (x: Int) => x + incNum
    val inc2 = inc(2)
    val inc100 = inc(100)
    println("inc2: " + inc2(1))
    println("inc100: " + inc100(3))

    echo("12","123","haha","hello")

    val arr = Array("spark","is","spark")
    echo(arr: _*)
  }

  def echo(strs: String*) = {
    strs.foreach(println)
  }

}
