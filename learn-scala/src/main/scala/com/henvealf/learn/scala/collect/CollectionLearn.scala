package com.henvealf.learn.scala.collect

import scala.collection.immutable.{HashMap, HashSet}

// as java
import collection.JavaConverters._

/**
  * 各种集合学习
  *
  * @author hongliang.yin/Henvealf on 2018/2/26
  */
object CollectionLearn extends App{

  def arrayLearn(): Unit = {
    println("begin learn Array..")
    // 构造方法构造，
    val arrayNew = new Array[String](5)

    // 工厂方法构造
    val array = Array(1,2,3,4,5,6)

    // 获取指定元素
    val three = array(3)
    println(three)

    // 设置元素
    array(3) = 33

    // 获取指定元素
    val threeThree = array(3)
    println(threeThree)

    // 获取越界元素
//    val outOfBounds = array(10)
    print("end learn Array")
  }

  def listLearn(): Unit = {
    println("begin learn List")
    // 普通的直接构造list
    val list = List(1,2,3,4)
    println(list)

    // 拼接 List 与 List， 内部使用的 ListBuffer 实现的。
    val listAppend = list ::: List(5,6,7)
    println(listAppend)

    // 拼接 List 与 元素
    val listAppendEle = 0 :: list
    println(listAppendEle)

    val listAppendEleReverse = (5 :: list).reverse
    println(listAppendEleReverse)

    // 获取元素
    val ele = list(3)
    println(ele)

//    val outOfBounds = list(10)
    println("end learn list")
  }

  def learnTuple() :Unit = {
    println("begin learn Tuple..")
    val tuple2 = (1,"String")
    println(tuple2._1)
    println(tuple2._2)
    println("end learn Tuple..")
  }

  def learnSet():Unit = {
    var set = Set(1, 1, 2)
    println(set)
    val hashSet = HashSet(1,2,3,4,5)
    set += 4

    set(2)
  }

  def learnMap() : Unit = {
    var map = HashMap(1 -> "one", 2 -> "two")
    map += (3 -> "three")
    println(map(3))

    map.foreach (ele => {
      println(ele._1 + " " + ele._2)
    })

    map.foreach{
      case (k, v) => {
        println(k + " " + v)
      }
    }
  }

  arrayLearn()
  listLearn()
  learnTuple()
  learnSet()
  learnMap()
}
