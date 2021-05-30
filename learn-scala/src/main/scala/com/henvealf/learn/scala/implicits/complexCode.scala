package com.henvealf.learn.scala.implicits

/**
  *
  * @author hongliang.yin/Henvealf 
  * @date 2018/4/19
  */
object complexCode {
  implicit def intToList(i: Int): List[Int] = List(i)
  def main(args: Array[String]): Unit = {
    implicitParams()
    println(1.getValue())
    implicitObject()
  }

  def implicitVal(): Unit = {
    val one: List[Int] = 1
    println(one)
  }

  def implicitMethod(): Unit = {
    val aList = 1 ::: 2 ::: 3 ::: 4
    println(aList)
  }

  def implicitParams(): Unit = {
    implicit val list:List[Int] = List(1, 2, 3, 4, 5, 6)
//    implicit val list1:List[Int] = List(2, 3, 4, 5, 6)
    println(containsList(1))
  }

  def containsList(x:Int)(implicit list: List[Int]): Boolean = {
    list.contains(x)
  }
  // implicit class
  implicit class SingleList(val x : Int) {
    private val list: List[Int] = List(x)

    def getValue(): Int = list.head
  }

  // 主动得到被implicit的对象。

  def implicitObject(): Unit = {
    implicit val list = List(1,2,3,4,5,6)
    val i = implicitly[List[Int]].head
    println(i)
  }

}
