package com.example.storm.bolt;

import com.alibaba.fastjson.JSONArray;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessLock;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.trident.util.LRUMap;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 *
 * @author lgfcxx
 * @version 1.0
 * @desc
 * @createtime 2018/9/11 下午6:00
 * @see jdk 1.7
 **/
public class ProductCountBolt extends BaseRichBolt {

    private OutputCollector outputCollector;

    private int taskId;

    private LRUMap<Long, Long> lruMap = new LRUMap(1000);

    private static final int SESSION_OUTTIME = 5000;//ms

    private final String connectString = "10.211.55.4:2181,10.211.55.5:2181,10.211.55.6:2181";
    private CuratorFramework client;

    public void prepare(Map map, TopologyContext topologyContext, OutputCollector outputCollector) {
        startZookeeper();
        this.outputCollector = outputCollector;
        this.taskId = topologyContext.getThisTaskId();
        new Thread(new ProductThread()).start();
        initTaskId(taskId);
    }

    private void startZookeeper() {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        client = CuratorFrameworkFactory.builder()
                .connectString(connectString)
                .sessionTimeoutMs(SESSION_OUTTIME)
                .retryPolicy(retryPolicy)
                .namespace("super")
                .build();
        client.start();
    }

    /**
     * 每个task都有taskId，每一个task执行的预热的时候需要，获取分布式锁
     * 存储的taskId，for example: 111,222,333
     * @param taskId
     */
    private void initTaskId(int taskId) {
        InterProcessLock lock = new InterProcessMutex(client, "/taskid-list-lock");
        try {
            if(lock.acquire(3000, TimeUnit.SECONDS)){
                try {
                    final byte[] bytes = client.getData().forPath("/taskid-list-lock");
                    String taskList = new String(bytes);
                    if (!"".equals(taskList)) {
                        taskList += "," + taskId;
                    } else {
                        taskList += taskId;
                    }
                    client.setData().forPath("/taskid-list-lock", taskList.getBytes());
                } finally {
                    lock.release();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private class ProductThread implements Runnable {

        @Override
        public void run() {

            List<Map.Entry<Long, Long>> topnProductList = new ArrayList<>();
            List<Long> productidList = new ArrayList<>();

            while (true) {

                productidList.clear();

                topnProductList.clear();

                int topn = 3;

                if(lruMap.size() == 0) {
                    Utils.sleep(100);
                    continue;
                }

                for (Map.Entry<Long, Long> productCountEntry : lruMap.entrySet()) {
                    if (topnProductList.size() == 0) {
                        topnProductList.add(productCountEntry);
                    } else {
                        // 比较大小，生成最热topn的算法有很多种
                        // 但是我这里为了简化起见，不想引入过多的数据结构和算法的的东西
                        // 很有可能还是会有漏洞，但是我已经反复推演了一下了，而且也画图分析过这个算法的运行流程了
                        boolean bigger = false;

                        for (int i = 0; i < topnProductList.size(); i++) {
                            Map.Entry<Long, Long> topnProductCountEntry = topnProductList.get(i);

                            if (productCountEntry.getValue() > topnProductCountEntry.getValue()) {
                                int lastIndex = topnProductList.size() < topn ? topnProductList.size() - 1 : topn - 2;
                                for (int j = lastIndex; j >= i; j--) {
                                    topnProductList.set(j + 1, topnProductList.get(j));
                                }
                                topnProductList.set(i, productCountEntry);
                                bigger = true;
                                break;
                            }
                        }

                        if (!bigger) {
                            if (topnProductList.size() < topn) {
                                topnProductList.add(productCountEntry);
                            }
                        }
                    }
                }

                // 获取到一个topn list
                for(Map.Entry<Long, Long> topnProductEntry : topnProductList) {
                    productidList.add(topnProductEntry.getKey());
                }

                String topnProductListJSON = JSONArray.toJSONString(productidList);

                try {

                    client.create().forPath("/task-hot-product-list-" + taskId,topnProductListJSON.getBytes());
//                    client.setData().forPath("/task-hot-product-list-" + taskId, topnProductListJSON.getBytes());
                } catch (Exception e) {
                    e.printStackTrace();
                }

                Utils.sleep(1000);
            }
        }
    }

    public void execute(Tuple tuple) {
        final Long productId = tuple.getLongByField("productId");
        Long aLong = lruMap.get(productId);
        if (aLong == null) {
            aLong = 0L;
        }
        aLong++;
        lruMap.put(productId, aLong);
    }

    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {

    }
}
