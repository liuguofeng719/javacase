package com.boot.starter.service.impl;

import com.boot.starter.domain.ProductInventory;
import com.boot.starter.mapper.ProductInventoryMapper;
import com.boot.starter.service.ProductInventoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author lgfcxx
 * @version 1.0
 * @desc
 * @createtime 2018/6/26 下午5:16
 * @see jdk 1.7
 **/
@Service
public class ProductInventoryServiceImpl implements ProductInventoryService {

    @Autowired
    ProductInventoryMapper mapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    @Cacheable(value = "product")
    public List<ProductInventory> getProductInventory() {
        return mapper.getProductInventory();
    }

    @Override
    @Cacheable(value = "product", key = "#pid")
    public List<ProductInventory> getProductById(Integer pid) {
//        final List<ProductInventory> inventories = mapper.getProductById(pid);
//        FastJsonRedisSerializer serializer = new FastJsonRedisSerializer(Object.class);
//        redisTemplate.setKeySerializer(serializer);
//        redisTemplate.setValueSerializer(serializer);
//        final Object o = redisTemplate.opsForValue().get("product:" + pid);
//        if (o == null) {
//            redisTemplate.opsForValue().set("product:" + pid, JSON.toJSONString(inventories));
//        } else {
//            final List<ProductInventory> productInventories = JSON.parseArray(o.toString(), ProductInventory.class);
//            return productInventories;
//        }
        return mapper.getProductById(pid);
    }

    @Override
    @Transactional
    public void add(ProductInventory productInventory) {
        mapper.add(productInventory);
        final int i = 1 / 0;
    }
}
