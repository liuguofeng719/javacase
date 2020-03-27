package com.exercise.zookeeper.nativeapi.znode;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @author guofeng
 * @version 1.0
 * @see jdk 1.8
 **/
public class ZNodeTest implements Watcher {

    private ZooKeeper zooKeeper = null;

    public ZNodeTest() {
        try {
            zooKeeper = new ZooKeeper("127.0.0.1:2181,127.0.0.1:2182,127.0.0.1:2183", 5000, this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ZooKeeper getZooKeeper() {
        return zooKeeper;
    }

    @Override
    public void process(WatchedEvent event) {
        try {
            System.out.println(event.getPath() + " = " + event.getType());
            if (zooKeeper.exists("/guofeng", true) != null) {
                System.out.println(event.getType() + "=" + new String(zooKeeper.getData("/guofeng", true, new Stat())));
                // 获取子节点
                final List<String> children = zooKeeper.getChildren("/guofeng", true);
                for (final String child : children) {
                    System.out.println(event.getType() + " child = " + child);
                    // 获取子节点的数据
                    System.out.println(event.getType() + " child value= " + new String(zooKeeper.getData("/guofeng/"+child, true, new Stat())));
                }
            }
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

/**
 * watch test
 *
 */
 class App implements Watcher {

    private static CountDownLatch connectedSemaphore = new CountDownLatch(1);
    public static Stat stat = new Stat();

    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        String p = "/testaa";
        ZooKeeper zooKeeper = new ZooKeeper("127.0.0.1:2181,127.0.0.1:2182,127.0.0.1:2183", 5000, new App());
        connectedSemaphore.await();
        //exists register watch
        zooKeeper.exists(p, true);
        String path = zooKeeper.create(p, "456".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,
                CreateMode.PERSISTENT);
        //get register watch
        zooKeeper.getData(path, true, stat);
        zooKeeper.setData(path, "hhhh".getBytes(), -1);
        zooKeeper.exists(path, true);
        //exists register watch
        zooKeeper.delete(path, -1);

    }

    @Override
    public void process(WatchedEvent event) {
        if (Event.KeeperState.SyncConnected == event.getState()) {
            if (EventType.None == event.getType() && null == event.getPath()) {
                connectedSemaphore.countDown();
                System.out.println("Zookeeper session established");
            } else if (EventType.NodeCreated == event.getType()) {
                System.out.println("success create znode");

            } else if (EventType.NodeDataChanged == event.getType()) {
                System.out.println("success change znode: " + event.getPath() );

            } else if (EventType.NodeDeleted == event.getType()) {
                System.out.println("success delete znode");

            } else if (EventType.NodeChildrenChanged == event.getType()) {
                System.out.println("NodeChildrenChanged");

            }

        }
    }
}