package com.spark.demo

import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created with IntelliJ IDEA.
  *
  * @desc
  * @author lgfcxx
  * @createtime 2017/9/25 下午4:50
  * @version 1.0
  * @see jdk 1.7
  **/
object TextLines {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf();
    conf.setAppName("TextLines")
    conf.setMaster("local")

    val sc = new SparkContext(conf);

    //获取文件行数相同的总数
    val lines = sc.textFile("textLines.txt");
    val tuple = lines.map(f => (f, 1));
    val totalLine = tuple.reduceByKey((x, y) => x + y);

    totalLine.collect().foreach(f => println(f._1, f._2))
  }
}
