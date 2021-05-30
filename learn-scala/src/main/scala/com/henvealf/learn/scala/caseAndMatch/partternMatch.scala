package com.henvealf.learn.scala.caseAndMatch

import caseClass.{Expr, _}
/**
  * 15
  * @author hongliang.yin/Henvealf on 2018/5/1
  */
object partternMatch extends App {



  // 使用 case class 的 match
  def simplifyTop(expr: Expr): Expr = expr match {
    case UnOp("-", UnOp("-", e)) => e
    case BinOp("+", e, Number(0)) => e
    case BinOp("*", e, Number(1)) => e
    case _ => expr
  }

  val ex = simplifyTop(UnOp("-", UnOp("-", Var("1423"))))
  println(ex)

  // 下划线匹配
  // 下划线可以用来代替在 case 中不关心的对象。
  def wildcardsMatch(expr: Expr): Unit = expr match {
    case BinOp(_,_,_) => println(expr + " is a binary operation");
    case _ => println("It's something else")
  }

  wildcardsMatch(BinOp("+", Var("123"), Var("321")))

  // 常量匹配
  // 常量匹配仅仅会匹配常量他自己。 Any 用来当做一个常量
  def describe(x: Any) = x match {
    case 5 => "five"
    case true => "truth"
    case "hello" => "hi!"
    case Nil => "the empty list"
    case _ => "something else"
  }

  val res1 = describe(5)
  val res2 = describe(true)
  val res3 = describe("hahah")
  println(s"res1: $res1 \n res2: $res2 \n res3: $res3")

  // 变量匹配
  // 变量将会匹配任何的对象
  def variable(x: Any) = x match {
    case 0 => "zero"
    case somethingElse => "not zero " + somethingElse
  }

  val variableResult = variable("heihei")
  println("variable result: " + variableResult)

  import math.{E, Pi}
  // 常量 vs 变量
  // 当常量拥有一个标识的名字的时候，Scala 会认为使用大些开头的名字为常量，小写开头的为变量
  def variableAndConstants(x: Any) = x match {
    case Pi => "strage match? Pi = " + Pi
    case yuan => "a yuan variable: " + yuan
    case _ => "i dont konw"
  }

  val vs1 = variableAndConstants(Pi)
  val vs2 = variableAndConstants("haha")
  println(s"vs1: $vs1 \nvs2: $vs2")

  val warn = E match {
    case pi => "strange math? Pi" + pi
  }

  // 除了上面说的
  // 让 case 后的标识作为常量，有两点。
  // 1. 如果是某个对象的常量字段，使用变量前缀来指明的时候，就会匹配该该常量。
  // 2. 如果是一个local变量，可以使用 `` 来将其括起来
  def towTricks(x: Any) = x match {
    case caseClass.local => println("I AM a filed in object")
      // 只要与本地 local 变量的值相同，就会匹配上。
    case `local` => println("use ``" + local)
    case _ => println("i am not a constants")
  }

  towTricks(caseClass.local)
  val local = "213"
  towTricks(local)
  towTricks("213")
  towTricks("212")

  // 构造器匹配，前面说过了

  // 列表匹配
  // 你可以指定列表元素的个数
  val testList1 = List(0, 1, 2)
  // 三个元素，且第一元素是0
  testList1 match {
    case List(0, _, _) => println("found!")
    case _ =>
  }

  // 匹配任意长度的 list
  val testList2 = List(0,1,2,3,5)
  testList2 match {
    case List(_*) => println("found many element list")
    case _ => println("not found")
  }

  // Tuple 匹配
  val t = (1,2,3)
  t match {
    case (a,b,c) => println(a + b + c)
    case _ => println("tuple not found")
  }

  // 类型匹配
  // 简单的实现类型匹配
  val s:Any = "string"
  s match  {
    case x : String => println("a string")
    case m: Map[_, _] => println("a map, size is " + m.size)
    case _ => -1
  }
  // 另外一个匹配类型的方法
  if (s.isInstanceOf[String]) {
    val ss = s.asInstanceOf[String]
    ss.length
  }

  // 变量绑定
  // 使用 @ 来获取你模式中指定的部分变量。
  UnOp("abs", UnOp("abs", Var("one"))) match {
    case UnOp("abs", e @ UnOp("abs",_)) => println("variable binding result: " + e)
    case _ => println("nothing")
  }

  // 模式守卫。 使用if来进一步精确的控制你的模式变量
  def simplifyAdd(e : Expr) = e match {
    case BinOp("+", x, y) if x == y => BinOp("*", x ,Number(2))
    case _ => e
  }

  println("pattern guards: " + simplifyAdd(BinOp("+", Number(3), Number(3))))

  // 模式重叠，即模式递归自己

  def simplifyAll(expr: Expr): Expr = expr match {
    case UnOp("-", UnOp("-", e)) => simplifyAll(e)
    case BinOp("+", e, Number(0)) => simplifyAll(e)
    case BinOp("*", e, Number(1)) => simplifyAll(e)
    case UnOp(op, e) => UnOp(op, simplifyAll(e))
    case BinOp(op, l, r) => BinOp(op, simplifyAll(l), simplifyAll(r))
    case _ => expr
  }
  // 当使用模式重叠的时候，因为模式从上到下匹配9的，要注意避免一部分模式永远无法被使用的情况。
  def simplifyBad(expr: Expr): Expr = (expr: @unchecked)  match {
    case UnOp(op, e) => UnOp(op, simplifyBad(e))
    case UnOp("_", UnOp("-", e)) =>  e
  }

  // @unchecked
  def useUnchecked(e : Expr): String = (e: @unchecked) match {
    case Number(_) => "a number"
    case Var(_) => "a var"
  }

  // Option type

  def show(x: Option[String]) = x match {
    case Some(s) => s
    case None => "?"
  }

  val map = Map(1 -> "1", 2 -> "2")
  println(show(map get 1))
  println(show(map get 3))

  val second: PartialFunction[List[Int], Int] = {
    case x :: y :: _ => y
  }
  println(second.isDefinedAt(List(1,2,4)))
  println(second.isDefinedAt(List()))
  println(second.apply(List(1,2,4)))
//  println(second.applyOrElse(List(), ))
  for ((x, y) <- map) {
    println(s"$x haha $y")
  }
  println("---")
  val newMap = Map(1 -> Some("1"),2 -> Some("2"), 3 -> None)
  for ((x, Some(y)) <- newMap) {
    println(s"$x haha $y")
  }
}