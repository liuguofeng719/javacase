package com.exercise.zookeeper.nativeapi.znode;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author guofeng
 * @version 1.0
 * @see jdk 1.8
 **/
public class ZNodeTestClass  {

    public static void main(String[] args) throws IOException {
        ZNodeTest ztest = new ZNodeTest();
        try {
            final ZooKeeper zooKeeper = ztest.getZooKeeper();
        createNode(zooKeeper);
//            System.out.println(new String(zooKeeper.getData("/guofeng", true, new Stat())));
    } catch (KeeperException e) {
        e.printStackTrace();
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
        try {
        TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
}

    private static void createNode(ZooKeeper zooKeeper) throws KeeperException, InterruptedException {
        // 开启节点监听
        zooKeeper.exists("/guofeng", true);
        // 创建节点
        zooKeeper.create("/guofeng", "30".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
    }
}
