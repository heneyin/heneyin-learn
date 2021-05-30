package com.henvealf.learn.spark.graphx.relationships

import org.apache.spark.SparkContext
import org.apache.spark.graphx.{Edge, Graph, VertexId}
import org.apache.spark.rdd.RDD

/**
  * 2. 官网中属性图的例子。
  * 图的顶点包含的属性为用户名以及用户职业
  * 图的边使用一个字符串来表示顶点用户之间的关系。
  *
  * @author hongliang.yin/Henvealf on 2018/10/13
  */
object Relationships {
  def main(args: Array[String]): Unit = {
    // 图的表示
    val userGraph: Graph[(String, String), String] = null

    // 使用 RDD 的集合构建一个图
    val sc: SparkContext = SparkContext.getOrCreate();

    // 为顶点创建一个 RDD
    // 顶点需要的则为顶点的ID以及顶点的属性。
    val users: RDD[(VertexId, (String, String))] =
      sc.parallelize(Array( (3L, ("rxin", "student")),
                            (7L, ("jgonzal", "postdoc")),
                            (5L, ("franklin", "prof")),
                            (2L, ("istoica", "prof") )))

    // 为边创建一个RDD
    // 一个边的创建需要一个来源顶点的ID,目标顶点的ID，以及边的属性
    val relationships: RDD[Edge[String]] =
      sc.parallelize(Array( Edge(3L, 7L, "collab"),
                            Edge(5L, 3L, "advisor"),
                            Edge(2L, 5L, "colleage"),
                            Edge(5L, 7L, "pi")))

    // 默认值需要与顶点的类型相同，这里即是一个二元组
    val defaultUser =  (" John Doe", "Missing" )

    // 构建一个最初的图
    val graph = Graph(users, relationships, defaultUser)

    // 结构分别得到顶点和边
    // 统计属性为 postdoc 的顶点的个数
    graph.vertices.filter{ case(id, (name, pos)) => pos =="postdoc" }.count()

    // 起始顶点ID大于目的顶点的ID的边的个数
    graph.edges.filter(e => e.srcId > e.dstId).count()

    // -------------------------------
    // 三元组视图，可以得到一个集合，集合中的每个元素表示为一条边，且除了包含该边的属性还包含边的两个顶点的属性。

    val facts:RDD[String] =
      graph.triplets.map( t =>
        t.srcAttr._1 + " is the " + t.attr + " of " + t.dstAttr._1)

    facts.collect.foreach(println(_))

    



  }
}
