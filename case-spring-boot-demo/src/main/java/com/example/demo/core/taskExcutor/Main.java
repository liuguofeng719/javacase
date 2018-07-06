package com.example.demo.core.taskExcutor;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created with IntelliJ IDEA.
 *
 * @author lgfcxx
 * @version 1.0
 * @desc spring 异步执行，
 * 1，第一步 启用 @EnableAsync，
 * 2、第二步注入需要 任务调度池子，Executor
 * 3、第二步 在需要执行异步的方法里面添加@Async 方法
 * @createtime 2018/6/1 下午4:25
 * @see jdk 1.7
 **/
public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =new AnnotationConfigApplicationContext(TaskExecutorConfig.class);
        final TaskService bean = context.getBean(TaskService.class);
        for (int i = 0; i < 20; i++) {
            bean.executorAsyncTask(i);
            bean.executorAsyncTaskPlus(i);
        }
        context.close();
    }
}
