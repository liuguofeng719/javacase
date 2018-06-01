package com.example.demo.core.beaninit;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created with IntelliJ IDEA.
 *
 * @author lgfcxx
 * @version 1.0
 * @desc
 * @createtime 2018/6/1 下午2:53
 * @see jdk 1.7
 **/
@Configuration
@ComponentScan("com.example.demo.core.beaninit")
public class BeanConfig {

    //通过bean 实现初始化或销毁 和xml 一样
    @Bean(initMethod = "init", destroyMethod = "destroy")
    public UserBean getUser() {
        return new UserBean();
    }

    @Bean
    public JSR250WayService getWayService() {
        return new JSR250WayService();
    }
}
