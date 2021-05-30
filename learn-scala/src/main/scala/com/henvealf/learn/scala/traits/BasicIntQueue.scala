package com.henvealf.learn.scala.traits

import scala.collection.mutable.ArrayBuffer

/**
  *
  * @author hongliang.yin/Henvealf on 2018/5/1
  *
  */
class BasicIntQueue extends IntQueue {
  private val buf = new ArrayBuffer[Int]()

  override def put(x: Int): Unit = buf += x

  override def get(): Int = buf.remove(0)
}
