package com.rocketmq.test;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerOrderly;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author lgfcxx
 * @version 1.0
 * @desc 1、不同group 相同的topic，能收到消息
 * 2、相同group，相同topic，只有一个收到
 * 2、相同的group，负载效果
 * @createtime 2018/4/10 下午6:39
 * @see jdk 1.7
 **/
public class Consoumer1 {
    public static void main(String[] args) throws Exception {
        DefaultMQPushConsumer pushConsumer = new DefaultMQPushConsumer("rpGroup1");

        //这里设置消费模式，第一次的topic group 消费进度是重第一条开始消费，再次重新启动，就重消费的offset开始消费
        pushConsumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);
        pushConsumer.setNamesrvAddr("10.211.55.4:9876;10.211.55.5:9876");
        pushConsumer.setMessageModel(MessageModel.CLUSTERING);
        pushConsumer.subscribe("tpcTest1", "*");
//        pushConsumer.setMessageListener(new MessageListenerConcurrently() {
//            @Override
//            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list,
//                                                            ConsumeConcurrentlyContext consumeConcurrentlyContext) {
//                System.out.println(Thread.currentThread().getName() + "===");
//                for (final MessageExt messageExt : list) {
//                    System.out.println(messageExt.getTopic() + " = " + new String(messageExt.getBody()));
//                }
//                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
//            }
//        });
        pushConsumer.setMessageListener(new MessageListenerOrderly() {
            @Override
            public ConsumeOrderlyStatus consumeMessage(List<MessageExt> list, ConsumeOrderlyContext consumeOrderlyContext) {
                System.out.println(Thread.currentThread().getName() + "===");
                for (final MessageExt messageExt : list) {
                    System.out.println(messageExt.getTopic() + " = "+ new String(messageExt.getBody()));
                }
                return ConsumeOrderlyStatus.SUCCESS;
            }
        });


        pushConsumer.start();

    }
}
