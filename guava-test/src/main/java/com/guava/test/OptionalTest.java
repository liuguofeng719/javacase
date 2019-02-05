package com.guava.test;

import com.google.common.base.Optional;
import org.junit.jupiter.api.Test;

/**
 * @author guofeng
 * @version 1.0
 * @desc
 * @createtime 2018/12/28 5:13 PM
 * @see jdk 1.7
 **/
public class OptionalTest {

    private final static String KEY_ = "122";

    @Test
    public void ofTest() {
        final Optional<Integer> optional = Optional.fromNullable(null);
        System.out.println(optional.isPresent());
    }
}
