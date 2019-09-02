package com.exercise.designpattern.pipeline.v3;

import lombok.extern.slf4j.Slf4j;

/**
 * @author guofeng
 * @version 1.0
 * @desc
 * @createtime 2019/9/1 1:22 PM
 * @see jdk 1.8
 **/
@Slf4j
public class BasicValveImpl implements Valve {

    @Override
    public void invoke(ValveContext valveContext) {
        log.info("BasicValveImpl==={}", valveContext);
    }
}
