package com.example.cache.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created with IntelliJ IDEA.
 *
 * @author lgfcxx
 * @version 1.0
 * @desc
 * @createtime 2018/8/8 下午3:23
 * @see jdk 1.7
 **/
@Configuration
@MapperScan("com.example.cache.mapper")
public class MybatisConfig {
    
}
