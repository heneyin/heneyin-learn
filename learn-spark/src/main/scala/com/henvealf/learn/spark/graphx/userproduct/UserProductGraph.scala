package com.henvealf.learn.spark.graphx.userproduct

import org.apache.spark.graphx.Graph

// 1
// 如果同一个图的定点具有不同的类型，可以用继承的方式去完成定义。
class VertexProperty()
case class UserProperty(val name: String) extends VertexProperty
case class ProductProperty(val name: String, val price: Double) extends VertexProperty

/**
  *
  * @author hongliang.yin/Henvealf on 2018/10/13
  */
object UserProductGraph {

  def main(args: Array[String]): Unit = {
    // 第一个类型就为定点的属性，第二个为边的属性
    var graph: Graph[VertexProperty, String] = null
  }


}
