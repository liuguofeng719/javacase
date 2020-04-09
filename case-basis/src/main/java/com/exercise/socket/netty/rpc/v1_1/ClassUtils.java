package com.exercise.socket.netty.rpc.v1_1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class ClassUtils {


	private static final Logger logger = LoggerFactory.getLogger(ClassUtils.class);

	private static final Map<String, Class<?>> PRIMITIVE_CLASS = new HashMap<String, Class<?>>(1024);

	private static final Map<String, Class<?>> CLASS_CACHE = new HashMap<String, Class<?>>(1024);

	private static final Map<String, Method> METHOD_CACHE = new HashMap<String, Method>(1024);

	static {
		PRIMITIVE_CLASS.put("boolean", boolean.class);
		PRIMITIVE_CLASS.put("byte", byte.class);
		PRIMITIVE_CLASS.put("short", short.class);
		PRIMITIVE_CLASS.put("int", int.class);
		PRIMITIVE_CLASS.put("long", long.class);
		PRIMITIVE_CLASS.put("float", float.class);
		PRIMITIVE_CLASS.put("double", double.class);
		PRIMITIVE_CLASS.put("char", char.class);
		PRIMITIVE_CLASS.put("void", void.class);

		CLASS_CACHE.putAll(PRIMITIVE_CLASS);
	}

	public static Class<?> getClass(String className) throws ClassNotFoundException {
		Class<?> clazz = CLASS_CACHE.get(className);
		if (null != clazz) {
			return clazz;
		}
		synchronized (CLASS_CACHE) {
			if (null == CLASS_CACHE.get(className)) {
				clazz = PRIMITIVE_CLASS.get(className);
				if (null == clazz) {
					clazz = Class.forName(className);
				}
				CLASS_CACHE.put(className, clazz);
				return clazz;
			} else {
				return CLASS_CACHE.get(className);
			}
		}
	}
}
