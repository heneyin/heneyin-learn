package com.henvealf.learn.scala.implicits

/**
  *
  * @author hongliang.yin/Henvealf on 2018/4/21
  */
object bugFather extends App{
  implicit def originToFather(iBugOrange: IBugOrange): IAmFather = new IAmFather(iBugOrange.totalMoney)
  // 赋值
  val iAmFather: IAmFather = new IBugOrange(5)

  // 方法调用
  val iBugOrange: IBugOrange = new IBugOrange(6)
  iBugOrange.talk()
  new IBugOrange(6).talk()
  // 隐喻参数
  implicit val originList =  List(1,2,2,3,4,5,6,2,2,1,3,5,6,7)
//  implicit val originList1 =  List(1,2,2,3,4,5,6,2,2,1,3,5,6,7)
  println(iAmFather.findSonLikeOrange(3))

  implicitly[List[Int]].length
  implicitly[IBugOrange => IAmFather]
  //
//  var iBugOrange = new IBugOrange(4)
//  var iAmFather = new IAmFather(5)
  println(iBugOrange.totalMoney)
//  iAmFather = iBugOrange
  iBugOrange.talk()

  implicit class IAmFatherPlus(val iAmFather: IAmFather) {
    def fly() = {
      print("I will fly!")
      iAmFather.talk()
    }
  }
  new IAmFather(100).fly()
}
