package com.exercise.designpattern.pipeline.v2;

import lombok.extern.slf4j.Slf4j;

/**
 * @author guofeng
 * @version 1.0
 * @desc
 * @createtime 2019/9/1 12:05 PM
 * @see jdk 1.8
 **/
@Slf4j
public class ValveCheckImpl implements Valve {

    @Override
    public void invoke() {
        log.info("validate = {}", "ValveCheckImpl");
    }
}
