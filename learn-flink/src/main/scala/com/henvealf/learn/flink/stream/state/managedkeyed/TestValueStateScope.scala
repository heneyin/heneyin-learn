package com.henvealf.learn.flink.stream.state.managedkeyed

import org.apache.flink.api.common.functions.RichMapFunction
import org.apache.flink.api.common.state.{ValueState, ValueStateDescriptor}
import org.apache.flink.api.scala._
import org.apache.flink.configuration.Configuration
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment

/**
  * 学习使用 ManagedKeyedState 中的 ValueState
  * 测试 managered state 的作用域。
  * 结论是作用域只在函数中。
  * @author hongliang.yin/Henvealf  
  * @date 2019-09-25
  */
object TestValueStateScope{

  def main(args: Array[String]): Unit = {
    val env = StreamExecutionEnvironment.getExecutionEnvironment
    val fun = new AddValue()

    env.fromCollection(List(
      (1L, 2L),
      (1L, 3L),
      (1L, 4L),
      (1L, 5L),
      (2L, 1L),
      (2L, 2L),
      (2L, 4L)
    )).keyBy(_._1)
      .map(fun)
      .keyBy(_._1)
      .map(fun)
      .print()
    env.execute("TestValueStateScope")
  }

}


// ManagedKeyedState 需要在 RichFlatMapFunction 中使用。
class AddValue extends RichMapFunction[(Long, Long), (Long, Long)] {

  private var sum: ValueState[Long] = _

  override def map(in: (Long, Long)): (Long, Long) = {
    if (sum.value() == null) {
      sum.update(0)
    } else {
      sum.update(sum.value() + 1)
    }
    (in._1, sum.value())
  }

  override def open(parameters: Configuration): Unit = {
    sum = getRuntimeContext.getState(
      new ValueStateDescriptor[Long]("sum", createTypeInformation[ Long ])
    )
  }

}
