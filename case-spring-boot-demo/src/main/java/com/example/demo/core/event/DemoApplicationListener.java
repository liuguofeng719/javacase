package com.example.demo.core.event;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 *
 * @author lgfcxx
 * @version 1.0
 * @desc 订阅事件
 * @createtime 2018/6/1 下午3:30
 * @see jdk 1.7
 **/
@Component
public class DemoApplicationListener implements ApplicationListener<DemoEvent> {

    @Override
    public void onApplicationEvent(DemoEvent demoEvent) {
        System.out.println("事件源=" + demoEvent.getSource() + " = 事件消息" + demoEvent.getMsg());
    }
}
