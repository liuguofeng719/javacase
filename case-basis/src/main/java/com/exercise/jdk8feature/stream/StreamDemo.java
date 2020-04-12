package com.exercise.jdk8feature.stream;

import com.google.common.collect.Lists;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author guofeng
 * @version 1.0
 * @see jdk 1.8
 **/
public class StreamDemo {
    public static void main(String[] args) throws Exception {
        List<String> strings = Lists.newArrayList();
        strings.add("23");
        strings.add("24");
        strings.add("25");
        strings.add("26");
        System.out.println(strings.stream().max(Comparator.comparingInt(s -> Integer.valueOf(s))).get());
        System.out.println("-==============");
        strings.stream().map(s -> Integer.valueOf(s) * 2).collect(Collectors.toList()).forEach(System.out::println);
        System.out.println("===========");
        System.out.println(strings.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList()));

        Optional<Integer> optional = Optional.ofNullable(null);
        // 如果值存在则方法会返回true，否则返回 false。
        System.out.println(optional.isPresent());
        // 如果存在该值，返回值， 否则触发 other，并返回 other 调用的结果。
        System.out.println(optional.orElseGet(() -> new Integer(22)));

        //如果存在该值，返回包含的值，否则抛出由 Supplier 继承的异常
        optional.orElseThrow(() -> new ArithmeticException("22"));

        System.out.println(optional.orElse(1));
        // 返回一个指定非null值的Optional。s
        final Optional<Integer> integer = Optional.of(new Integer(1));
        // 如果存在该值，返回值，否则返回 other。
        System.out.println(integer.orElse(2));
        System.out.println(integer.get());
        //如果值存在则使用该值调用 consumer , 否则不做任何事情。
        integer.ifPresent(integer1 -> {
            System.out.println("==== " + integer1);
        });

    }
}
