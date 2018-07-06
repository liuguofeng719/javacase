package com.boot.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created with IntelliJ IDEA.
 *
 * @author lgfcxx
 * @version 1.0
 * @desc  自动配置
 * @createtime 2018/6/22 下午6:26
 * @see jdk 1.7
 **/
@Configuration
@EnableConfigurationProperties(HelloConfigProperties.class)
@ConditionalOnClass(HelloServiceImpl.class)
@ConditionalOnProperty(prefix = "hello", value = "enabled", matchIfMissing = true)
public class HelloServiceAutoConfiguration {

    @Autowired
    public HelloConfigProperties properties;

    @Bean
    @ConditionalOnMissingBean(HelloServiceImpl.class)
    public HelloServiceImpl helloService() {
        HelloServiceImpl helloService = new HelloServiceImpl();
        helloService.setMsg(properties.getMsg());
        return helloService;
    }
}
