package com.henvealf.learn.flink.graph

import org.apache.flink.graph.{Edge, EdgeDirection, Vertex}
import org.apache.flink.graph.scala.{EdgesFunctionWithVertexValue, Graph}
import org.apache.flink.api.scala._
import org.apache.flink.util.Collector

/**
  * 创建图。
  *
  * @author hongliang.yin/Henvealf  
  * @date 2019-10-01
  */
object Create extends App {
  val env = ExecutionEnvironment.getExecutionEnvironment

  val vertices  = List(
    new Vertex("eid1", VertexValue(("ip1", 1000L))),
    new Vertex("eid2", VertexValue(("ip2", 1000L))),
    new Vertex("eid3", VertexValue(("ip3", 1000L))),
    new Vertex("eid4", VertexValue(("ip4", 1000L)))
  )

  val edges = List(
    new Edge("eid1", "eid2", EdgeValue(List("id1"), 1000L, 2000L, "scan", 10, "baidu.com", 80, "http")),
    new Edge("eid1", "eid2", EdgeValue(List("id2"), 1000L, 2001L, "scan", 11, "baidu.com", 80, "http")),
    new Edge("eid1", "eid2", EdgeValue(List("id3"), 1001L, 2002L, "scan", 44, "baidu.com", 80, "http")),
    new Edge("eid2", "eid3", EdgeValue(List("id4"), 2005L, 4000L, "scan", 33, "baidu.com", 80, "http")),
    new Edge("eid2", "eid4", EdgeValue(List("id5"), 2300L, 4100L, "scan", 23, "baidu.com", 80, "http")),
    new Edge("eid4", "eid3", EdgeValue(List("id6"), 4110L, 5000L, "scan", 56, "baidu.com", 80, "http"))
  )

  val graph = Graph.fromCollection(vertices, edges, env)

  // merge
  // merge source target 相同，type 相同，时间上临近的 边。
//  graph.groupReduceOnEdges(new EdgesFunctionWithVertexValue() {
//    override def iterateEdges(v: Vertex[Nothing, Nothing], edges: Iterable[Edge[Nothing, Nothing]], out: Collector[Nothing]): Unit = {
//    }
//  },EdgeDirection.OUT)
}

/**
  * 边的值
  * @param startTime
  * @param endTime
  * @param eventType
  * @param md
  * @param port
  * @param protocal
  */
case class EdgeValue(eventId: List[String], startTime: Long, endTime: Long, eventType: String, nLogs: Int, var md: String, var port: Int, var protocal:String )

/**
  * 定点的值，主要记录出现过的 ip。
  * @param ips
  */
case class VertexValue(ips: (String, Long))
