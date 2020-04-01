package com.exercise.designpattern.strategy.filesort;

import com.exercise.designpattern.strategy.filesort.impl.ConcurrentExternalSortAlgImpl;
import com.exercise.designpattern.strategy.filesort.impl.ExternalSortAlgImpl;
import com.exercise.designpattern.strategy.filesort.impl.MapReduceSortImpl;
import com.exercise.designpattern.strategy.filesort.impl.QuickSortAlgImpl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author guofeng
 * @version 1.0
 * @see jdk 1.8
 **/
public class Sorter {

    public static final long GB = 1000 * 1000 * 1000;
    public static List<SortAlg> list = new ArrayList<>();

    static {
        list.add(new SortAlg(0, 6 * GB, SortFactory.getSortAlg(SortFactory.QUICKSORT)));
        list.add(new SortAlg(6 * GB, 10 * GB, SortFactory.getSortAlg(SortFactory.QUICKSORT)));
        list.add(new SortAlg(10 * GB, 100 * GB, SortFactory.getSortAlg(SortFactory.QUICKSORT)));
        list.add(new SortAlg(100 * GB, Long.MAX_VALUE, SortFactory.getSortAlg(SortFactory.QUICKSORT)));
    }

    /**
     * @param filePath
     * 第二次冲重构
     */
    public void sortFile3(String filePath) {
        File file = new File(filePath);
        final long length = file.length();
        ISortAlg iSortAlg = null;
        for (final SortAlg sortAlg : list) {
            if (sortAlg.isRange(length)) {
                iSortAlg = sortAlg.getiSortAlg();
                break;
            }
        }
        iSortAlg.sort(filePath);
    }

    /**
     * @param filePath
     * 第一次重构
     */
    public void sortFile2(String filePath) {
        File file = new File(filePath);
        final long length = file.length();
        ISortAlg iSortAlg;
        // [0, 6GB)
        if (length < 6 * GB) {
            iSortAlg = SortFactory.getSortAlg(SortFactory.QUICKSORT);
        } else if (length < 10 * GB) {
            // [6GB, 10GB)
            iSortAlg = SortFactory.getSortAlg(SortFactory.EXTERNALSORT);
        } else if (length < 100 * GB) {
            //[10GB, 100GB)
            iSortAlg = SortFactory.getSortAlg(SortFactory.CONCURRENTEXTERNALSORT);
        } else {
            iSortAlg = SortFactory.getSortAlg(SortFactory.MAPREDUCESORT);
        }
        iSortAlg.sort(filePath);
    }

    /**
     * @param filePath
     */
    public void sortFile1(String filePath) {
        File file = new File(filePath);
        final long length = file.length();
        ISortAlg iSortAlg;
        // [0, 6GB)
        if (length < 6 * GB) {
            iSortAlg = new QuickSortAlgImpl();
        } else if (length < 10 * GB) {
            // [6GB, 10GB)
            iSortAlg = new ExternalSortAlgImpl();
        } else if (length < 100 * GB) {
            //[10GB, 100GB)
            iSortAlg = new ConcurrentExternalSortAlgImpl();
        } else {
            iSortAlg = new MapReduceSortImpl();
        }
        iSortAlg.sort(filePath);
    }
}
