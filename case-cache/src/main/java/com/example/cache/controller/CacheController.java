package com.example.cache.controller;

import com.example.cache.domain.ProductInfo;
import com.example.cache.domain.ShopInfo;
import com.example.cache.rebuildcache.ReBuildCacheQueue;
import com.example.cache.service.ProductInfoService;
import com.example.cache.service.ShopInfoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 *
 * @author lgfcxx
 * @version 1.0
 * @desc
 * @createtime 2018/8/23 上午10:22
 * @see jdk 1.7
 **/
@Controller
public class CacheController {

    @Autowired
    private ShopInfoService shopInfoService;

    @Autowired
    private ProductInfoService productInfoService;

    /**
     * @desc 如果redis 拿不到数据，在堆缓存里面拿，如果不到就查询数据库
     */
    @RequestMapping("/getProductInfo")
    @ResponseBody
    public ProductInfo getProductInfo(Long productId) {

        ProductInfo productInfo = productInfoService.getProductForRedis(productId);
        if (productInfo != null) {
            System.out.println("=============获取redis中的Product缓存：" + productInfo);
            return productInfo;
        }

        productInfo = productInfoService.getProductForLocalCache(productId);
        if (productInfo != null) {
            System.out.println("=============获取LocalCache中的Product缓存：" + productInfo);
            return productInfo;
        }
        //查询数据库,重建缓存，暂时不实现
        final ReBuildCacheQueue<ProductInfo> instance = ReBuildCacheQueue.getInstance();

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
        instance.put(productInfo);
        return productInfo;
    }

    @RequestMapping("/getShopInfo")
    @ResponseBody
    public ShopInfo getShopInfo(Long shopId) {

        ShopInfo shopInfo = shopInfoService.getShopInfoForRedis(shopId);
        if (shopInfo != null) {
            System.out.println("=============获取redis中的Shop缓存：" + shopInfo);
            return shopInfo;
        }

        shopInfo = shopInfoService.getShopInfoForLocalCache(shopId);
        if (shopInfo != null) {
            System.out.println("=============获取redis中的Shop缓存：" + shopInfo);
            return shopInfo;
        }

        //查询数据库,重建缓存，暂时不实现
        return null;
    }
}
