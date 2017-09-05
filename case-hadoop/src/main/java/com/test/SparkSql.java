package com.test;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.sql.Column;
import org.apache.spark.sql.Column$;
import org.apache.spark.sql.DataFrameReader;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SQLContext;
import org.apache.spark.sql.SaveMode;
import org.apache.spark.sql.SparkSession;

/**
 * Created with IntelliJ IDEA.
 *
 * @author lgfcxx
 * @version 1.0
 * @desc
 * @createtime 2017/8/29 下午5:37
 * @see JDK 1.7
 **/
public class SparkSql {

    public static void main(String[] args) {

        SparkSession spark = SparkSession
                .builder()
                .appName("sql")
                .master("local")
//                .enableHiveSupport()
                .getOrCreate();

        DataFrameReader reader = spark.read()
                .format("jdbc")
                .option("url", "jdbc:mysql://10.28.16.94:3306/smzc")
                .option("user", "smapp")
                .option("password", "smcdyanfa");

        Dataset<Row> jdbcDF = reader.option("dbtable", "order_info").load();

        jdbcDF.show(10);

//        jdbcDF.registerTempTable("order_info");

//        SQLContext sqlContext = jdbcDF.sqlContext();
//        sqlContext.createDataFrame()
//        sqlContext.sql("select * from order_info");

        Dataset<Row> jdbcDF1 = reader.option("dbtable", "order_info_address").load();
        //操作会写数据库
//        jdbcDF1.write().mode(SaveMode.Append).jdbc();
        Dataset<Row> join = jdbcDF.join(jdbcDF1,
                jdbcDF.col("id").equalTo(new Column("orderId")), "left")
                .select(
                        new Column[]{
                                col("orderId").as("o"),
                                col("passengerMobile"),
                                col("fromAddress"),
                                col("fromStreet")
                        });
        join.show(10);
//        Dataset<Row> passengerMobile = join.groupBy(jdbcDF.col("passengerMobile")).count();
//        passengerMobile.show();
//        JavaRDD<Row> javaRDD = passengerMobile.toJavaRDD();

    }

    public static Column col(String colName){
        return new Column(colName);
    }
}
