package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.servlet.filter.OrderedCharacterEncodingFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.filter.CharacterEncodingFilter;

import javax.annotation.Resource;

@RestController
@SpringBootApplication
public class CaseSpringBootDemoApplication {

    @Value("${com.demo.name}")
    private String name;

    @Resource
    private DemoBean demoBean;

    @RequestMapping(value = "/hello", produces = {"text/plain;charset=UTF-8"})
    public String getHello() {
        return "hello : " + name + " = " + demoBean.toString();
    }

    @Bean
    @ConditionalOnMissingBean(CharacterEncodingFilter.class)
    public CharacterEncodingFilter characterEncodingFilter() {
        CharacterEncodingFilter filter = new OrderedCharacterEncodingFilter();
        filter.setEncoding("utf-8");
        filter.setForceRequestEncoding(true);
        filter.setForceResponseEncoding(true);
        return filter;
    }

    public static void main(String[] args) {
        SpringApplication.run(CaseSpringBootDemoApplication.class, args);
    }
}
