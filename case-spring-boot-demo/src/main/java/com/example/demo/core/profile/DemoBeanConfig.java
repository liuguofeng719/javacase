package com.example.demo.core.profile;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Created with IntelliJ IDEA.
 *
 * @author lgfcxx
 * @version 1.0
 * @desc
 * @createtime 2018/6/1 下午3:04
 * @see jdk 1.7
 **/
@Configuration
//@ComponentScan("com.example.demo.core.profile")
public class DemoBeanConfig {

    @Bean
    @Profile("pro")
    public DemoBean getProductBean() {
        return new DemoBean("hello  product");
    }

    @Bean
    @Profile("dev")
    public DemoBean getDevBean() {
        return new DemoBean("hello dev");
    }
}
