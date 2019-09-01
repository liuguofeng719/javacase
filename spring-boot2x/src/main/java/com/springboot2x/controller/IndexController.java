package com.springboot2x.controller;

import com.springboot2x.domain.User;
import com.springboot2x.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author guofeng
 * @version 1.0
 * @desc
 * @createtime 2019/2/4 10:33 AM
 * @see jdk 1.7
 **/
@Controller
public class IndexController {

    @Autowired
    private UserService userService;

    @RequestMapping("/index")
    public String index() {
        return "test";
    }

    @RequestMapping("/user/{id}")
    @ResponseBody
    public User getUserId(@PathVariable("id") long id) {
        return userService.getUserById(id);
    }
}
