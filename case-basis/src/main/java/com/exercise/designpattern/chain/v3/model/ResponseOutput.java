package com.exercise.designpattern.chain.v3.model;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

/**
 * @author guofeng
 * @version 1.0
 * @see jdk 1.8
 **/
@Data
@Builder
@ToString
public class ResponseOutput {
    private String content;
}
