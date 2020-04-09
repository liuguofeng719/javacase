package com.exercise.socket.netty.rpc.v1_1;

import java.util.Arrays;

public class RpcRequest {
    private String id;

    private String serviceName;

    private String methodName;

    private transient Class<?>[] parameterTypes; //add: {double.class, double.class}

    private String[] parameterTypeNames; //add: {"double", "double"}

    private Object[] args;

    public RpcRequest() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Class<?>[] getParameterTypes() {
        return parameterTypes;
    }

    public void setParameterTypes(Class<?>[] parameterTypes) {
        this.parameterTypes = parameterTypes;
    }

    public String[] getParameterTypeNames() {
        return parameterTypeNames;
    }

    public void setParameterTypeNames(String[] parameterTypeNames) {
        this.parameterTypeNames = parameterTypeNames;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Arrays.hashCode(args);
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((methodName == null) ? 0 : methodName.hashCode());
        result = prime * result + Arrays.hashCode(parameterTypeNames);
        result = prime * result + ((serviceName == null) ? 0 : serviceName.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        RpcRequest other = (RpcRequest) obj;
        if (!Arrays.equals(args, other.args))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (methodName == null) {
            if (other.methodName != null)
                return false;
        } else if (!methodName.equals(other.methodName))
            return false;
        if (!Arrays.equals(parameterTypeNames, other.parameterTypeNames))
            return false;
        if (serviceName == null) {
            if (other.serviceName != null)
                return false;
        } else if (!serviceName.equals(other.serviceName))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "RpcRequestv1_1 [id=" + id + ", serviceName=" + serviceName + ", methodName=" + methodName
                + ", parameterTypeNames=" + Arrays.toString(parameterTypeNames) + ", args=" + Arrays.toString(args)
                + "]";
    }


}