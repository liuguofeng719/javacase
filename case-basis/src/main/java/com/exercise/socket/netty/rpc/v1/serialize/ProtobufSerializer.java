package com.exercise.socket.netty.rpc.v1.serialize;

import com.exercise.socket.netty.rpc.v1.RpcException;
import com.exercise.socket.netty.rpc.v1.RpcRequest;
import com.exercise.socket.netty.rpc.v1.Serializer;
import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class ProtobufSerializer implements Serializer {
    private static Objenesis objenesis = new ObjenesisStd(true);
    private static Map<Class<?>, Schema<?>> cachedSchema = new ConcurrentHashMap<Class<?>, Schema<?>>();

    private static <T> Schema<T> getSchema(Class<T> cls) {
        @SuppressWarnings("unchecked")
        Schema<T> schema = (Schema<T>) cachedSchema.get(cls);
        if (schema == null) {
            schema = RuntimeSchema.createFrom(cls);
            if (schema != null) {
                cachedSchema.put(cls, schema);
            }
        }
        return schema;
    }

    @Override
    public <T> byte[] serialize(T obj) {
        @SuppressWarnings("unchecked")
        Class<T> cls = (Class<T>) obj.getClass();
        LinkedBuffer buffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);
        try {
            Schema<T> schema = getSchema(cls);
            return ProtostuffIOUtil.toByteArray(obj, schema, buffer);
        } catch (Exception e) {
            throw new RpcException(e.getMessage(), e);
        } finally {
            buffer.clear();
        }
    }

    @Override
    public <T> Object deserialize(byte[] bytes, Class<T> clazz) {
        try {
            T message = objenesis.newInstance(clazz);
            Schema<T> schema = getSchema(clazz);
            ProtostuffIOUtil.mergeFrom(bytes, message, schema);
            return message;
        } catch (Exception e) {
            throw new RpcException(e.getMessage(), e);
        }
    }

    public static void main(String[] args) {
        RpcRequest request = new RpcRequest();
        request.setMethodName("add");
        request.setParam1(1.0);
        request.setParam2(3.0);

        ProtobufSerializer sr = new ProtobufSerializer();
        byte[] bytes = sr.serialize(request);
        System.out.println(bytes.length);

        RpcRequest rr = (RpcRequest) sr.deserialize(bytes, RpcRequest.class);

        System.out.println(rr.toString());
    }
}