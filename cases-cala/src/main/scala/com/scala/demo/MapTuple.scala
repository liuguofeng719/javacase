package com.scala.demo


import scala.collection.{SortedMap, mutable}

/**
  * Created with IntelliJ IDEA.
  *
  * @desc
  * @author lgfcxx
  * @createtime 2017/9/9 下午3:46
  * @version 1.0
  * @see jdk 1.7
  **/
object MapTuple {
  def main(args: Array[String]): Unit = {
    //map的2种方式，map对象的内容是不可变的
    val map = Map("spark" -> 6, "hadoop" -> 11);
    val map1 = Map(("spark", 6), ("hadoop", 11));
    val map2 = Map();
    for ((k, v) <- map) {
      println(k, v);
    }

    //可变Map
    val orderInfo = new mutable.HashMap[String, Int]()
    orderInfo("java") = 23
    orderInfo("c#") = 12

    for (elem <- orderInfo) {
      println(elem._1, elem._2);
    }

    //若果没有，就返回默认值
    println(orderInfo.getOrElse("java", 10));

    //可变
    val userInfo = scala.collection.mutable.Map[String, Int]("zhang" -> 4, "lisi" -> 5);
    userInfo += ("wangwu" -> 6, "ceshi" -> 8); //增加2个数据
    userInfo -= ("ceshi"); //删除数据

    for ((k, v) <- userInfo) {
      println(k + " " + v);
    }

    //只获取key
    val keySet = userInfo.keySet;
    for (key <- keySet) {
      println("key = " + key)
    }
    //只获取value
    val values = userInfo.values;
    for (value <- values) {
      println("value = " + value)
    }

    //通过yield 返回新的对象
    val newUserInfo = for ((k, v) <- userInfo) yield (k, v);

    //通过key 排序
    val sortInfo = SortedMap("zhansan" -> 12, "lisi" -> 13, "wangwu" -> 15);
    for ((name, age) <- sortInfo) {
      println(name + " =" + age);
    }

    //LinkedHashMap 按照数据顺序插入

    val linkHashMap = new scala.collection.mutable.LinkedHashMap[String,Int];


    //tuple 使用，tuple 可以返回不同类型的值，tuple 可以作为函数的返回值，在返回若干个值，例如SparkContext 为例
    //   Create and start the scheduler
    //   val (sched, ts) = SparkContext.createTaskScheduler(this, master, deployMode)
    //   _schedulerBackend = sched
    //   _taskScheduler = ts
    val tuple1 = ("spark",20,"I am","haha");
    println(tuple1._1,tuple1._2,tuple1._3,tuple1._4);
  }
}
