package com.example.configuration.springbootconfigurationdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringBootConfigurationdemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootConfigurationdemoApplication.class, args);
    }

    @Bean
//    @ConfigurationProperties("spring.demo")
    public BeeConfigurationOne show(){
        return BeeBuilder.create().builder();
    }
}
