package com.exercise.designpattern.chain.v2.model;

import lombok.Builder;
import lombok.Data;

/**
 * @author guofeng
 * @version 1.0
 * @see jdk 1.8
 **/
@Data
@Builder
public class RequestInput {
    private String content;
    private String uid;
}
