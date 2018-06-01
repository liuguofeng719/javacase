package com.example.demo.core.javaconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created with IntelliJ IDEA.
 *
 * @author lgfcxx
 * @version 1.0
 * @desc
 * @createtime 2018/5/31 下午4:20
 * @see jdk 1.7
 **/
@Configuration
public class JavaConfig {

    @Bean
    public FunctionSerivce functionSerivce() {
        return new FunctionSerivce();
    }

    @Bean
    public UserFunctionService userFunctionService(){
        final UserFunctionService userFunctionService = new UserFunctionService();
        userFunctionService.setFunctionSerivce(functionSerivce());
        return userFunctionService;
    }
}
