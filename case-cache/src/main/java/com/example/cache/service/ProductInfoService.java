package com.example.cache.service;

import com.example.cache.domain.ProductInfo;

/**
 * Created with IntelliJ IDEA.
 *
 * @author lgfcxx
 * @version 1.0
 * @desc
 * @createtime 2018/8/17 下午4:11
 * @see jdk 1.7
 **/
public interface ProductInfoService {

    ProductInfo saveProductForLocalCache(ProductInfo productInfo);

    ProductInfo getProductForLocalCache(Long pid);


    ProductInfo getProductForRedis(Long pid);

    void saveProductForRedisCache(ProductInfo productInfo);
}
