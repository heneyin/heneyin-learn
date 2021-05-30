package com.henvealf.learn.scala

/**
  *
  * @author hongliang.yin/Henvealf on 2018/3/20
  */
object tryFinallytTest extends App{
  def notReturn: Int = try {
    println("I am in try.")
    return 12 / 0
  } catch {
    case e: Exception => {println("I am in catch"); 2}
  } finally {
    println("I am in finally")
    return 3
  }

  println(notReturn)
  println("--------------")
  def hasReturn: Int = try {
    println("I am in try")
    return 12 / 3
  } catch {
    case e: Exception => {println("I am in catch"); return 2}
  } finally {
    println("I am in finally")
     return 3 / 12
  }

  println(hasReturn)
}
