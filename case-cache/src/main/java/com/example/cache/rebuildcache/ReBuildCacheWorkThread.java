package com.example.cache.rebuildcache;

import com.example.cache.domain.ProductInfo;
import com.example.cache.service.ProductInfoService;
import com.example.cache.utils.SpringContext;

import org.apache.curator.framework.CuratorFramework;
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
 * @createtime 2018/8/27 下午4:06
 * @see jdk 1.7
 **/
public class ReBuildCacheWorkThread implements Runnable {

    final ApplicationContext application = SpringContext.getApplicationContext();
    final ReBuildCacheQueue<ProductInfo> instance = ReBuildCacheQueue.getInstance();
    final ProductInfoService infoService = application.getBean(ProductInfoService.class);
    final CuratorFramework curatorFramework = application.getBean(CuratorFramework.class);

    public ReBuildCacheWorkThread() {
        curatorFramework.start();
    }

    @Override
    public void run() {

        while (true) {
            final ProductInfo productInfo = instance.get();
            InterProcessMutex lock = new InterProcessMutex(curatorFramework, "/lock_product_" + productInfo.getId());
            try {
                if (lock.acquire(3000, TimeUnit.SECONDS)) {

                    try {

                        final ProductInfo productForRedis = infoService.getProductForRedis(productInfo.getId());

                        if (productForRedis != null) {

                            final Date modifiedTime = productInfo.getModifiedTime();

                            final Date productForRedisModifiedTime = productForRedis.getModifiedTime();

                            if (modifiedTime.before(productForRedisModifiedTime)) {
                                System.out.println("redis中的缓存商品的时间="+productForRedisModifiedTime+" = 数据库中的商品的时间 = "+ modifiedTime);
                                continue;
                            }
                        }

                        infoService.saveProductForLocalCache(productInfo);
                        infoService.saveProductForRedisCache(productInfo);

                    } finally {
                        lock.release();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
