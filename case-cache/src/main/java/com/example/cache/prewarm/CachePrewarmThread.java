package com.example.cache.prewarm;

import com.alibaba.fastjson.JSONArray;
import com.example.cache.domain.ProductInfo;
import com.example.cache.service.ProductInfoService;
import com.example.cache.utils.SpringContext;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessLock;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.springframework.context.ApplicationContext;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 *
 * @author lgfcxx
 * @version 1.0
 * @desc
 * @createtime 2018/9/13 下午5:54
 * @see jdk 1.7
 **/
public class CachePrewarmThread implements Runnable {

    final ApplicationContext application = SpringContext.getApplicationContext();
    final ProductInfoService infoService = application.getBean(ProductInfoService.class);
    final CuratorFramework curatorFramework = application.getBean(CuratorFramework.class);

    public CachePrewarmThread() {
        //启动zookeeper 客户端
        curatorFramework.start();
    }

    @Override
    public void run() {

        try {
            //获取存在zookeeper中的storm task任务的taskId
            String taskList = new String(curatorFramework.getData().forPath("/taskid-list-lock"));
            if (taskList != null && !"".equals(taskList)) {
                final String[] taskIds = taskList.split(",");

                for (final String taskId : taskIds) {

                    String taskIdLockPath = "task-lock-" + taskId;
                    InterProcessLock processLock = new InterProcessMutex(curatorFramework, taskIdLockPath);

                    if (processLock.acquire(300, TimeUnit.SECONDS)) {

                        try {

                            String taskIdStatusLockPath = "/taskid-status-lock-" + taskId;
                            InterProcessLock processStatusLockPath = new InterProcessMutex(curatorFramework, taskIdStatusLockPath);

                            if (processStatusLockPath.acquire(300, TimeUnit.SECONDS)) {

                                try {

                                    //查询预热状态
                                    final String taskIdStatus = new String(curatorFramework.getData().forPath("/taskid-status-" + taskId));

                                    if("".equals(taskIdStatus)){

                                        //获取每个task 任务的热点数据
                                        String productList = new String(curatorFramework.getData().forPath("/task-hot-product-list-" + taskId));

                                        final JSONArray jsonArray = JSONArray.parseArray(productList);
                                        for(int i = 0; i < jsonArray.size(); i++) {
                                            Long productId = jsonArray.getLong(i);
                                            ProductInfo productInfo = new ProductInfo();
                                            productInfo.setId(productId);
                                            productInfo.setName("iphone7手机");
                                            productInfo.setPrice(5599.00);
                                            productInfo.setPictureList("a.jpg,b.jpg");
                                            productInfo.setSpecification("iphone7的规格");
                                            productInfo.setService("iphone7的售后服务");
                                            productInfo.setColor("红色,白色,黑色");
                                            productInfo.setSize("5.5");
                                            productInfo.setShopId(1L);
                                            productInfo.setModifiedTime(new Date());
                                            infoService.saveProductForLocalCache(productInfo);
                                            infoService.saveProductForRedisCache(productInfo);
                                        }
                                        curatorFramework.create().forPath("/taskid-status-" + taskId, "success".getBytes());
                                    }
                                }finally {
                                    processStatusLockPath.release();
                                }
                            }
                        } finally {
                            processLock.release();
                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
