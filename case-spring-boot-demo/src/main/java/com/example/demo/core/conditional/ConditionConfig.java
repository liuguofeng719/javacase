package com.example.demo.core.conditional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * Created with IntelliJ IDEA.
 *
 * @author lgfcxx
 * @version 1.0
 * @desc
 * @createtime 2018/6/13 下午6:09
 * @see jdk 1.7
 **/
@Configuration
public class ConditionConfig {

    @Bean
    @Conditional(WindowsCondtional.class)
    public WindowsListShowCmdServiceImpl getWindows() {
        return new WindowsListShowCmdServiceImpl();
    }

    @Bean
    @Conditional(MacConditional.class)
    public MacListShowCmdServiceImpl getMac() {
        return new MacListShowCmdServiceImpl();
    }
}
