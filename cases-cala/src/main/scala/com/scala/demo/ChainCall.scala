package com.scala.demo

/**
  * Created with IntelliJ IDEA.
  *
  * @desc
  * @author lgfcxx
  * @createtime 2017/9/18 下午2:49
  * @version 1.0
  * @see jdk 1.7
  **/
class PersonType {
//  def sayHello(name: String): this.type = {
//    println("PersonType=" + name);
//    this
//  }
  def sayHello2(name: String)= {
    println("PersonType=" + name);
    this
  }
}

class Man extends PersonType {
  def myHello(name: String): this.type = {
    println("myHello=" + name)
    this
  }
}

class WoMan extends PersonType {
//  def woHello(name: String): this.type = {
//    println("WoMan=" + name)
//    this
//  }

  def woHello2(name: String) = {
    println("WoMan=" + name)
    this
  }
}


object ChainCall {
  def main(args: Array[String]): Unit = {
    val person = new PersonType
//    person.sayHello("yyy");

    val woman = new WoMan;
    woman.woHello2("WoMan").sayHello2("PersonType")
//    woman.woHello("111").sayHello("teste");
  }
}
