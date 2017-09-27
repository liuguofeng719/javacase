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
object WordCountCluster {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf();
    sparkConf.setAppName("WordCount") //设置应用程序名字，在jod里面能查看
    // 这里可以通过spark-submit 手动指定
//    sparkConf.setMaster("local") //设置应用的模式，local 为本地模式

    // SparkContext 里面包括 DAGScheduler TaskScheduler Lineage SchedulerBacked，等master注册信息
    val sc = new SparkContext(sparkConf);

    // 创建RDD 读取本地文件，并且创建一个Partitions
//    val textFile = sc.textFile("text.txt", 1);
    //读取文件，并切分成
//    val textFile = sc.textFile("hdfs://SparkMaster:7077/user/root/order_finish");
    //默认会通过上下文去找
    val textFile = sc.textFile("/user/root/order_finish");

    //TransFormation级别处理，例如：filter ,Map 等高级函数
    // 把文件的每一行的字符拆分成单个的单词

    //把每一个行的字符，通过空格分割，之后在通过flatMap 合并成一行
    val words = textFile.flatMap(line => line.split(" "))

    //把拆分的单词，每个初始化为1 word => (word, 1)
    val pairs = words.map(word => (word, 1)); //这里返回元组

    //统计单词出现的次数，通过reduceByKey 来统计相同key,值得累加,这里包括local 和reducer 级别同时reduce
    val wordCounts = pairs.reduceByKey((x, y) => x + y); //可以简写成 pairs.reduceByKey(_+_) 通过占位符实现

    //如果在集群上面需要在Driver 上面打印，需要调用collect()
    wordCounts.collect().foreach(word => println(word._1, word._2))

    //把wordCounts 点击排序功能
    val clickCount = wordCounts.map(wc => (wc._2, wc._1))
    val sortKey = clickCount.sortByKey(false);//默认是升序，设置为false 降序
    sortKey.collect().foreach(s=>println(s._1,s._2))

    sc.stop();
  }
}
