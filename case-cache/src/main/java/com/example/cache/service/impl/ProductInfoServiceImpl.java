package com.example.cache.service.impl;

import com.alibaba.fastjson.JSON;
import com.example.cache.domain.ProductInfo;
import com.example.cache.service.ProductInfoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 *
 * @author lgfcxx
 * @version 1.0
 * @desc
 * @createtime 2018/8/17 下午4:13
 * @see jdk 1.7
 **/
@Service
public class ProductInfoServiceImpl implements ProductInfoService {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 存入缓存信息
     * @param productInfo
     * @return
     */
    @CachePut(value = "product", key = "'key_'+#productInfo.id")
    @Override
    public ProductInfo saveProductForLocalCache(ProductInfo productInfo) {
        return productInfo;
    }

    /**
     * 获取商品信息
     * @param pid
     * @return
     */
    @Cacheable(value = "product",key = "'key_'+#pid")
    @Override
    public ProductInfo getProductForLocalCache(Long pid) {
        return null;
    }

    @Override
    public ProductInfo getProductForRedis(Long pid) {
        final String productKey = "product:" + pid;
        final Object product = redisTemplate.opsForValue().get(productKey);
        if (null != product) {
            final String jsonStr = product.toString();
            return JSON.parseObject(jsonStr, ProductInfo.class);
        }
        return null;
    }


    @Override
    public void saveProductForRedisCache(ProductInfo productInfo) {
        final String productKey = "product:" + productInfo.getId();
        redisTemplate.opsForValue().set(productKey, JSON.toJSONString(productInfo));
    }
}
