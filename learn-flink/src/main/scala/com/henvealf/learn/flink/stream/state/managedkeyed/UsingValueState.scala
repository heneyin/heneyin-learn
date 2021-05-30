package com.henvealf.learn.flink.stream.state.managedkeyed

import org.apache.flink.api.common.functions.RichFlatMapFunction
import org.apache.flink.api.common.state.{ValueState, ValueStateDescriptor}
import org.apache.flink.api.scala._
import org.apache.flink.configuration.Configuration
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.util.Collector

/**
  * 学习使用 ManagedKeyedState 中的 ValueState
  *
  * @author hongliang.yin/Henvealf  
  * @date 2019-09-25
  */
object UsingValueState{

  def main(args: Array[String]): Unit = {
    val env = StreamExecutionEnvironment.getExecutionEnvironment
    env.fromCollection(List(
      (1L, 2L),
      (1L, 3L),
      (1L, 4L),
      (1L, 5L),
      (2L, 1L),
      (2L, 2L),
      (2L, 4L)
    )).keyBy(_._1)
      .flatMap(new CountWindowAverage())
      .print()
    env.execute("UsingValueState")
  }

}


// ManagedKeyedState 需要在 RichFlatMapFunction 中使用。
// 按照计数窗口 计算 keyed 平均值, 这里就是说每 2 条数据记一次数。
class CountWindowAverage extends RichFlatMapFunction[(Long, Long), (Long, Long)] {

  private var sum: ValueState[(Long, Long)] = _

  override def flatMap(in: (Long, Long), collector: Collector[(Long, Long)]): Unit = {
    val tmpCurrentSum = sum.value()

    val currentSum = if (tmpCurrentSum == null) {
      (0L, 0L)
    } else {
      tmpCurrentSum
    }

    val newSum = (currentSum._1 + 1, currentSum._2 + in._2)

    sum.update(newSum)

    if (newSum._1 >= 2) {
      collector.collect((in._1, newSum._2 / newSum._1))
      sum.clear()
    }
  }

  override def open(parameters: Configuration): Unit = {
    sum = getRuntimeContext.getState(
      new ValueStateDescriptor[(Long, Long)]("average", createTypeInformation[(Long, Long)])
    )
  }

}
