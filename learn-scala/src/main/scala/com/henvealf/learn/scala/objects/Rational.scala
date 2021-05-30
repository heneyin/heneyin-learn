package com.henvealf.learn.scala.objects

/**
  * 学习 Scala 的类，一个分数
  * 在方法签名后添加(参数)，即可提供一个客户端可使用的构造器，并且其中的参数（类参数）可以直接在类中使用。
  * 类参数为 val 的。
  * @author hongliang.yin/Henvealf on 2018/3/2
  */
class Rational(n: Int, d:Int) {
  // 提前检查参数
  require(d != 0,"Rational's denominator can't be zero.")
  private val g = gcd(n.abs, d.abs)
  val number: Int = n / g
  val denum: Int = d / g

  /**
    * 使用 override 重写父类方法，
    * 或者说方法签名与父类的相同时，必须使用 override 关键字
    * @return
    */
  override def toString: String = s"$number/$denum"

  /**
    * 辅助构造器
    * 辅助构造器中必须以主构造器或者其他在堆栈最底部调用了主构造器的辅助构造器。
    * 所以事实上，真正实体化类的是主构造器。
    * @param n
    * @return
    */
  def this(n: Int) = this(n, 1)

  // 类参数不能直接用对象获取。编译时候会报错，不过 idea 没有提前报错。
  // 要将类参数赋值给类中的字段。
//  def add(that: Rational) : Rational =
//    new Rational(n * that.d + that.n * d, d * that.d)

  def + (that: Rational): Rational =
    new Rational(
      number * that.denum + that.number * denum,
      denum * that.denum)
  def + (that: Int): Rational =
    new Rational(number + denum * that, denum)

  def - (that: Rational): Rational =
    new Rational(number * that.denum - that.number * denum,
      denum * that.denum)
  def - (that: Int): Rational =
    new Rational(number - that * denum, denum)

  def * (that: Rational) : Rational =
    new Rational(number * that.number, denum * that.denum)

  def * (that: Int): Rational =
    new Rational(number * that, denum)


  def / (that: Rational) : Rational =
    new Rational(number * that.denum, denum * that.number)

  def / (that: Int): Rational =
    new Rational(number, denum * that)

  def lessThan(that: Rational): Boolean =
    this.number * that.denum < this.denum * that.number

  def max(that: Rational): Rational =
    if (this.lessThan(that)) that else this

  private def gcd(a: Int, b: Int):Int =
    if (b == 0) a else gcd(b, a % b)
}

