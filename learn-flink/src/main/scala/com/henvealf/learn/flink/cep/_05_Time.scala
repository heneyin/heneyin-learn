package com.henvealf.learn.flink.cep

/**
  *
  * @author hongliang.yin/Heneyin
  * @date 2022/2/15
  */
class _05_Time {
  /**
    * 元素顺序是 CEP 处理的关键。
    * 当使用 event time，为了保证元素按照正确的顺序被处理, 一个输入的元素起初放在一个 buffer 中，buffer 中的元素会按照他们的时间戳升序排序，
    * 当一个水位线到达，buffer 中时间戳小于水位线的元素就会被处理。这意味着在水位线之间的元素会按照 event time 的顺序进行处理。
    *
    * 为了保证跨水位线的事件按照事件时间处理，Flink CEP库假定水位线一定是正确的，
    * 并且把时间戳小于最新水位线的事件看作是晚到的。 晚到的事件不会被处理。你也可以指定一个侧输出标志来收集比最新水位线晚到的事件
    *
    */
}
