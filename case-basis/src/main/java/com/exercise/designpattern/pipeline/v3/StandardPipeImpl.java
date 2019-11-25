package com.exercise.designpattern.pipeline.v3;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author guofeng
 * @version 1.0
 * @desc
 * @createtime 2019/9/1 12:34 PM
 * @see jdk 1.8
 **/
public class StandardPipeImpl implements Pipe {

    final List<Valve> valves = Lists.newCopyOnWriteArrayList();

    private Valve basicValve;

    private ValveContext standardContext;

    public StandardPipeImpl() {
        standardContext = new StandardContextImpl();
    }

    @Override
    public void addValve(Valve valve) {
        valves.add(valve);
    }

    @Override
    public void removeValve(Valve valve) {
        valves.remove(valve);
    }

    @Override
    public void setBasicValve(Valve valve) {
        this.basicValve = valve;
    }

    @Override
    public void invoke() {
        standardContext.invokeNext();
    }

    private class StandardContextImpl implements ValveContext {

         AtomicInteger step = new AtomicInteger(0);

        @Override
        public void invokeNext() {
            if (!valves.isEmpty() && step.get() < valves.size()) {
                valves.get(step.getAndIncrement()).invoke(standardContext);
            } else {
                basicValve.invoke(standardContext);
            }
        }
    }

    public static void main(String[] args) {
        Pipe pipe = new StandardPipeImpl();
        pipe.addValve(new ValveLogImpl());
        pipe.addValve(new ValveCheckParamsImpl());
        pipe.setBasicValve(new BasicValveImpl());
        pipe.invoke();
    }
}
