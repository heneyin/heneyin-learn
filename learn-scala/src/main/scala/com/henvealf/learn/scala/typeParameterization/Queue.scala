package com.henvealf.learn.scala.typeParameterization

/**
  * 19.2
  * @author hongliang.yin/Henvealf on 2018/5/9
  */
//class Queue[T] (private val leading:List[T], private val trailing: List[T]) {
//  private def mirror =
//    if (leading.isEmpty)
//      new Queue(trailing.reverse, Nil)
//    else
//      this
//
//  def head = mirror.leading.head
//
//  def tail = {
//    val q = mirror
//    new Queue(q.leading.tail, q.trailing)
//  }
//
//  def enqueue(x: T) = new Queue(leading, x :: trailing)
//}
//object Queue {
//  def appply[T](xs: T*) = new Queue[T](xs.toList, Nil)
//}

trait Queue[T] {
  def head: T
  def tail: Queue[T]
  def enqueue(x: T) : Queue[T]
}

object Queue {
  private class QueueImp[T](private val leading: List[T], private val trailing: List[T]) extends Queue[T] {
      private def mirror =
        if (leading.isEmpty)
          new QueueImp(trailing.reverse, Nil)
        else
          this

      def head = mirror.leading.head

      def tail = {
        val q = mirror
        new QueueImp(q.leading.tail, q.trailing)
      }

      def enqueue(x: T) = new QueueImp(leading, x :: trailing)
  }
}
