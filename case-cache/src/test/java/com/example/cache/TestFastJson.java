package com.example.cache;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.example.cache.domain.ProductInfo;

import org.junit.Test;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 *
 * @author lgfcxx
 * @version 1.0
 * @desc
 * @createtime 2018/8/28 下午6:31
 * @see jdk 1.7
 **/
public class TestFastJson {

    @Test
    public void testFastjson(){
        ProductInfo productInfo = new ProductInfo();
        productInfo.setId(1L);
        productInfo.setName("iphone7手机");
        productInfo.setPrice(5599.00);
        productInfo.setPictureList("a.jpg,b.jpg");
        productInfo.setSpecification("iphone7的规格");
        productInfo.setService("iphone7的售后服务");
        productInfo.setColor("红色,白色,黑色");
        productInfo.setSize("5.5");
        productInfo.setShopId(1L);
        productInfo.setModifiedTime(new Date());

        final String toJSONString = JSON.toJSONString(productInfo, SerializerFeature.PrettyFormat, SerializerFeature.WriteDateUseDateFormat);
        System.out.println(toJSONString);

        final ProductInfo productInfo1 = JSON.parseObject(toJSONString, ProductInfo.class);
        System.out.println(productInfo1);
    }
}
