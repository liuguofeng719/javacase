package com.boot.starter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication
@MapperScan("com.boot.starter.mapper")
public class BootStarterThymeleafApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootStarterThymeleafApplication.class, args);
	}
}
