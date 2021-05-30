package com.henvealf.learn.scala

import scala.io.Source

/**
  * 对文件中的每行文本进行计数，并把计算后的数格式化的展示在每行最左
  * @author hongliang.yin/Henvealf 
  *
  */
object TextInFileSum extends App{

  /**
    * 得到每个字符串长度数的长度
    * @param s 字符串
    * @return
    */
  private def widthOfLength(s: String) = s.length.toString.length

  if (args.length > 0) {
    val lines = Source.fromFile(args(0)).getLines().toList

    val longestLine = lines.reduceLeft(
      (a, b) => if (a.length > b.length) a else b
    )

    val longestLineWidth = widthOfLength(longestLine)

    for (line <- lines) {
      val numSpaces = longestLineWidth - widthOfLength(line)
      val leftString = " " * numSpaces
      println(leftString + line.length + " | " + line)
    }
  } else {
    Console.err.println("Please enter filename")
  }

}