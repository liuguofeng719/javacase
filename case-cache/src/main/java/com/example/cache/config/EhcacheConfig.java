package com.example.cache.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

/**
 * Created with IntelliJ IDEA.
 *
 * @author lgfcxx
 * @version 1.0
 * @desc
 * @createtime 2018/8/8 下午4:44
 * @see jdk 1.7
 **/
@Configuration
@EnableCaching
public class EhcacheConfig {

    @Bean
    public EhCacheManagerFactoryBean factoryBean(){
        EhCacheManagerFactoryBean factoryBean = new EhCacheManagerFactoryBean();
        factoryBean.setConfigLocation(new ClassPathResource("ehcache.xml"));
        factoryBean.setShared(true);
        return factoryBean;
    }

    @Bean
    public EhCacheCacheManager cacheManager(EhCacheManagerFactoryBean factoryBean){
        return new EhCacheCacheManager(factoryBean.getObject());
    }
}
