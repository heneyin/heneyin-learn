package com.henvealf.learn.scala.caseAndMatch

/**
  *
  * @author hongliang.yin/Henvealf on 2018/5/1
  */
object caseClass extends App{

  // 在该文件之外，就无法继承。防止额外的继承无法被覆盖到。
  // 并且会在编译期 match 没有覆盖所有子类的时候，发出警告。
  sealed abstract class Expr

  case class Var(name: String) extends Expr
  case class Number(num: Double) extends Expr
  case class UnOp(operator: String, arg: Expr) extends Expr
  case class BinOp(operator: String, left: Expr, right: Expr) extends Expr

  /** 使用 case 后，会为你的代码添加一些语法糖 **/
  // 1. 添加了一个使用类名定义的工厂方法
  val v  = Var("x")
  // 根据上面的类定义，你可以很方便的嵌套他们
  val op = BinOp("+", Number(1), v)

  // 2. case class 在构造器中的参数都会隐式的加上 val 修饰。
  v.name
  op.left
  // 这里显式的指定为var试一试
  case class Optionn(var maybe: String) extends Expr
  val optionn = Optionn("null")
  // 如果在参数上显式的指定 var， 会起作用。
  optionn.maybe = "1121"
  println("optionn maybe:" + optionn.maybe)

  // 3. 添加一些原始方法的实现： toString hashCode equals
  println(v)
  // equals 方法是比较参数中的对象相不相同。
  println("equals: " + (v == Var("x")))
  println("hash code" + v.hashCode())

  // 4. 编译器会在 case class 中添加一个 copy 方法，让你能够在copy的同时修改类的示例。
  // 通过指定参数的名字来指定要修改的参数。
  println("copy后的：" + op.copy(operator = "-"))

  val local = "local"



}
