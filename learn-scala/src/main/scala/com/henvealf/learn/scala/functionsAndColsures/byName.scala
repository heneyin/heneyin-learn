package com.henvealf.learn.scala.functionsAndColsures

import javax.sql.rowset.Predicate

/**
  * 9.5
  * @author hongliang.yin/Henvealf on 2018/3/20
  */
object byName extends App{
  /* by name 参数, 用于省略 () => expression 中的 () => ，简化客户端代码 */
  val isEnableAssert = true;

  // 使用正常的函数定义
  def simpleAssert(predicate: () => Boolean) = {
    if (isEnableAssert && !predicate())
      throw new AssertionError()
  }

  //  simpleAssert( 2 > 3) 会报错
  simpleAssert(() => 2 > 3)

  // 为了省略()=> 可以使用 by name 参数
  def byNameAssert(predicate: => Boolean) = {
    if (isEnableAssert && !predicate)
      throw new AssertionError()
  }

  byNameAssert(1 > 2)

  /*
  * 使用 by name 与 直接传入 boolean 变量的区别就是。 判定正反操作发生的时间不同， by name 是在函数内部开始调用参数函数的时候，
  * boolean 变量需要在函数执行之前将 boolean 值算出，这样在上面的例子中，无论 isEnableAssert 是 true 还是 false。都会进行比较
  *
  * */

}
