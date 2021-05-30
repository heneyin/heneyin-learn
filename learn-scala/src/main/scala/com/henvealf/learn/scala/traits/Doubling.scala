package com.henvealf.learn.scala.traits

/**
  *
  * @author hongliang.yin/Henvealf on 2018/5/1
  */
trait Doubling extends IntQueue{
  abstract override def put(x: Int): Unit = super.put(2 * x)
}
