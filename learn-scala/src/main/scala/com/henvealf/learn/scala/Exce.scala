package com.henvealf.learn.scala

/**
  * Created by Henvealf on 2017/6/9.
  * http://git.oschina.net/henvealf
  */
object Exce {

  def main(args: Array[String]) = {

    val nums = Array(1,2,3,4,5,65,6,34,22,33,6,22);

    val newNums =
      for (num <- nums if num > 30)
        yield num

    newNums.foreach(println)

    //throw new RuntimeException("n must  be even")
  }


}
