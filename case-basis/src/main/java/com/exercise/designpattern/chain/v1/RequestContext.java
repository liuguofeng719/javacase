package com.exercise.designpattern.chain.v1;

import lombok.ToString;

/**
 * @author guofeng
 * @version 1.0
 * @see jdk 1.8
 **/
@ToString
public class RequestContext {
    private String type;
    private String content;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
