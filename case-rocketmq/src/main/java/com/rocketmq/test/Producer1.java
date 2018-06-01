package com.rocketmq.test;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

/**
 * Created with IntelliJ IDEA.
 *
 * @author lgfcxx
 * @version 1.0
 * @desc
 * @createtime 2018/4/10 下午4:57
 * @see jdk 1.7
 **/
public class Producer1 {

    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("a");
        producer.setNamesrvAddr("10.211.55.5:9876;10.211.55.4:9876");
        producer.start();
        Integer hass = 5;
        for (int i = 1; i < 5; i++) {
            //Create a message instance, specifying topic, tag and message body.
            Message msg = new Message("tpcTest1" /* Topic */,
                    "TagA" /* Tag */,
                    ("1 " +
                            i).getBytes(RemotingHelper.DEFAULT_CHARSET) /* Message body */
            );
//            final SendResult sendResult = producer.send(msg, new MessageQueueSelector() {
//                @Override
//                public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {
//                    Integer id = (Integer) arg;
//                    int index = id % mqs.size();
//                    return mqs.get(index);
//                }
//            }, hass);

            //Call send message to deliver message to one of brokers.
            SendResult sendResult = producer.send(msg);
            System.out.printf("%s%n", sendResult);
        }

        //Shut down once the producer instance is not longer in use.
        producer.shutdown();
    }
}
