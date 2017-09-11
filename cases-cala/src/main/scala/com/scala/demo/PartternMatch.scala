package com.scala.demo

/**
  * Created with IntelliJ IDEA.
  *
  * @desc
  * @author lgfcxx
  * @createtime 2017/9/10 下午3:10
  * @version 1.0
  * @see jdk 1.7
  **/

//声明case class 类
class DataFrame

case class ComputeFrame(name: String, popurlar: Boolean) extends DataFrame

case class StoreageFrame(name: String, popurlar: Boolean) extends DataFrame

object PartternMatch {

  def main(args: Array[String]): Unit = {
    //    matchValue("Spark");
    //    matchValue("Hadoop");
    //    val test = "testString"
    //    matchVar(test)
    //    matchType(Array(1, 2))
    //    matchCollectionValue(Array("Scala"))
    //    matchCollectionValue(Array("Scala", "Java"))
    //    matchCollectionValue(Array("Scala", "Hadoop"))
    //    matchCaseClass(ComputeFrame("Spark", true))
        matchCaseClass(StoreageFrame("HDFS", true))
    matchOption("Spark", Map[String, String]("Spark" -> "Scala", "Spring" -> "Java"))
  }

  //值匹配
  def matchValue(value: String): Unit = {
    value match {
      case "Spark" => println("Spark")
      case "Scala" => println("scala")
      case _ if value == "Hadoop" => println("Hadoop")
      case _ => println("unKnown") //未知
    }
  }

  //变量匹配
  def matchVar(value: String): Unit = {
    value match {
      case _value => println(_value)
      case _ => println("unKnown")
    }
  }

  //类型匹配
  def matchType(t: Any): Unit = {
    t match {
      case i: Int => println("Integer")
      case d: Double => println("Double")
      case s: String => println("String")
      case arr: Array[Int] => println("Array[Int]")
      case _ => println("unKnown")
    }
  }

  //集合里面的值匹配
  def matchCollectionValue(msg: Array[String]): Unit = {
    msg match {
      case Array("Scala") => println("one")
      case Array("Scala", "Java") => println("two")
      case Array("Scala", _*) => println("many")
      case _ => println("unKnown")
    }
  }

  //类型匹配
  def matchCaseClass(clzz: DataFrame): Unit = {
    clzz match {
      case ComputeFrame(name, popurlar) => println("ComputeFrame: " + name + " = " + popurlar)
      case StoreageFrame(name, popurlar) => println("StoreageFrame: " + name + " = " + popurlar)
      case _ => println("type not found!!")
    }
  }


  def matchOption(key: String, values: Map[String, String]): Unit = {
    values.get(key) match {
      case Some(value) => println(value)
      case None => println("value not found!!")
    }
  }
}
