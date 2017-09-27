package com.spark.demo

import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created with IntelliJ IDEA.
  * @desc
  * @author lgfcxx
  * @createtime 2017/9/24 下午2:05
  * @version 1.0
  * @see jdk 1.7
  **/
object RDDBasedCollections {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf();
    conf.setAppName("Collections")
    conf.setMaster("local")
    //这里匹配是单机，如果任务失败不会重试，如果想模拟分布式，使用多线程，local[1] 到local[*] 还有并行度local[1,2]
    //RDD源代码 createTaskScheduler 源代码

    val sc = new SparkContext(conf);
    var numbers = 1 to 100;
    val rdd = sc.parallelize(numbers);
    //第一个参数，数据，第一个参数，并行度，并行度的计算（2-4) * core 32核 64 - 128 个线程
    val sum = rdd.reduce(_ + _)
    println("1+2+3" + ".... 99 + 100 = " + sum)
  }
}
