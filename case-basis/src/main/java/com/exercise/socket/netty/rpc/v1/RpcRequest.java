package com.exercise.socket.netty.rpc.v1;

public class RpcRequest {
    private String id;

    private String serviceName;

    private String methodName;

    private double param1;
    private double param2;

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

    public double getParam1() {
        return param1;
    }

    public void setParam1(double param1) {
        this.param1 = param1;
    }

    public double getParam2() {
        return param2;
    }

    public void setParam2(double param2) {
        this.param2 = param2;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((methodName == null) ? 0 : methodName.hashCode());
        long temp;
        temp = Double.doubleToLongBits(param1);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(param2);
        result = prime * result + (int) (temp ^ (temp >>> 32));
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
        if (Double.doubleToLongBits(param1) != Double.doubleToLongBits(other.param1))
            return false;
        if (Double.doubleToLongBits(param2) != Double.doubleToLongBits(other.param2))
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
        return "RpcRequest [id=" + id + ", serviceName=" + serviceName + ", methodName=" + methodName + ", param1="
                + param1 + ", param2=" + param2 + "]";
    }


}