package com.exercise.designpattern.chain.v3;

import com.exercise.designpattern.chain.v2.model.RequestInput;
import com.exercise.designpattern.chain.v2.model.ResponseOutput;

import java.util.HashMap;

/**
 * @author guofeng
 * @version 1.0
 * @see jdk 1.8
 * filter 模式
 **/
public class App {

    public static void main(String[] args) {
        Filter filter = new ValidateFilter(new AuthenticationFilter(new Target()));
        filter.executor(RequestInput.builder().build(),ResponseOutput.builder().build() );

        HashMap<String,String> map = new HashMap<>();
        map.put("1","2" );
        map.put("1","3" );
        System.out.println(map.get("1"));

        System.out.println(1^1);
        System.out.println(1^0);

        System.out.println(Integer.highestOneBit(7));
        System.out.println(Integer.highestOneBit(16));
        System.out.println(Integer.highestOneBit((17-1)<<1));
    }
}
