package com.exercise.thread.aqs.readwrite;

/**
 * Created with IntelliJ IDEA.
 *
 * @author lgfcxx
 * @version 1.0
 * @desc
 * @createtime 2018/3/29 下午2:15
 * @see jdk 1.7
 **/
public class ReadWriteLockDemoTest {

    public static void main(String[] args) {
        ReadWriteLockDemo rwl = new ReadWriteLockDemo();
        new Thread(new Runnable() {
            @Override
            public void run() {
                rwl.put("key1", "value1");
            }
        }).start();

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                rwl.put("key2", "value2");
//            }
//        }).start();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                rwl.put("key3", "value2");
//            }
//        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " get = " + rwl.get("key1"));
            }
        }).start();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println(Thread.currentThread().getName() + "get = " + rwl.get("key2"));
//            }
//        }).start();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println(Thread.currentThread().getName() + "get = " + rwl.get("key3"));
//            }
//        }).start();
    }
}
