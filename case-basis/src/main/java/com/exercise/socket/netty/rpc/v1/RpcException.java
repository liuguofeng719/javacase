package com.exercise.socket.netty.rpc.v1;

public class RpcException extends RuntimeException {

    public RpcException(String msg) {
        super(msg);
    }

    public RpcException(String message, Exception e) {
        super(message, e);
    }

}
