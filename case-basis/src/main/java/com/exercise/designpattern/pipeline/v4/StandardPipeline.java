package com.exercise.designpattern.pipeline.v4;

import java.util.ArrayList;

/**
 * @author guofeng
 * @version 1.0
 * @see jdk 1.8
 **/
public class StandardPipeline implements Pipeline {

    protected Valve basic;
    protected Valve first;

    public StandardPipeline() {
        this.basic = null;
        this.first = null;
    }

    protected void destroyInternal() {
        Valve[] valves = this.getValves();
        Valve[] arr = valves;
        int len = valves.length;
        for (int i = 0; i < len; ++i) {
            Valve valve = arr[i];
            this.removeValve(valve);
        }
    }

    @Override
    public Valve getBasic() {
        return this.basic;
    }

    @Override
    public void setBasic(Valve valve) {
        Valve oldBasic = this.basic;
        if (oldBasic != valve) {
            if (valve != null) {
                for (Valve current = this.first; current != null; current = current.getNext()) {
                    if (current.getNext() == oldBasic) {
                        current.setNext(valve);
                        break;
                    }
                }
                this.basic = valve;
            }
        }
    }

    @Override
    public void addValve(Valve valve) {
        if (this.first == null) {
            this.first = valve;
            valve.setNext(this.basic);
        } else {
            for (Valve current = this.first; current != null; current = current.getNext()) {
                if (current.getNext() == this.basic) {
                    current.setNext(valve);
                    valve.setNext(this.basic);
                    break;
                }
            }
        }
    }

    @Override
    public Valve[] getValves() {
        ArrayList<Valve> valveList = new ArrayList();
        Valve current = this.first;
        if (current == null) {
            current = this.basic;
        }

        while (current != null) {
            valveList.add(current);
            current = current.getNext();
        }

        return (Valve[]) valveList.toArray(new Valve[0]);
    }

    @Override
    public void removeValve(Valve valve) {
        Valve current;
        if (this.first == valve) {
            this.first = this.first.getNext();
            current = null;
        } else {
            current = this.first;
        }

        while (current != null) {
            if (current.getNext() == valve) {
                current.setNext(valve.getNext());
                break;
            }
            current = current.getNext();
        }

        if (this.first == this.basic) {
            this.first = null;
        }
    }

    @Override
    public Valve getFirst() {
        return this.first != null ? this.first : this.basic;
    }
}
