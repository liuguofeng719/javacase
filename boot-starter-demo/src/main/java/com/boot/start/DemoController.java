package com.boot.start;

import com.boot.demo.HelloServiceImpl;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 *
 * @author lgfcxx
 * @version 1.0
 * @desc
 * @createtime 2018/6/25 上午11:25
 * @see jdk 1.7
 **/
@RestController
public class DemoController {

    @Resource
    private HelloServiceImpl helloService;

    @RequestMapping("/msg")
    public String getMessage() {
        final String msg = helloService.getMsg();
        return msg;
    }
}
