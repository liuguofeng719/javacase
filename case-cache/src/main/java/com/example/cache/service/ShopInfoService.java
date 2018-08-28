package com.example.cache.service;

import com.example.cache.domain.ShopInfo;

/**
 * Created with IntelliJ IDEA.
 *
 * @author lgfcxx
 * @version 1.0
 * @desc
 * @createtime 2018/8/17 下午4:10
 * @see jdk 1.7
 **/
public interface ShopInfoService {
    void saveShopInfoForRedis(ShopInfo shopInfo);

    ShopInfo getShopInfoForRedis(Long id);

    ShopInfo getShopInfoForLocalCache(Long id);

    ShopInfo saveShopInfoForLocalCache(ShopInfo shopInfo);
}
