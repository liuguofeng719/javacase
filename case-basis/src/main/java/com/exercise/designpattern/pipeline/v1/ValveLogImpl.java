package com.exercise.designpattern.pipeline.v1;

import lombok.extern.slf4j.Slf4j;

/**
 * @author guofeng
 * @version 1.0
 * @desc
 * @createtime 2019/9/1 11:47 AM
 * @see jdk 1.8
 **/
@Slf4j
public class ValveLogImpl implements Valve {

    @Override
    public void invoke() {
        log.info("valve = {}", "记录日志");
    }
}
