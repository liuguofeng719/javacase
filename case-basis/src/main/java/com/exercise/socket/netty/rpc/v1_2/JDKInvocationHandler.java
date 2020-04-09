package com.exercise.socket.netty.rpc.v1_2;

import com.exercise.socket.netty.rpc.v1_1.NettyClient;
import com.exercise.socket.netty.rpc.v1_1.RpcRequest;
import com.exercise.socket.netty.rpc.v1_1.RpcResponse;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.SynchronousQueue;

public class JDKInvocationHandler implements InvocationHandler {
	private NettyClient client;
	private Class<?> proxyClass;

	public JDKInvocationHandler(Class<?> proxyClass, NettyClient client) {
		this.proxyClass = proxyClass;
		this.client = client;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] paramValues) throws Throwable {
		String methodName = method.getName();
		Class<?>[] paramTypes = method.getParameterTypes();
		if ("toString".equals(methodName) && paramTypes.length == 0) {
			return client.toString();
		} else if ("hashCode".equals(methodName) && paramTypes.length == 0) {
			return client.hashCode();
		} else if ("equals".equals(methodName) && paramTypes.length == 1) {
			Object another = paramValues[0];
			return proxy == another
					|| (proxy.getClass().isInstance(another) && client.equals(parseInvoker(another)));
		}


		RpcRequest request = buildRequest(proxyClass.getName(), method, paramValues);
		RpcResponse response = sendRPCRequest(request);

		return response.getResult();
	}


	private RpcResponse sendRPCRequest(RpcRequest request) {
		SynchronousQueue<RpcResponse> queue = new SynchronousQueue<>();
		NettyClient.putSunchronousQuee(request.getId(), queue);
		RpcResponse response = null;
		try {
			client.sendRpcRequest(request);
			response = queue.take();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return response;
	}

	public static NettyClient parseInvoker(Object proxyObject) {
		InvocationHandler handler = java.lang.reflect.Proxy.getInvocationHandler(proxyObject);
		if (handler instanceof JDKInvocationHandler) {
			return ((JDKInvocationHandler) handler).getProxyInvoker();
		}
		return null;
	}

	public Class<?> getProxyClass() {
		return proxyClass;
	}

	public NettyClient getProxyInvoker() {
		return client;
	}

	private RpcRequest buildRequest(String serviceName, Method method, Object[] args) {

		String id = UUID.randomUUID().toString();

		RpcRequest request = new RpcRequest();
		request.setServiceName(serviceName);

		request.setId(id);
		request.setMethodName(method.getName());
		request.setArgs(args);

		List<String> parameterTypes = new ArrayList<String>();
		for (Class<?> parameterType : method.getParameterTypes()) {
			parameterTypes.add(parameterType.getName());
		}

		request.setParameterTypeNames(parameterTypes.toArray(new String[0]));

		return request;
	}
}
