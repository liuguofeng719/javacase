package com.springboot2x;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
//@MapperScan(basePackages = "com.springboot2x.mapper",annotationClass = Repository.class)
public class Springboot2xApplication  {
    public static void main(String[] args) {
        SpringApplication.run(Springboot2xApplication.class, args);
    }
}

