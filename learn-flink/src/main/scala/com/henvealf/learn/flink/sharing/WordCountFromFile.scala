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
  *
  * @author hongliang.yin/Heneyin
  * @date 2021/5/30
  */
object WordCountFromFile {

  def main(args: Array[String]): Unit = {

    val env = StreamExecutionEnvironment.createLocalEnvironmentWithWebUI()
    env.setParallelism(2)

    // -------------------
    // Kafka source
    // -------------------
    val consumer = new FlinkKafkaConsumer[String](List(KafkaConstant.SOURCE_TOPIC).asJava, new SimpleStringSchema(), kafkaConsumerProperties())
    // 总是从最后拿
    consumer.setStartFromEarliest()

    val stream = env.addSource(consumer)

    // -------------------
    // wc
    // -------------------

    val resultStream = stream
        .flatMap(_.toLowerCase.split("\\W+"))
        .filter(_.nonEmpty)
        .map((_, 1))
        .keyBy(_._1)
        .window(TumblingProcessingTimeWindows.of(Time.seconds(5)))
        .sum(1)
        .map(t => s"${t._1} -> ${t._2}")

    // -------------------
    // Kafka sink
    // -------------------
    val producer = new FlinkKafkaProducer[String](KafkaConstant.SINK_TOPIC, new SimpleStringSchema(), kafkaProducerProperties())
    resultStream.addSink(producer)

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
