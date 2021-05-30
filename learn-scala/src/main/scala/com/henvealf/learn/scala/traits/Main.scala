package com.henvealf.learn.scala.traits

/**
  *
  * @author hongliang.yin/Henvealf on 2018/5/1
  */
object Main extends App{
  class MyQueue extends BasicIntQueue with Doubling

  val queue = new MyQueue
  queue.put(10)
  println(queue.get())
}
