package com.spark.demo

import org.apache.spark.{SparkConf, SparkContext}

import scala.collection.immutable.HashMap

/**
  * Created with IntelliJ IDEA.
  *
  * @desc
  * @author lgfcxx
  * @createtime 2017/10/8 上午9:25
  * @version 1.0
  * @see jdk 1.7
  **/
object SecondarySortApp {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("SecondarySortApp").setMaster("local");
    val sc = new SparkContext(conf)
    val lines = sc.textFile("SecondarySortApp.txt")
    val pairWithSortKey = lines.map(line => {
      val i = line.split(" ")
      (SecondarySortKey(i(0).toInt, i(1).toInt), line)
    })
    val sortKey = pairWithSortKey.sortByKey(false);
    val sortResult = sortKey.map(line => line._2);
    sortResult.collect().foreach(println(_))
  }
}
