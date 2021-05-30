package com.henvealf.learn.scala.implicits

/**
  * @author hongliang.yin/Henvealf 
  * @date 2018/4/21
  */
class IAmFather(val money: Int) {

  def talk() = println(s"Stand here($money)!")

  def findSonLikeOrange(iSonLike: Int)(implicit orangeColorList: List[Int]): Int =
    orangeColorList.count(_ == iSonLike)

  def findBestOrange(implicit orangeColorList: List[Int]): Int =
    orangeColorList match {
      case Nil => throw new IllegalArgumentException("哇！橘子卖光了")
      case List(x) => x
      case x :: rest =>
        val max = findBestOrange
        if (x > max) x else max
    }
}

object IAmFather {
  implicit def originToFather(iBugOrange: IBugOrange): IAmFather = new IAmFather(iBugOrange.totalMoney)
}