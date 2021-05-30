package com.henvealf.learn.scala.implicits

/**
  *
  * @author hongliang.yin/Henvealf 
  * @date 2018/4/21
  */
class IBugOrange(val number: Int) {

  val per: Int = 1

  def totalMoney : Int = per * number

}
object IBugOrange {
//  implicit def originToFather(iBugOrange: IBugOrange): IAmFather = new IAmFather(iBugOrange.totalMoney)
}