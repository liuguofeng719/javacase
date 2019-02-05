package com.guava.test.cache;

/**
 * @author guofeng
 * @version 1.0
 * @desc
 * @createtime 2018/12/29 4:01 PM
 * @see jdk 1.7
 **/
public interface Cache<K, T> {

    void put(K key, T data);

    T get(K key);
}
