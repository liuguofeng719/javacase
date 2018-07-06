package com.boot.starter.web;

import com.boot.starter.domain.ProductInventory;
import com.boot.starter.service.ProductInventoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created with IntelliJ IDEA.
 *
 * @author lgfcxx
 * @version 1.0
 * @desc
 * @createtime 2018/6/25 下午4:42
 * @see jdk 1.7
 **/
@Controller
public class IndexController {

    @Autowired
    ProductInventoryService inventoryService;

    @RequestMapping("/")
    public String index(Model model) {
        for (final ProductInventory productInventory : inventoryService.getProductInventory()) {
            System.out.println(productInventory);
        }
        model.addAttribute("userName", "张三2");
        return "index";
    }

    @RequestMapping("/getProductById/{pid}/")
    public String getProductById(@PathVariable("pid") Integer pid) {
        System.out.println(inventoryService.getProductById(pid));
        return "index";
    }


    @RequestMapping("/add")
    @ResponseBody
    public void add() {
        ProductInventory productInventory = new ProductInventory();
        productInventory.setProductId(2);
        productInventory.setInventoryCnt(20);
        inventoryService.add(productInventory);
    }
}
