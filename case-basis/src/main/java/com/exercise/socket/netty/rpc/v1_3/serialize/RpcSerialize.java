package com.exercise.socket.netty.rpc.v1_3.serialize;

/**
 * @author guofeng
 * @version 1.0
 * @see jdk 1.8
 **/
public interface RpcSerialize {

    <T> byte[] encode(T data);

    <T> T decode(byte[] data, T clzz);
}
