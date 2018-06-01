package com.example.demo.core.event;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created with IntelliJ IDEA.
 *
 * @author lgfcxx
 * @version 1.0
 * @desc
 * 1,自定义事件，继承ApplicationEvent
 * 2、实现监听事件 ApplicationListener
 * 3、使用容器发布事件
 * @createtime 2018/6/1 下午3:25
 * @see jdk 1.7
 **/
public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(EventConfig.class);
        final EventPublish publish = context.getBean(EventPublish.class);
        publish.publish("测试消息");
        context.close();
    }
}
