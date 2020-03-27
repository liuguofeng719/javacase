package com.example.configuration.springbootconfigurationdemo;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author guofeng
 * @version 1.0
 * @see jdk 1.8
 **/
@ConfigurationProperties(prefix = "spring.datasource.druid")
public class BeeConfigurationOne implements InitializingBean {
    private String name;
    private String pwd;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("====" + this.name+ "="+this.pwd);
    }
}
