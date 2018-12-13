package com.boot.start;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created with IntelliJ IDEA.
 *
 * @author lgfcxx
 * @version 1.0
 * @desc
 * @createtime 2018/10/6 下午12:31
 * @see jdk 1.7
 **/
public class SpringRestTemplate implements FactoryBean {

    private RestTemplate restTemplate = new RestTemplate();

    private TradeEnum.RestHttpEnum restHttpEnum;

    public Class interfaceInstance;

    public void setInterfaceInstance(Class interfaceInstance) {
        this.interfaceInstance = interfaceInstance;
    }

    public Object getObject() throws Exception {
        return Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[]{interfaceInstance}, new ProxyHandlerTemplate());
    }

    public Class<?> getObjectType() {
        return interfaceInstance;
    }

    public void setRestHttpEnum(TradeEnum.RestHttpEnum restHttpEnum) {
        this.restHttpEnum = restHttpEnum;
    }

    private class ProxyHandlerTemplate implements InvocationHandler {

        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (args != null && args.length > 0) {
                return restTemplate.postForObject(restHttpEnum.getServerUrl() + method.getName(), args[0], method.getReturnType());
            } else {
                return restTemplate.postForObject(restHttpEnum.getServerUrl() + method.getName(), null, method.getReturnType());
            }
        }
    }
}
