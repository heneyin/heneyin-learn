package com.henvealf.learn.scala

/**
  * Created by Henvealf on 2017/6/12.
  * http://git.oschina.net/henvealf
  */
object FileMatherCopy {

  private val filesHere = new java.io.File(".").listFiles()

  def matchFile(macther: String => Boolean) =
    for(file <- filesHere; if macther(file.getName))
      yield file

  def macthEnding(query: String) =
    matchFile(_.endsWith(query))

  def main(args: Array[String]): Unit = {
    val files = macthEnding(".iml")
    files.foreach(println _)
  }


}
