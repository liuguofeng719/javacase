package com.springboot2x.aware;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

/**
 * @author guofeng
 * @version 1.0
 * @desc
 * @createtime 2019/2/8 8:28 PM
 * @see jdk 1.7
 **/
@Component
public class DataSourceAware implements ApplicationContextAware {

    @Autowired
    private ApplicationContext applicationContext ;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        final DataSource dataSource = applicationContext.getBean(DataSource.class);
        System.out.println("dataSource = [" + dataSource.getClass().getName() + "]");
    }
}
