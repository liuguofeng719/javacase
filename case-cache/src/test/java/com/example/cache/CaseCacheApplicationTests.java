package com.example.cache;

import com.google.common.collect.Lists;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.cache.domain.ProductInfo;
import com.example.cache.domain.ProductInventory;
import com.example.cache.domain.ShopInfo;
import com.example.cache.domain.User;
import com.example.cache.mapper.ProductInventoryMapper;
import com.example.cache.rebuildcache.ReBuildCacheQueue;
import com.example.cache.service.ProductInfoService;
import com.example.cache.service.ShopInfoService;
import com.example.cache.service.UserService;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CaseCacheApplicationTests {

    @Autowired
    private UserService userService;

    @Autowired
    private ProductInventoryMapper mapper;

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private ShopInfoService shopInfoService;

    @Autowired
    private ProductInfoService productInfoService;

    static final int SESSION_OUTTIME = 5000;//ms
    final String connectString = "10.211.55.4:2181,10.211.55.5:2181,10.211.55.6:2181";

    @Test
    public void zookeeperDistributeLock() throws Exception {

        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000,3);

        CuratorFramework client = CuratorFrameworkFactory.builder()
                            .connectString(connectString)
                            .sessionTimeoutMs(SESSION_OUTTIME)
                            .retryPolicy(retryPolicy)
                            .namespace("super")
                            .build();
        client.start();

        try {

            final ExecutorService executorService = Executors.newFixedThreadPool(3);
            client.create().inBackground((CuratorFramework curatorFramework, CuratorEvent curatorEvent) -> {
                System.out.println(String.format("eventType:%s,resultCode:%s",curatorEvent.getType(),curatorEvent.getResultCode()));
            }, executorService).forPath("/my/path5/path12");
//            client.create().forPath("/my/");
            final List<String> strings = client.getChildren().forPath("/my/path5");
            System.out.println(strings);
//            client.delete().deletingChildrenIfNeeded().forPath("/my/test");
//            client.delete().withVersion(10000).forPath("/my/test");
//            client.delete().guaranteed().forPath("/my/test");
//            final Stat stat = client.checkExists().forPath("/my/path6");
//            if (stat == null) {
//                client.create().creatingParentContainersIfNeeded().withMode(CreateMode.EPHEMERAL).forPath("/my/path6", "I love messi".getBytes());
//            }
//            byte[] bs = client.getData().forPath("/my/path6");
//            client.getData().storingStatIn(new Stat()).forPath("/my/path6");
//            client.setData().forPath("/my/path6","sss".getBytes());
//            System.out.println("新建的节点，data为:" + new String(bs));
        } catch (Exception e) {
            e.printStackTrace();
        }

        InterProcessMutex lock = new InterProcessMutex(client, "/my/path");
        if(lock.acquire(100, TimeUnit.SECONDS)){
            try{

            }finally {
                lock.release();
            }
        }
    }

    @Test
    public void rebuildCache() {

        final ReBuildCacheQueue<ProductInfo> instance = ReBuildCacheQueue.getInstance();
        String productInfoJSON = "{\"id\": 2, \"name\": \"iphone7手机\", \"price\": 5599, \"pictureList\":\"a.jpg,b.jpg\", \"specification\": \"iphone7的规格\", \"service\": \"iphone7的售后服务\", \"color\": \"红色,白色,黑色\", \"size\": \"5.5\", \"shopId\": 1, \"modifiedTime\": \"2018-08-28 12:01:00\"}";
        ProductInfo productInfo = JSON.parseObject(productInfoJSON, ProductInfo.class);
        instance.put(productInfo);

    }

    @Test
    public void shopCache() {
        String productInfoJSON = "{\"id\": 1, \"name\": \"iphone7手机\", \"price\": 5599, \"pictureList\":\"a.jpg,b.jpg\", \"specification\": \"iphone7的规格\", \"service\": \"iphone7的售后服务\", \"color\": \"红色,白色,黑色\", \"size\": \"5.5\", \"shopId\": 1, \"modifiedTime\": \"2018-08-28 12:01:00\"}";

        ProductInfo productInfo = JSON.parseObject(productInfoJSON, ProductInfo.class);
        productInfoService.saveProductForRedisCache(productInfo);
        String shopInfoJSON = "{\"id\": 1, \"name\": \"小王的手机店\", \"level\": 5, \"goodCommentRate\":0.99}";
        ShopInfo shopInfo = JSONObject.parseObject(shopInfoJSON, ShopInfo.class);
        shopInfoService.saveShopInfoForRedis(shopInfo);

        System.out.println(shopInfoService.getShopInfoForRedis(1L));
        System.out.println(productInfoService.getProductForRedis(1L));

        shopInfoService.saveShopInfoForLocalCache(shopInfo);
        System.out.println(shopInfoService.getShopInfoForLocalCache(5L));

    }



    @Test
    public void kafkaProducer() throws Exception {
        final String topic = "kafkaTestTopic";
        final String key = "kafkaKey";
        final String data = "你好";
        kafkaTemplate.send(topic, key, data);
    }

    @Test
    public void contextLoads() {
        User user = new User();
        user.setId(20L);
        user.setAddress("xxx");
        user.setName("陈思诚");
        user.setAge(34);
        userService.save(user);
        System.out.println(userService.getUserById(20L));
    }

    @Test
    public void addList() throws Exception {
        List<ProductInventory> list = Lists.newArrayList();
        for (int i = 0; i < 10; i++) {
            ProductInventory productInventory = new ProductInventory();
            productInventory.setInventoryCnt(i);
            productInventory.setProductId(i);
            list.add(productInventory);
        }
        mapper.add(list);
    }

    @Test
    public void redisCluster() throws Exception{
        final ValueOperations valueOperations = redisTemplate.opsForValue();
        valueOperations.set("1","333");
        final Object zhangsan = valueOperations.get("1");
        System.out.println(zhangsan);
    }

}
