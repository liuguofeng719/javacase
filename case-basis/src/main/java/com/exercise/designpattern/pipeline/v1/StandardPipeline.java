package com.exercise.designpattern.pipeline.v1;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * @author guofeng
 * @version 1.0
 * @desc
 * @createtime 2019/9/1 11:47 AM
 * @see jdk 1.8
 **/
public class StandardPipeline implements Pipeline {

    final List<Valve> valves = Lists.newCopyOnWriteArrayList();

    public StandardPipeline() {
        valves.add(new ValveLogImpl());
        valves.add(new ValveValidateImpl());
    }

    @Override
    public void invoke() {
        for (final Valve valve : valves) {
            valve.invoke();
        }
    }

    public static void main(String[] args) {
        Pipeline pipeline = new StandardPipeline();
        pipeline.invoke();
    }
}
