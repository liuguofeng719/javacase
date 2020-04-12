package com.exercise.socket.netty.rpc.v1_3;

import com.exercise.socket.netty.rpc.v1_3.serialize.RpcJsonSerialize;
import com.exercise.socket.netty.rpc.v1_3.serialize.RpcSerialize;

/**
 * @author guofeng
 * @version 1.0
 * @see jdk 1.8
 **/
public class RpcSerializeFactory {

    public static RpcSerialize getRpcSerialize() {
        return new RpcJsonSerialize();
    }
}
