package com.example.demo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Created with IntelliJ IDEA.
 *
 * @author lgfcxx
 * @version 1.0
 * @desc
 * @createtime 2018/5/21 上午11:55
 * @see jdk 1.7
 **/
@Configuration
@ConfigurationProperties(prefix = "com.demo")
@PropertySource("classpath:conf.properties")
public class DemoBean {

    private String namex;
    private String pwd;
    private String secret;
    private Integer number;
    private Long bignumber;
    private String uuid;
    private Integer randInt;
    private Integer randRange;

    @Override
    public String toString() {
        return "DemoBean{" +
                "namex='" + namex + '\'' +
                ", pwd='" + pwd + '\'' +
                ", secret='" + secret + '\'' +
                ", number=" + number +
                ", bignumber=" + bignumber +
                ", uuid='" + uuid + '\'' +
                ", randInt=" + randInt +
                ", randRange=" + randRange +
                '}';
    }

    public Integer getRandInt() {
        return randInt;
    }

    public void setRandInt(Integer randInt) {
        this.randInt = randInt;
    }

    public Integer getRandRange() {
        return randRange;
    }

    public void setRandRange(Integer randRange) {
        this.randRange = randRange;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Long getBignumber() {
        return bignumber;
    }

    public void setBignumber(Long bignumber) {
        this.bignumber = bignumber;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getNamex() {
        return namex;
    }

    public void setNamex(String namex) {
        this.namex = namex;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

}
