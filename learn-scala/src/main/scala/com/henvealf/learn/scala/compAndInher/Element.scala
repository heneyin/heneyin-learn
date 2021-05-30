package com.henvealf.learn.scala.compAndInher

/**
  * 一个抽象类
  * 10.1
  * @author hongliang.yin/Henvealf on 2018/3/20
  */
abstract class Element {
  // 抽象方法
  def contents: Array[String]
  def height: Int = contents.length
  def width:  Int = if (height == 0) 0 else contents(0).length

  def above(that: Element): Element =
    new ArrayElement(this.contents ++ that.contents)

  def beside(that: Element) : Element = {
    new ArrayElement(
      for ((line1, line2) <- this.contents zip that.contents)
        yield line1 + line2
    )
  }

  override def toString: String = contents mkString "\n"

  def demo: Unit
}
