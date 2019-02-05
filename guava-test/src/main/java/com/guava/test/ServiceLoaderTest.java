package com.guava.test;

import com.guava.test.cache.Cache;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @author guofeng
 * @version 1.0
 * @desc
 * @createtime 2018/12/29 2:46 PM
 * @see jdk 1.7
 **/
public class ServiceLoaderTest {

    public static void main(String[] args) {

        final ServiceLoader<Cache> load = ServiceLoader.load(Cache.class);

        final Iterator<Cache> iterator = load.iterator();

        while (iterator.hasNext()) {

            final Cache next = iterator.next();
            System.out.println(next.getClass());
            next.put(11, 11);
            System.out.println(next.get(11));
        }
    }
}
