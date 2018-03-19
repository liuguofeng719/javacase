package com.spark.demo

import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created with IntelliJ IDEA.
  *
  * @desc TransFormations 实战
  * @author lgfcxx
  * @createtime 2017/9/27 上午11:24
  * @version 1.0
  * @see jdk 1.7
  **/
object TransFormations {

  def main(args: Array[String]): Unit = {
    val sc = sparkContext("TransFormations")
//    mapTransFormation(sc);
//    filterTransFormation(sc);
//    flatMapTransFormation(sc);
//    groupByKeyTransFormation(sc);
//    reduceByKeyTransFormation(sc);
    joinTransFormation(sc);
//    coGroupTransFormation(sc);
    sc.stop() //释放sparkContext Driver创建的对象资源
  }

  def sparkContext(name: String) = {
    val conf = new SparkConf();
    conf.setMaster("local");
    conf.setAppName(name)

    //创建一个RDD唯一的入口，也是Driver 的灵魂
    val sc = new SparkContext(conf);
    sc
  }

  /**
    * (4,CompactBuffer(Tachyon),CompactBuffer())
    * (1,CompactBuffer(Spark),CompactBuffer(100, 101))
    * (3,CompactBuffer(Java),CompactBuffer(80))
    * (2,CompactBuffer(Hadoop),CompactBuffer(65, 90, 70))
    * coGroup 必须是key Value
    * @param sc
    */
  def coGroupTransFormation(sc: SparkContext) = {

    val names = Array((1, "Spark"), (2, "Hadoop"), (3, "Java"), (4, "Tachyon"))
    val scores = Array((1, 100), (2, 65), (3, 80), (1, 101), (2, 90), (2, 70))
    val nameDatas = sc.parallelize(names);
    val scoreDatas = sc.parallelize(scores);
    val coDatas = nameDatas.cogroup(scoreDatas);
    coDatas.foreach(f => println(f._1, f._2._1, f._2._2))

  }

  //通过key 学生和成绩关联
  def joinTransFormation(sc: SparkContext) = {
    val student = Array(Tuple2(1, "Spark"), Tuple2(2, "Hadoop"), Tuple2(3, "Tachyon"))
    val score = Array(Tuple2(1, "100"), Tuple2(2, "65"), Tuple2(3, "85"))
    var stu = sc.parallelize(student);
    var core = sc.parallelize(score);
    val stuScore = stu.join(core);
    stuScore.collect().foreach(f => println(f._1, f._2))
  }

  def groupByKeyTransFormation(sc: SparkContext) = {
    val datas = Array((100, "JAVA"), (100, "SPARK"), (80, "hadoop"), (80, "HBASE"), (70, "KAFKA"));
    val datas1 = Array(new Tuple2(100, "JAVA"), new Tuple2(100, "SPARK"), new Tuple2(80, "hadoop"), new Tuple2(80, "HBASE"), new Tuple2(70, "KAFKA"));

    val data = sc.parallelize(datas1);
    val group = data.groupByKey(); //按照相同的key对Value进行分组，分组后的value是一个集合
    group.collect().foreach(item => println(item._1, item._2));
  }

  def reduceByKeyTransFormation(sc: SparkContext) = {
    val datas = Array((100, "JAVA"), (100, "SPARK"), (80, "hadoop"), (80, "HBASE"), (70, "KAFKA"));
    val data = sc.parallelize(datas);
    val reduce = data.map(item => (item._1, 1)).reduceByKey((x, y) => x + y); //相同的key进行值累加
    reduce.foreach(f => println(f))
  }

  def mapTransFormation(sc: SparkContext) = {
    //Range
    val arr = 1 to 10;
    //根据集合创建RDD
    val numbers = sc.parallelize(arr);
    val map = numbers.map(item => item * 2); //map 适用于任何类型元素，其对集合里面的每个元素循环并且处理
    //在集群中使用collect，把数据返回到driver
    map.collect().foreach(println(_));
  }

  def filterTransFormation(sc: SparkContext) = {
    //Range
    val arr = 1 to 10;
    //根据集合创建RDD
    val numbers = sc.parallelize(arr);
    //filter 过滤满足条件的数据返回
    val filters = numbers.filter(item => item % 2 == 0);
    filters.collect().foreach(println(_))
  }

  def flatMapTransFormation(sc: SparkContext) = {
    //flatMap
    val arrayStrs = Array("Hello Spark", "Hello Java", "Hello Hadoop");
    val dataStrs = sc.parallelize(arrayStrs)
    //循环每一行元素，同个单词切分，之后形成集合（Hello,Spark）,（Hello,Java）,（Hello,Hadoop），
    //最后通过flatMap形成大集合(Hello,Spark,Hello,Java,Hello,Hadoop）
    val flatDatas = dataStrs.flatMap(item => item.split(" "));
    flatDatas.collect().foreach(println(_));
  }
}
