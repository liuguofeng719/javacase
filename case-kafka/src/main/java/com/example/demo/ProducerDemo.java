package com.example.demo;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 *
 * @author lgfcxx
 * @version 1.0
 * @desc
 * @createtime 2018/8/15 下午10:32
 * @see jdk 1.7
 **/
public class ProducerDemo {
    // Topic
    private static final String topic = "kafkaTopic";

    public static void main(String[] args) throws Exception {

        Properties props = new Properties();
        props.put("bootstrap.servers", "10.211.55.4:9092,10.211.55.5:9092,10.211.55.6:9092");
        props.put("acks", "0");
        props.put("group.id", "1111");
        props.put("retries", "0");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        //生产者实例
        KafkaProducer producer = new KafkaProducer(props);

        int i = 1;

        // 发送业务消息
        // 读取文件 读取内存数据库 读socket端口
        while (true) {
            Thread.sleep(1000);
            producer.send(new ProducerRecord<String, String>(topic, "key:" + i, "value:" + i));
            System.out.println("key:" + i + " " + "value:" + i);
            i++;
        }
    }
}
