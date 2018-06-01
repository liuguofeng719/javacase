package com.exercise.thread.aqs.readwrite;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created with IntelliJ IDEA.
 *
 * @author lgfcxx
 * @version 1.0
 * @desc 读和写同时操作是互斥，写和写是互斥，读和读是共享的
 * @createtime 2018/3/29 下午12:10
 * @see jdk 1.7
 **/
public class ReadWriteLockDemo {

    public Map<String, Object> map = new HashMap<>();
    private ReadWriteLock rwl = new ReentrantReadWriteLock();
    private Lock r = rwl.readLock();
    private Lock w = rwl.writeLock();

    public void put(String key, String value) {
        try {
            w.lock();
            System.out.println("开始执行写操作。。。");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            map.put(key, value);
        } finally {
            w.unlock();
            System.out.println("写操作执行完成。。。");
        }
    }

    public Object get(String key) {
        try {
            r.lock();
            System.out.println("开始执行读操作。。。");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return map.get(key);
        } finally {
            r.unlock();
            System.out.println("读操作完成。。。");
        }
    }
}
