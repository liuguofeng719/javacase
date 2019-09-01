package com.springboot2x.config;

import com.springboot2x.aop.GreetToBeforeAdvice;
import com.springboot2x.aop.GreetingInterceptor;
import com.springboot2x.aop.Waiter;
import com.springboot2x.aop.WaiterImpl;
import org.aopalliance.aop.Advice;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author guofeng
 * @version 1.0
 * @desc
 * @createtime 2019/2/16 4:14 PM
 * @see jdk 1.7
 **/
@Configuration
public class AopConfig {

    @Bean
    public Waiter getWaiterImpl(){
        return new WaiterImpl();
    }

    @Bean(name = "greetBeforeAdvice")
    public Advice getAdvice(){
        return new GreetToBeforeAdvice();
    }

    @Bean(name = "methodInterceptor")
    public GreetingInterceptor getInterceptor(){
        return new GreetingInterceptor();
    }

    @Bean(name = "waiter")
    public ProxyFactoryBean getProxyFactoryBean()  {
        ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
        try {
            proxyFactoryBean.setProxyInterfaces(WaiterImpl.class.getInterfaces());
            proxyFactoryBean.setInterceptorNames("greetBeforeAdvice");//这里字符串，就是xml里面的id，如果是多用逗号隔开
            proxyFactoryBean.setTarget(getWaiterImpl());
            proxyFactoryBean.setOptimize(true);//强制使用cglib 代理
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return proxyFactoryBean;
    }

    @Bean(name = "waiter1")
    public ProxyFactoryBean getProxyFactoryBean1()  {
        ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
        try {
            proxyFactoryBean.setProxyInterfaces(WaiterImpl.class.getInterfaces());
            proxyFactoryBean.setInterceptorNames("methodInterceptor");//这里字符串，就是xml里面的id，如果是多用逗号隔开
            proxyFactoryBean.setTarget(getWaiterImpl());
//            proxyFactoryBean.setOptimize(true);//强制使用cglib 代理
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return proxyFactoryBean;
    }
}
