package com.rocketmq.test;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
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
 * @desc
 * @createtime 2018/4/10 下午6:39
 * @see jdk 1.7
 **/
public class Consoumer {
    public static void main(String[] args) throws  Exception{
        DefaultMQPushConsumer pushConsumer = new DefaultMQPushConsumer("rpGroup4");

        //这里设置消费模式，第一次的topic group 消费进度是重第一条开始消费，再次重新启动，就重消费的offset开始消费
        pushConsumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);
        pushConsumer.setMessageModel(MessageModel.CLUSTERING);
//        pushConsumer.setNamesrvAddr("10.211.55.4:9876;10.211.55.5:9876");
        pushConsumer.setNamesrvAddr("10.28.16.106:9876;10.28.16.157:9876");
        pushConsumer.subscribe("tpcTest3","*");

        final MessageListenerConcurrently messageListener = new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list,
                                                            ConsumeConcurrentlyContext consumeConcurrentlyContext) {
                for (final MessageExt messageExt : list) {
                    System.out.println(messageExt.getTopic() + " = " + new String(messageExt.getBody()));
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        };

        pushConsumer.setMessageListener(messageListener);

        pushConsumer.start();

    }

    final MessageListenerOrderly listenerOrderly = new MessageListenerOrderly() {
        @Override
        public ConsumeOrderlyStatus consumeMessage(List<MessageExt> list, ConsumeOrderlyContext consumeOrderlyContext) {
            for (final MessageExt messageExt : list) {
                System.out.println(messageExt.getTopic() + " = " + new String(messageExt.getBody()));
            }
            return ConsumeOrderlyStatus.SUCCESS;
        }
    };
}
