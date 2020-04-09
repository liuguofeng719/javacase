package com.exercise.socket.netty.rpc.v1;

public class RpcResponse {
    private String id;

    private String serviceName;

    private String methodName;

    private String cause;

    private double result;

    public RpcResponse() {
        super();
    }


    public String getId() {
        return id;
    }


    public String getServiceName() {
        return serviceName;
    }


    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }


    public void setId(String id) {
        this.id = id;
    }


    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((cause == null) ? 0 : cause.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((methodName == null) ? 0 : methodName.hashCode());
        long temp;
        temp = Double.doubleToLongBits(this.result);
        result = prime * result + (int) (temp ^ (temp >>> 32));
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
        RpcResponse other = (RpcResponse) obj;
        if (cause == null) {
            if (other.cause != null)
                return false;
        } else if (!cause.equals(other.cause))
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
        if (Double.doubleToLongBits(result) != Double.doubleToLongBits(other.result))
            return false;
        return true;
    }


    @Override
    public String toString() {
        return "RpcResponse [id=" + id + ", methodName=" + methodName + ", cause=" + cause + ", result=" + result + "]";
    }


}
