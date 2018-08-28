package com.example.cache.domain;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 *
 * @author lgfcxx
 * @version 1.0
 * @desc
 * @createtime 2018/6/26 下午4:58
 * @see jdk 1.7
 **/
public class ProductInventory implements Serializable {

    private Integer productId;
    private Integer inventoryCnt;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getInventoryCnt() {
        return inventoryCnt;
    }

    public void setInventoryCnt(Integer inventoryCnt) {
        this.inventoryCnt = inventoryCnt;
    }

    @Override
    public String toString() {
        return "ProductInventory{" +
                "productId=" + productId +
                ", inventoryCnt=" + inventoryCnt +
                '}';
    }
}
