package com.guava.test.cache;

import com.google.common.collect.Maps;

import java.util.concurrent.ConcurrentMap;

/**
 * @author guofeng
 * @version 1.0
 * @desc
 * @createtime 2018/12/29 4:02 PM
 * @see jdk 1.7
 **/
public class LocalCacheImpl<K, T> implements Cache<K, T> {

    private ConcurrentMap<K, T> hashMap = Maps.newConcurrentMap();

    @Override
    public void put(K key, T data) {
        hashMap.put(key, data);
    }

    @Override
    public T get(K key) {
        return hashMap.get(key);
    }
}
