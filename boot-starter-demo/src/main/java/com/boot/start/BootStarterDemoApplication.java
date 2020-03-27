package com.boot.start;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath*:spring-rest-web.xml")
public class BootStarterDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootStarterDemoApplication.class, args);
	}
}
