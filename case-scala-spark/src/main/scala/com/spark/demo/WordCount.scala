package com.spark.demo

import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created with IntelliJ IDEA.
  * @desc
  * @author lgfcxx
  * @createtime 2017/9/21 下午2:15
  * @version 1.0
  * @see jdk 1.7
  **/
object WordCount {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf();
    sparkConf.setAppName("WordCount") //设置应用程序名字，在jod里面能查看
    sparkConf.setMaster("local") //设置应用的模式，local 为本地模式

    // SparkContext 里面包括 DAGScheduler TaskScheduler Lineage SchedulerBackend，等master注册信息
    val sc = new SparkContext(sparkConf);

    // 创建RDD 读取本地文件，并且创建一个Partitions
    val textFile = sc.textFile("text.txt", 1);

    //TransFormation级别处理，例如：filter ,Map 等高级函数
    // 把文件的每一行的字符拆分成单个的单词

    //把每一个行的字符，通过空格分割，之后在通过flatMap返回一个合并之后的大集合
    val words = textFile.flatMap(line => line.split(" "))

    //把拆分的单词，每个初始化为1 word => (word, 1)
    val pairs = words.map(word => (word, 1)); //这里返回元组

    //统计单词出现的次数，通过reduceByKey 来统计相同key,值得累加,这里包括local 和reducer 级别同时reduce
    //shuffle 之前的操作 local reduce，主要负责本地统计，并且把统计后的结果按照分区策略放到不同的文件 ，产生网络就会产生Stage
    val wordCounts = pairs.reduceByKey((x, y) => x + y); //可以简写成 pairs.reduceByKey(_+_) 通过占位符实现
    //前面的操作全部是第一个Stage完全是基于内存迭代 reduceByKey 本地reduce计算之后，同一个key放在同一个分片中，产生的内容放在磁盘中，共shuffle使用
    wordCounts.foreach(word => println(word._1, word._2))

    //把wordCounts 排序
    val clickCount = wordCounts.map(wc => (wc._2, wc._1))
    val sortKey = clickCount.sortByKey(false);
    sortKey.foreach(s=>println(s._1,s._2))
    clickCount.saveAsTextFile("");
    sc.stop();
  }
}
