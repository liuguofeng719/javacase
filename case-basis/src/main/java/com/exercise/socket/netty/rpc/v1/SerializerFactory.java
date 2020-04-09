package com.exercise.socket.netty.rpc.v1;


import com.exercise.socket.netty.rpc.v1.serialize.ProtobufSerializer;

public class SerializerFactory {

    public static Serializer getSerializer() {
        return new ProtobufSerializer();
    }

}
