package com.henvealf.learn.flink.sharing

import java.util.Properties

import org.apache.flink.api.common.serialization.SimpleStringSchema
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.streaming.api.windowing.assigners.TumblingProcessingTimeWindows
import org.apache.flink.streaming.api.windowing.time.Time
import org.apache.flink.streaming.connectors.kafka.{FlinkKafkaConsumer, FlinkKafkaProducer}

// 引入解决类型问题
import org.apache.flink.api.scala._
import collection.JavaConverters._

/**
  * Consumer command: kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic wc-sink
  * Arg: stream -> 流模式
  * @author hongliang.yin/Heneyin
  * @date 2021/5/30
  */
object WordCountExample {

  def main(args: Array[String]): Unit = {

    val isStreamSource =
      if (args.length == 0) {
        true
      } else {
        args(0) == "stream"
      }

    val env = StreamExecutionEnvironment.createLocalEnvironmentWithWebUI()
    env.setParallelism(2)
    // env.setRuntimeMode(RuntimeExecutionMode.BATCH)

    // -------------------
    // Kafka source
    // -------------------

    val streamOrBatch =
      if (isStreamSource) {
        val kafkaConsumer = new FlinkKafkaConsumer[String](List(KafkaConstant.SOURCE_TOPIC).asJava, new SimpleStringSchema(), kafkaConsumerProperties())
        // 总是从最后拿
        kafkaConsumer.setStartFromEarliest()

        env.addSource(kafkaConsumer)
      } else {
        env.readTextFile("learn-flink/src/main/resources/sharing/word-count.txt")
      }

    // -------------------
    // wc
    // -------------------

    val stream1 = streamOrBatch
        .flatMap(_.toLowerCase.split("\\W+")).name("splitToWord")
        .filter(_.nonEmpty)
        .map((_, 1))
        .keyBy(_._1)

    val resultStream =
      if (isStreamSource) {
        stream1.window(TumblingProcessingTimeWindows.of(Time.seconds(5)))
          .sum(1)
          .map(t => s"${t._1} -> ${t._2}")
      } else {
        stream1
          .sum(1)
          .map(t => s"${t._1} -> ${t._2}")
      }

    // -------------------
    // Kafka sink
    // -------------------
    val producer = new FlinkKafkaProducer[String](KafkaConstant.SINK_TOPIC, new SimpleStringSchema(), kafkaProducerProperties())
    resultStream.addSink(producer).name("kafka sink")

    println(env.getExecutionPlan)
    env.execute()
  }

  def kafkaConsumerProperties(): Properties = {
    val properties = new Properties()
    properties.put("bootstrap.servers", KafkaConstant.BOOTSTRAP_SERVER)
    properties.put("group.id", "sharing-001")
    properties.put("auto.offset.reset", "latest")
    properties;
  }

  def kafkaProducerProperties(): Properties = {
    val properties = new Properties()
    properties.put("bootstrap.servers", KafkaConstant.BOOTSTRAP_SERVER)
    properties
  }
}
