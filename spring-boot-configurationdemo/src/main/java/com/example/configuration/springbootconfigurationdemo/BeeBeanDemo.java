package com.example.configuration.springbootconfigurationdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author guofeng
 * @version 1.0
 * @see jdk 1.8
 **/
@Component
public class BeeBeanDemo {
    @Autowired
    BeeConfigurationOne beeConfigurationOne;

    @PostConstruct
    public void show() {
        System.out.println(beeConfigurationOne.getName() + "=" + beeConfigurationOne.getPwd());
    }

}
