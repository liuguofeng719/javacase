package com.test.spark;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.MapFunction;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.api.java.function.VoidFunction;

import java.util.Arrays;
import java.util.Iterator;

import scala.Tuple2;

/**
 * Created with IntelliJ IDEA.
 *
 * @author lgfcxx
 * @version 1.0
 * @desc
 * @createtime 2017/8/30 下午2:46
 * @see JDK 1.7
 **/
public class WordCount {
    public static void main(String[] args) {
//        SparkSession sparkSession = SparkSession.builder().master("local").appName("WorkCount").getOrCreate();

        SparkConf conf = new SparkConf().setAppName("WorkCount").setMaster("local");
        JavaSparkContext jsc = new JavaSparkContext(conf);
        JavaRDD<String> stringRDD = jsc.textFile("text.txt");


        stringRDD.map(new Function<String, Object>() {
            public Object call(String v1) throws Exception {
                System.out.println("map="+v1);
                return v1;
            }
        });
        // 里面全是人 val xs = Seq(john, mary, alice, bob)
        // 每个人有一个朋友列表, 可以这么访问:
        // x.friends // 返回一个序列, 里面每个元素是一个人
        // 每个人有一个朋友列表, 可以这么访问:
        // x.friends // 返回一个序列, 里面每个元素是一个人
        // 得到一个序列的序列:
        // Seq(
        //   Seq(harry, hermione, ron),
        //   Seq(sam, frodo),
        //   Seq(),
        //   Seq(jamie, tyrion, cersei)
        // )
        //
        // xs.flatMap(x => x.friends)
        // 得到一个序列
        // Seq(harry, hermione, ron, sam, frodo, jamie, tyrion, cersei)
        JavaRDD<String> stringJavaRDD = stringRDD.flatMap(new FlatMapFunction<String, String>() {
            public Iterator<String> call(String lines) throws Exception {
                System.out.println(lines);
                return Arrays.asList(lines.split(" ")).iterator();
            }
        });


        //转换成元组
        JavaPairRDD<String, Integer> mapToPair = stringJavaRDD.mapToPair(new PairFunction<String, String, Integer>() {

            public Tuple2<String, Integer> call(String word) throws Exception {
                return new Tuple2<String, Integer>(word, 1);
            }
        });

        mapToPair.foreach(new VoidFunction<Tuple2<String, Integer>>() {
            public void call(Tuple2<String, Integer> t) throws Exception {
                System.out.println(t._1() + "=" + t._2());
            }
        });

        //把相同key，value值 累加  1，2，3，4  1+2=3 3+3=6
        JavaPairRDD<String, Integer> reduceByKey = mapToPair.reduceByKey(new Function2<Integer, Integer, Integer>() {
            public Integer call(Integer v1, Integer v2) throws Exception {
                System.out.println("reduce = "+v1 + "==" + v2);
                return v1 + v2;
            }
        });

        reduceByKey.foreach(new VoidFunction<Tuple2<String, Integer>>() {
            public void call(Tuple2<String, Integer> t) throws Exception {
                System.out.println("reduce print="+ t._1() + "=" + t._2());
            }
        });
        stringJavaRDD.foreach(new VoidFunction<String>() {
            public void call(String s) throws Exception {
                System.out.println(s);
            }
        });
    }
}
