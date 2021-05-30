package com.henvealf.learn.spark.sql

import org.apache.spark.{SparkConf, sql}
import org.apache.spark.sql.SparkSession

/**
  * <p>
  *  总结能用到的操作 df 的操作。
  * <p>
  *
  * @author hongliang.yin/Henvealf on 2019-06-03
  */
object DataFrameOperator {

  def main(args: Array[String]): Unit = {

    val session = getSession()
    import session.implicits._

    val df = session.createDataFrame(genData())

    /// ----------------------- map --------------------

    df.map(user => "User:" + user(0)).show()
    // df.map(user => "User:" + df("name")).show()
    df.map(user => "User:" + user.getAs[String]("name")).show()


    // filter
    df.filter($"name" === "kafka").show()
//    df.show(10)


  }


  def getSession(): SparkSession = {
    val conf = new SparkConf()
    conf.setMaster("local[1]")
    SparkSession.builder().config(conf).getOrCreate()
  }

  def getTestDf() = {
    val session = getSession()


  }

  def genData():List[User]= {
    List(User("xiaoming", 12, 34.4, List("swim", "go")),
         User("kafka", 24, 34.4, List("swim", "pingpang")) ,
         User("hadoop", 43, 34.4, List("swim", "ball")),
         User("yarn", 34, 564.4, List("sdf", "ball")))
  }

}

case class User(name: String, age: Int, grade: Double, favorite: List[String])