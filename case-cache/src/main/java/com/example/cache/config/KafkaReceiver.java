package com.example.cache.config;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 *
 * @author lgfcxx
 * @version 1.0
 * @desc
 * @createtime 2018/8/16 下午3:23
 * @see jdk 1.7
 **/
@Component
public class KafkaReceiver {

    private final Logger logger = LoggerFactory.getLogger(getClass());

//    @KafkaListener(topics = {"kafkaTestTopic"}, groupId = "test-consumer-group")
    public void listener(ConsumerRecord<?, ?> record) {
        logger.info("kafka的key: " + record.key());
        logger.info("kafka的value: " + record.value().toString());
    }
}
