package com.henvealf.learn.scala

import java.math.BigInteger

import scala.collection.mutable.Set
import scala.collection.immutable.HashMap
import scala.collection.mutable

/**
  * 第一章中的简单代码
  * Created by Henvealf on 2017/6/5.
  * http://git.oschina.net/henvealf
  */
object OneLearn {

  def main(args: Array[String]): Unit = {
    // dui map 进行操作，使用 += 操作符。
    var capital = Map("US" -> "Washington","France" -> "Paris")
    capital += ("Japan" -> "Tokyo")
    println(capital("US"))

    // HashMap
    var hashMap = HashMap("nihao" -> "hha", "wodettain" -> "fafda");

    println(factorial(7))

    println(" test apply " + OneLearn(5))

    val list1 = List(1,2,3,4,5)
    val list2 = List(6,7,8,9,0)

    val list = list1 ::: list2

    println(list)

    list1.head
    val listadd = 1 :: list :: Nil

    println(listadd)

    println("-----")

    var jetSet = mutable.Set("Boeing", "Airbus")
    jetSet += "Lear"
    println(jetSet.contains("Cessna"))
    // jetSet contains("dasda")

    val treasureMap = mutable.Map[Int, String]()
    treasureMap += (1 -> "Go to island.")
    treasureMap += (2 -> "Find big X on ground.")
    treasureMap += (3 -> "Dig.")

    println(treasureMap(2))

    val acc = new ChecksumAccumulator
    val sca = new ChecksumAccumulator

    // acc.sum = 3

//    acc = new ChecksumAccumulator

    val a = Array(1,3,4)
//    a.apply()
    print(a(1))
  }

  def apply(number: Int): Int = number

  // 定义一个函数，指定数据类型为 BigInt， 防止溢出。
  def factorial(x: BigInt) : BigInt =
    if (x == 0) 1 else x * factorial(x - 1)

  // 使用 Java 的 BigInteger 类来完成
  def factorialWithJava(x: BigInteger) : BigInteger =
    if(x == BigInteger.ONE)
      BigInteger.ONE
    else
      x.multiply(factorialWithJava(x.subtract(BigInteger.ONE)))

}
