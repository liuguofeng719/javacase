package com.spark.demo;

import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.api.java.function.VoidFunction;

import java.util.HashMap;
import java.util.stream.Collectors;

import scala.Tuple2;

/**
 * Created with IntelliJ IDEA.
 *
 * @author lgfcxx
 * @version 1.0
 * @desc
 * @createtime 2017/10/8 上午10:05
 * @see jdk 1.7
 **/
public class SecondarySorkKeyApp {

    public static void main(String[] args) {
        SparkConf conf = new SparkConf();
        conf.setAppName("SecondarySorkKeyApp");
        conf.setMaster("local");
        SparkContext sc = new SparkContext(conf);
        JavaSparkContext context = new JavaSparkContext(sc);
        JavaRDD<String> javaRDD = context.textFile("SecondarySortApp.txt");
        JavaPairRDD<SecondarySorkKey, String> javaPairRDD = javaRDD.mapToPair(new PairFunction<String, SecondarySorkKey, String>() {
            public Tuple2<SecondarySorkKey, String> call(String s) throws Exception {
                String[] split = s.split(" ");
                return new Tuple2<SecondarySorkKey, String>(new SecondarySorkKey(Integer.valueOf(split[0]), Integer.valueOf(split[1])), s);
            }
        });
        JavaPairRDD<SecondarySorkKey, String> secondarySorkKeyStringJavaPairRDD = javaPairRDD.sortByKey();
        JavaRDD<String> map = secondarySorkKeyStringJavaPairRDD.map(new Function<Tuple2<SecondarySorkKey, String>, String>() {
            public String call(Tuple2<SecondarySorkKey, String> v1) throws Exception {
                return v1._2();
            }
        });
        map.foreach(new VoidFunction<String>(){
            public void call(String s) throws Exception {
                System.out.println(s);
            }
        });
    }
}
