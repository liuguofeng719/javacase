package com.test;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import java.util.Arrays;
import java.util.List;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName("Spark Pi").setMaster("local");
        JavaSparkContext sc = new JavaSparkContext(conf);
        List<Integer> data = Arrays.asList(1, 2, 3, 4, 5);
        JavaRDD<Integer> distData = sc.parallelize(data);
        List<Integer> collect = distData.collect();
        for (final Integer integer : collect) {
            System.out.println(integer);
        }


//        JavaRDD<String> input = sc.textFile("s3://...");
//        JavaRDD<String> words = input.flatMap(new FlatMapFunction<String, String>() {
//            public Iterator<String> call(String x) {
//                return Arrays.asList(x.split(" ")).iterator();
//            }
//        });
//
//        JavaPairRDD<String, Integer> result = words.mapToPair(
//        new PairFunction<String, String, Integer>() {
//            public Tuple2<String, Integer> call(String x) {
//                return new Tuple2(x, 1);
//            }
//        }).reduceByKey(
//            new Function2<Integer, Integer, Integer>() {
//                public Integer call(Integer a, Integer b) {
//                    return a + b;
//                }
//            }
//        );
    }
}
