package com.example.cache.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created with IntelliJ IDEA.
 *
 * @author lgfcxx
 * @version 1.0
 * @desc
 * @createtime 2018/8/16 下午4:23
 * @see jdk 1.7
 **/
@Controller
public class KafkaTestController {

    @Autowired
    KafkaTemplate kafkaTemplate;

    @RequestMapping("/send")
    @ResponseBody
    public void send(){
        final String topic = "kafkaTestTopic";
        final String key = "kafkaKey";
        final String data = "你好";
        kafkaTemplate.send(topic, key, data);
    }
}
