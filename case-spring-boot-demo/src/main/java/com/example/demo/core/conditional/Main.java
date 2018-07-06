package com.example.demo.core.conditional;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created with IntelliJ IDEA.
 *
 * @author lgfcxx
 * @version 1.0
 * @desc
 * 1,实现Condition接口
 * 2、通过Conditional 注解表示需要通过条件的类
 * @createtime 2018/6/13 下午6:12
 * @see jdk 1.7
 **/
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ConditionConfig.class);
        final ListShowCmdService bean = context.getBean(ListShowCmdService.class);
        System.out.println("当前的操作系统cmd为：" + bean.listShowCmd() + " = " + context.getEnvironment().getProperty("os.name"));
    }
}
