package com.example.cache.rebuildcache;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created with IntelliJ IDEA.
 *
 * @author lgfcxx
 * @version 1.0
 * @desc
 * @createtime 2018/8/27 下午3:51
 * @see jdk 1.7
 **/
public class ReBuildCacheQueue<T> {

    ArrayBlockingQueue<T> blockingQueue = new ArrayBlockingQueue<>(10);

    public void put(T data) {
        try {
            blockingQueue.put(data);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public T get() {
        try {
            return blockingQueue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static class ReBuildCacheInstance {

        static ReBuildCacheQueue reBuildCache = null;

        static {
            reBuildCache = new ReBuildCacheQueue();
        }

        public static ReBuildCacheQueue getInstance() {
            return reBuildCache;
        }
    }

    public static ReBuildCacheQueue getInstance() {
        return ReBuildCacheInstance.getInstance();
    }

}
