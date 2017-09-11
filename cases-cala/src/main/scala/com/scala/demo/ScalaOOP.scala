package com.scala.demo

/**
  * Created with IntelliJ IDEA.
  *
  * @desc
  * @author lgfcxx
  * @createtime 2017/9/9 下午2:40
  * @version 1.0
  * @see jdk 1.7
  **/
//定义抽象属性，必须指定类型 val name:String;
//trait修饰的类接口，里面的方法是没有方法块，但是如果修饰里面类，里面的方法全部是有方法就当工具类，里面的方法编译都会添加到子类去，如果是类继承类，属性方法都在在父类
//实现接口关键字 with

//object ScalaOOP 是 class ScalaOOP 伴生对象，class ScalaOOP 是 object ScalaOOP 伴生类
// object ScalaOOP 是单例对象
class ScalaOOP(age: Int) {
  var name = "scala";

  def sayHello = {
    //伴生类调用 伴生对象 ，可以访问伴生对象所有方法和属性，包括私有的
    println("My name is " + name + "" + ScalaOOP.number + " age= " + age);
  }
}

object ScalaOOP {

  var number = 0;

  //静态方法
  def sum = {
    number += 1;
    println(number);
  }

  def main(args: Array[String]): Unit = {

    //    val scalaOOP = new ScalaOOP;
    val scalaOOP = ScalaOOP(50)  //实际是通过apply方法进行了对象实例化，避免了手动new对象
    scalaOOP.sayHello

    ScalaOOP.sum
    ScalaOOP.sum
    ScalaOOP.sum

  }

  //通过apply 方法 重载
  def apply(): ScalaOOP = {
    println("number=" + number);
    //    number += 1;
    new ScalaOOP(20)
  }

  def apply(age:Int): ScalaOOP = {
    println("number=" + number);
    //    number += 1;
    new ScalaOOP(age)
  }
}
