package com.boot.starter.service;

import com.boot.starter.domain.ProductInventory;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author lgfcxx
 * @version 1.0
 * @desc
 * @createtime 2018/6/26 下午4:59
 * @see jdk 1.7
 **/
public interface ProductInventoryService {
    List<ProductInventory> getProductInventory();
    List<ProductInventory> getProductById(Integer pid);
    void add(ProductInventory productInventory);
}
