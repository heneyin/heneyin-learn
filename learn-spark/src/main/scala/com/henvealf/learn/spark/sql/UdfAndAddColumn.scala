package com.henvealf.learn.spark.sql

import org.apache.spark.sql.SparkSession
import org.apache.spark.{SparkConf, SparkContext}
/**
  * <p>
  *  直接操作 DataFrame，在通过复杂计算后添加新列。
  * <p>
  *
  * @author hongliang.yin/Henvealf on 2019-05-21
  */
object UdfAndAddColumn {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf()
    conf.setMaster("local[1]")

    val spark = SparkSession.builder().config(conf)
      .appName(this.getClass.getSimpleName)
      .getOrCreate()

    // 注意这里的 spark 为前面的变量名。
    // 可以使用 $"colName"
    import spark.implicits._

    /// add a column
    // 这里引入的 udf 可以定义udf函数
    import org.apache.spark.sql.functions._

    val df = spark.read.json("learn-spark/src/main/resources/testData.txt")

    df.show(3)


    val myUdf = udf((age: Int) => age + 1)
    // 使用时，使用 df("colName") 或者 $"colName" 得到所应用的 udf 的列。
    val afterAddDf = df.withColumn("ageInc1", myUdf(df("age")))
        .withColumn("age", myUdf($"age"))

    afterAddDf.show(3)

    spark.close()

  }

}
