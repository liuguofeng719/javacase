package com.exercise.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author lgfcxx
 * @version 1.0
 * @desc
 * @createtime 2017/9/5 下午3:21
 * @see jdk 1.7
 *  -Xss1m 代表栈的深度
 *  -verbose:gc  -Xms64M -Xmx64M -XX:MaxDirectMemorySize=5M -Xss1M -XX:+PrintGCDetails
 *  -verbose:gc -Xms64M -Xmx64M -XX:MaxDirectMemorySize=5M -Xss1M -XX:+PrintGCDetails -XX:+HeapDumpOnOutOfMemoryError
 *
 **/
class Person {
}

public class HeapOverFlowOutOfMemory {

    //堆溢出测试
    public static void main(String[] args) {
        List<Person> personList = new ArrayList<>();
        int i = 0;
        while (true) {
            personList.add(new Person());
            System.out.println("Person instance" + i++);
        }
    }
}
