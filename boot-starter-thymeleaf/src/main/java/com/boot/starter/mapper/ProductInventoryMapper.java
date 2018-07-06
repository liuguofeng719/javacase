package com.boot.starter.mapper;

import com.boot.starter.domain.ProductInventory;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author lgfcxx
 * @version 1.0
 * @desc
 * @createtime 2018/6/26 下午4:58
 * @see jdk 1.7
 **/
public interface ProductInventoryMapper {

    List<ProductInventory> getProductInventory();

    void add(ProductInventory productInventory);

    List<ProductInventory> getProductById(@Param("pid") Integer pid);
}
