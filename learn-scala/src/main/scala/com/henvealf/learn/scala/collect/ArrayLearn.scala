package com.henvealf.learn.scala.collect

/**
  *
  * @author hongliang.yin/Henvealf on 2018/3/21
  */
object ArrayLearn extends App{

  // zip 将两个数组拼装成一个tuple2列表
  val a = Array(1, 2, 3)
  val b = Array("a", "b")
  val aZipB = a zip b
  println(aZipB.mkString(","))

  println((a ++ b).mkString(","))

}
