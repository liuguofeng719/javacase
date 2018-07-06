package com.example.demo.core.taskExcutor;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 *
 * @author lgfcxx
 * @version 1.0
 * @desc
 * @createtime 2018/6/1 下午4:34
 * @see jdk 1.7
 **/
@Service
public class TaskService {

    @Async
    public void executorAsyncTask(Integer integer) {
        System.out.println("执行任务" + integer);
    }

    @Async
    public void executorAsyncTaskPlus(Integer integer) {
        System.out.println("累加值" + (integer + 1));
    }
}
