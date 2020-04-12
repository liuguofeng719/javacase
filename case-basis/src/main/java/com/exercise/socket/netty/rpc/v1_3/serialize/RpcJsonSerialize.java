package com.exercise.socket.netty.rpc.v1_3.serialize;

import com.alibaba.fastjson.JSON;

/**
 * @author guofeng
 * @version 1.0
 * @see jdk 1.8
 **/
public class RpcJsonSerialize implements RpcSerialize {

    @Override
    public <T> byte[] encode(T data) {
        return JSON.toJSONBytes(data);
    }

    @Override
    public <T> T decode(byte[] data, T clzz) {
        return JSON.parseObject(data, clzz.getClass());
    }
}
