package com.henvealf.learn.flink.stream

import java.util.Properties

import org.apache.commons.lang3.StringUtils
import org.apache.flink.api.common.serialization.SimpleStringSchema
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.streaming.api.windowing.assigners.{TumblingProcessingTimeWindows}
import org.apache.flink.streaming.api.windowing.time.Time
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer
// 引入防止类型错误
import org.apache.flink.streaming.api.scala._

/**
  * 测试读取 kafka， 执行windows操作。
  * @author hongliang.yin/Henvealf  
  * @date 2020/7/14
  */
object WindowWordCountFromKafka {

  def main(args: Array[String]): Unit = {

    /* 定义 kafka stream connector */
    // Flink kafka consumer 不依赖 Kafka 消费者提交的 offset 做容错。
    // 提交一般意味着暴露给其他进程作监控目的。
    // https://ci.apache.org/projects/flink/flink-docs-release-1.11/dev/connectors/kafka.html
    val properties = new Properties()
    properties.setProperty("bootstrap.servers", "localhost:9092")
    properties.setProperty("group.id", "testWindow")
    // partition and topic discovery
    properties.setProperty("flink.partition-discovery.interval-millis", "10000")
    val topic = "testWindows"

    val flinkKafkaConsumer =
      new FlinkKafkaConsumer[String](topic, new SimpleStringSchema(), properties);
    // 消费起始位置配置.
    flinkKafkaConsumer.setStartFromLatest()
    // flinkKafkaConsumer.setStartFromTimestamp(111)

    // 也可以为每个 partition 指定偏移量
    // flinkKafkaConsumer.setStartFromSpecificOffsets(specificStartOffsets)

    // 指定水印规则
    // flinkKafkaConsumer.assignTimestampsAndWatermarks(
    //   WatermarkStrategy.forBoundedOutOfOrderness(Duration.ofSeconds(20))
    // )

    /* 生成流 */
    val env = StreamExecutionEnvironment.getExecutionEnvironment
    env.setParallelism(2)
    env.enableCheckpointing(1000)

    val stream = env.addSource(flinkKafkaConsumer)
//    val stream = env.fromElements("a", "b", "c", "a", "b", "c", "a", "b", "c", "a", "b", "c")
//    stream.print()
//    stream
//      .filter((str: String) => StringUtils.isNotEmpty(str))
//      .map( _ => 1)
//      .windowAll(TumblingProcessingTimeWindows.of(Time.seconds(5)))
//      .reduce((a1, a2) => a1 + a2)
//      .print()

    stream
      .filter((str: String) => StringUtils.isNotEmpty(str))
      .map( a => (a, 1))
      .keyBy((kv) => kv._1)
      .window(TumblingProcessingTimeWindows.of(Time.seconds(1)))
      .reduce((a1, a2) => (a1._1, a1._2 + a2._2))
      .print()
    env.execute("测试")
    Thread.sleep(3000)
  }

}
