package com.example.demo.core.aop;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Created with IntelliJ IDEA.
 *
 * @author lgfcxx
 * @version 1.0
 * @desc
 * @createtime 2018/5/31 下午4:47
 * @see jdk 1.7
 **/
@Configuration
@EnableAspectJAutoProxy  // 开启spring 对 aspect 的支持
@ComponentScan("com.example.demo.core.aop")
public class AopConfig {
}
