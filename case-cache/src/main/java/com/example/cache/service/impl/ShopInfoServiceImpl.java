package com.example.cache.service.impl;

import com.alibaba.fastjson.JSON;
import com.example.cache.domain.ShopInfo;
import com.example.cache.service.ShopInfoService;

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
 * @createtime 2018/8/23 上午10:30
 * @see jdk 1.7
 **/
@Service
public class ShopInfoServiceImpl implements ShopInfoService {

    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public void saveShopInfoForRedis(ShopInfo shopInfo) {
        final String shopKey = "shop:"+shopInfo.getId();
        redisTemplate.opsForValue().set(shopKey, JSON.toJSONString(shopInfo));
    }

    @Override
    public ShopInfo getShopInfoForRedis(Long id) {
        final String shopKey = "shop:"+id;
        final Object shopInfo = redisTemplate.opsForValue().get(shopKey);
        if (shopInfo != null) {
            return JSON.parseObject(shopInfo.toString(),ShopInfo.class);
        }
        return null;
    }

    @Cacheable(value = "shop",key = "'key_'+#id")
    @Override
    public ShopInfo getShopInfoForLocalCache(Long id) {
        return null;
    }

    @CachePut(value = "shop", key = "'key_'+#shopInfo.id")
    @Override
    public ShopInfo saveShopInfoForLocalCache(ShopInfo shopInfo) {
        return shopInfo;
    }
}
