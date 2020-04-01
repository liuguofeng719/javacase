package com.exercise.designpattern.strategy.filesort;

import com.exercise.designpattern.strategy.filesort.impl.ConcurrentExternalSortAlgImpl;
import com.exercise.designpattern.strategy.filesort.impl.ExternalSortAlgImpl;
import com.exercise.designpattern.strategy.filesort.impl.MapReduceSortImpl;
import com.exercise.designpattern.strategy.filesort.impl.QuickSortAlgImpl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author guofeng
 * @version 1.0
 * @see jdk 1.8
 **/
public class SortFactory {
    public static final String QUICKSORT = "quickSort";
    public static final String EXTERNALSORT = "externalSort";
    public static final String CONCURRENTEXTERNALSORT = "concurrentExternalSort";
    public static final String MAPREDUCESORT = "mapReduceSort";

    private static Map<String, ISortAlg> sortAlgMap = new ConcurrentHashMap<>();
    static {
        sortAlgMap.put("quickSort", new QuickSortAlgImpl());
        sortAlgMap.put("externalSort", new ExternalSortAlgImpl());
        sortAlgMap.put("concurrentExternalSort", new ConcurrentExternalSortAlgImpl());
        sortAlgMap.put("mapReduceSort", new MapReduceSortImpl());
    }

    public static ISortAlg getSortAlg(String sortType){
        return sortAlgMap.get(sortType);
    }
}
