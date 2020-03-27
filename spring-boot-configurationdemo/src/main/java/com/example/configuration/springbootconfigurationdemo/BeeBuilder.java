package com.example.configuration.springbootconfigurationdemo;

/**
 * @author guofeng
 * @version 1.0
 * @see jdk 1.8
 **/
public class BeeBuilder {

    public static BeeBuilder create(){
        return new BeeBuilder();
    }

    public  BeeConfigurationOne builder(){
        return new BeeConfigurationOne();
    }
}
