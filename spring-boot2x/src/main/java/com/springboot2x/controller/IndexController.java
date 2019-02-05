package com.springboot2x.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author guofeng
 * @version 1.0
 * @desc
 * @createtime 2019/2/4 10:33 AM
 * @see jdk 1.7
 **/
@Controller
public class IndexController {

    @RequestMapping("/index")
    public String index() {
        return "test";
    }
}
