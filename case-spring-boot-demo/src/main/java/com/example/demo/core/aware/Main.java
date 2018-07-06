package com.example.demo.core.aware;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created with IntelliJ IDEA.
 *
 * @author lgfcxx
 * @version 1.0
 * @desc
 * 1,BeanNameAware 获取容器bean的名字
 * 2、BeanFactoryAware 获取当前BeanFactory，这样可以调用容器的服务
 * 3、ApplicationContextAware 当前的Application Context ,这样可以调用容器的服务
 * 4、MessageSourceAware 获取Message Source，这样可以获取文本信息
 * 5、ApplicationEventPublisherAware 应用事件发布器，可以发布事件，在event的demo中可以通过这个接口发布事件
 * 6、ResourceLoaderAware 获取资源加载器，可以获取加载外部资源文件
 * @createtime 2018/6/1 下午4:05
 * @see jdk 1.7
 **/
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AwareConfig.class);
        final AwareService bean = context.getBean(AwareService.class);
        bean.outPrint();
        context.close();
    }
}
