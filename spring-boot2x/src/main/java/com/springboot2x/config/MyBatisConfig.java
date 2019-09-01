package com.springboot2x.config;

import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Repository;

/**
 * @author guofeng
 * @version 1.0
 * @desc
 * @createtime 2019/2/8 9:19 PM
 * @see jdk 1.7
 **/
@Configuration
public class MyBatisConfig {

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer msc = new MapperScannerConfigurer();
        msc.setSqlSessionFactoryBeanName("sqlSessionFactory");
        msc.setBasePackage("com.springboot2x.mapper");
        msc.setAnnotationClass(Repository.class);
        return msc;
    }
}
