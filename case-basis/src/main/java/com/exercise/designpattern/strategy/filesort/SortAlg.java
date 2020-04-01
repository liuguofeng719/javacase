package com.exercise.designpattern.strategy.filesort;

/**
 * @author guofeng
 * @version 1.0
 * @see jdk 1.8
 **/
public class SortAlg {

    private long start;
    private long end;
    private ISortAlg iSortAlg;

    public SortAlg(long start, long end, ISortAlg iSortAlg) {
        this.start = start;
        this.end = end;
        this.iSortAlg = iSortAlg;
    }

    public boolean isRange(long size) {
        return size > start && size < end;
    }

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public long getEnd() {
        return end;
    }

    public void setEnd(long end) {
        this.end = end;
    }

    public ISortAlg getiSortAlg() {
        return iSortAlg;
    }

    public void setiSortAlg(ISortAlg iSortAlg) {
        this.iSortAlg = iSortAlg;
    }
}
