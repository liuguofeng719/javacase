package com.demo.springboot;

import com.demo.springboot.validator.UserVo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Hello world!
 */
@RestController
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
public class App {

    @RequestMapping("/user")
    public String sayHello(@Valid @RequestBody UserVo userVo, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            for (final ObjectError objectError : bindingResult.getAllErrors()) {
                System.out.println(objectError.getDefaultMessage());
            }
        }
        return "sayhello";
    }

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
