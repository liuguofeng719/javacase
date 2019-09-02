package com.exercise.designpattern.pipeline.v2;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * @author guofeng
 * @version 1.0
 * @desc
 * @createtime 2019/9/1 11:59 AM
 * @see jdk 1.8
 **/
public class StandardPipeline implements PipeLine {

    final List<Valve> valves = Lists.newCopyOnWriteArrayList();

    private Valve basicValve;

    @Override
    public void addValve(Valve valve) {
        valves.add(valve);
    }

    @Override
    public void setBasicValve(Valve valve) {
        this.basicValve = valve;
    }

    @Override
    public void removeValve(Valve valve) {
        valves.remove(valve);
    }

    @Override
    public void invoke() {
        for (final Valve valve : valves) {
            valve.invoke();
        }
        basicValve.invoke();
    }

    public static void main(String[] args) {
        PipeLine pipeLine = new StandardPipeline();
        pipeLine.setBasicValve(new ValvePrintlnImpl());
        pipeLine.addValve(new ValveCheckImpl());
        pipeLine.addValve(new ValveLogImpl());
        pipeLine.invoke();
    }
}
