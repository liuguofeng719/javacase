package com.boot.starter.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created with IntelliJ IDEA.
 *
 * @author lgfcxx
 * @version 1.0
 * @desc
 * @createtime 2018/7/4 下午5:28
 * @see jdk 1.7
 **/
public class LogBackDemo {

    private final static Logger logger = LoggerFactory.getLogger(LogBackDemo.class);

    public static void main(String[] args) {
        logger.trace("trace");
        logger.debug("debug");
        logger.info("info");
        logger.warn("warn");
        logger.error("error");
    }
}
