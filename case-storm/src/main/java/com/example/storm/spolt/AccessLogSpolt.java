package com.example.storm.spolt;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichSpout;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;
import org.apache.storm.utils.Utils;

import java.time.Duration;
import java.util.Arrays;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created with IntelliJ IDEA.
 *
 * @author lgfcxx
 * @version 1.0
 * @desc 1, 单线程消费kafka 队列里面的数据， 2、把数据放入ArrayBlockQueue阻塞队列里面 3，nextTuple 不断拉去ArrayBlockQueue
 * 里面的数据，发射，如果数据为空，休眠
 * @createtime 2018/9/11 下午4:39
 * @see jdk 1.7
 **/
public class AccessLogSpolt extends BaseRichSpout {

    SpoutOutputCollector spoutOutputCollector;

    ArrayBlockingQueue<String> arrayBlockingQueue = new ArrayBlockingQueue<String>(1000);

    public void open(Map map, TopologyContext topologyContext, SpoutOutputCollector spoutOutputCollector) {
        this.spoutOutputCollector = spoutOutputCollector;
        startKafaConsumer();
    }

    private void startKafaConsumer() {

        Properties props = new Properties();
        props.put("zookeeper.connect", "10.211.55.4:9092,10.211.55.5:9092,10.211.55.6:9092");
        props.put("group.id", "eshop-cache-group");
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("auto.offset.reset", "earliest");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        KafkaConsumer<String, String> consumer = new KafkaConsumer<String,String>(props);
        final String topic = "access-log";
        consumer.subscribe(Arrays.asList(topic));

        new Thread(new ConsumerMessageProcessor(consumer)).start();
    }

    private class ConsumerMessageProcessor implements Runnable {

        KafkaConsumer<String, String> consumer;

        public ConsumerMessageProcessor(KafkaConsumer<String, String> consumer) {
            this.consumer = consumer;
        }

        @Override
        public void run() {
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(1000));
                if (!records.isEmpty()) {
                    for (ConsumerRecord<String, String> record : records) {
                        try {
                            arrayBlockingQueue.put(record.value());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    public void nextTuple() {
        if (arrayBlockingQueue.size() > 0) {
            try {
                spoutOutputCollector.emit(new Values(arrayBlockingQueue.take()));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            Utils.sleep(100);
        }
    }

    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        outputFieldsDeclarer.declare(new Fields("message"));
    }
}
