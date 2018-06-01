package com.example.demo.core.properties;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 *
 * @author lgfcxx
 * @version 1.0
 * @desc
 * @createtime 2018/5/31 下午5:07
 * @see jdk 1.7
 **/
@Configuration
@ComponentScan("com.example.demo.core.properties")
@PropertySource("classpath:test.properties")
public class ElConfig {

    @Value("I love you")
    public String normal;

    @Value("#{systemProperties['os.name']}")
    public String osName;

    @Value("classpath:test.properties")
    private Resource testResource;

    @Value("${test.author}")
    private String author;

    @Autowired
    private Environment environment;

    //为了使用PropertySource中的属性解析定义中的$ {...}占位符或@Value注释，
    // 必须注册PropertySourcesPlaceholderConfigurer。这在使用XML时自动发生，
    // 但在使用@Configuration类时必须使用静态@Bean方法显式注册。有关详细信息和示例，
    // 请参阅@ Configuration的javadoc的“使用外部化值”部分和@Bean的javadoc的“BeanFactoryPostProcessor
    // 返回的@Bean方法的注释”。
    @Bean
    public static PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    public void outPrint(){
        try {
            System.out.println(normal);
            System.out.println(osName);
            System.out.println(IOUtils.toString(testResource.getInputStream(),"UTF-8"));
            System.out.println(author);
            System.out.println(environment.getProperty("test.address"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
