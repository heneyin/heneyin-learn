package com.henvealf.learn.scala.functionsAndColsures

import java.io.{File, PrintWriter}

/**
  * 科里化
  * 是指方法的构建闭包的多次重命名。
  * 9.4
  *
  * @author hongliang.yin/Henvealf on 2018/3/20
  */
object currying extends App{

  def cuuriedSum(x: Int)(y: Int) = x + y
  // 等价于
  def first(x: Int) = (y:Int) => x + y

  // 这里的下划线可以认为是实例化了一个函数，并且还包含了闭包变量 x。
  val pluOne = cuuriedSum(1)_
  println(pluOne(1))
  val pluTwo = cuuriedSum(2)_
  println(3)

  // 可以使用函数的定义来构建新的控制结构
  // 函数可以作为参数传入函数中。
  // 下面就构建了重复执行一个操作的结构
  def twice(op: Double => Double, x: Double): Double = op(op(x))
  println(twice(_ * 2, 2))

  // 我们可以将一些固定的操作分离出来。转换成一个方法来调用，而不是重复编写客户端代码。
  // 下面的小例子就分离了写文件中对写对象的打开关闭操作，向外放开了具体的文件写入工作。
  def witePrintWriter(file: File, op: PrintWriter => Unit) = {
    val writer = new PrintWriter(file)
    try {
      op(writer)
    }finally {
      writer.close()
    }
  }
  witePrintWriter(new File("testWrite"), writer => writer.println(new java.util.Date()))

  // 传参的时候，可以将小括号换为花括号
  println{1231}
  // 限制就是使用花括号，只能传入一个参数。
  //  val g = "Hello, world!"
  //  g{1,1};

  def witePrintWriterRe(file: File)(op: PrintWriter => Unit) = {
    val writer = new PrintWriter(file)
    try {
      op(writer)
    }finally {
      writer.close()
    }
  }

  // 提供花括号的原因就是，你可以将函数参数分离出成一个参数列表
  // 当你使用双参数列表的函数的时候，可以让代码看起来更加明了。
  witePrintWriterRe(new File("testWrite")) {
    writer => writer.println(new java.util.Date());
  }


}