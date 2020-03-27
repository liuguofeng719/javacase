package com.example.configuration.springbootconfigurationdemo;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author guofeng
 * @version 1.0
 * @see jdk 1.8
 **/
@Data
@Component
//@ConfigurationProperties("spring.demo")
public class BeeConfiguration {
    @Autowired
    private BeeConfigurationOne beeConfigurationOne;
    private String name;
    private String pwd;
}
