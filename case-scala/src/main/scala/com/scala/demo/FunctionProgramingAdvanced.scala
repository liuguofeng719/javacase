package com.scala.demo

/**
  * Created with IntelliJ IDEA.
  *
  * @desc 函数式编程
  * @author lgfcxx
  * @createtime 2017/9/8 下午2:35
  * @version 1.0
  * @see jdk 1.7
  **/
object FunctionProgramingAdvanced {

  def main(args: Array[String]): Unit = {

    //把函数赋值给变量
    val f = hiBigData _
    f("Spark","武侯区");

    //匿名函数
    val b = (name: String) => println("hello=" + name);
    b("hello")

    //函数当着参数传递
    def getName(func: (String,String) => Unit, name: String,addr:String): Unit = {
      func(name,addr);
    }

    getName(f, "zhangsan","武侯区");

    //函数当着返回值
    def getString = (name: String) => println(name);
    getString("lisi");

    //闭包，Scala能实现闭包是因为Scala背后是类和对象，函数的参数都是类的成员
    def getResult(messge: String) = (name: String) => println(messge + " = " + name)

    getResult("Hello")("Java") // 柯里化

    Array(0 to 10: _*).map { (item: Int) => 2 * item }.foreach { x => println(x) }

  }

  def hiBigData(name: String,addr:String): Unit = {
    println("Hi: " + name)
  }

  def funVar = 12;

  //  def hiBigData(name: String, age: Int): Unit = {
  //    println("Hi: " + name)
  //  }

}
