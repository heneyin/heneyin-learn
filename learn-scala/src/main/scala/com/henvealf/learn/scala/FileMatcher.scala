package com.henvealf.learn.scala

/**
  * Created by Henvealf on 2017/6/12.
  * http://git.oschina.net/henvealf
  */
object FileMatcher {

  private def filesHere = (new java.io.File(".")).listFiles

  // matcher 是一个函数u，String 为参数，多个参数，就使用()括起来，
  // Boolean 是返回值
  private def filesMatching(matcher: String => Boolean) =
    for (file <- filesHere; if matcher(file.getName))
      yield file

  // 上层，调用 filesMatching， 传入方法。
  // 传入方法中的参数，其中的_使用 file.getName 来替换
  def filesEnding(query: String) =
    filesMatching(_.endsWith(query))

  def filesContaining(query: String) =
    filesMatching(_.contains(query))

  def filesRegex(query: String) =
    filesMatching(_.matches(query))

  def main(args: Array[String]): Unit = {
    val fileName = filesEnding(".scala")
    fileName.foreach(println)
  }
}
