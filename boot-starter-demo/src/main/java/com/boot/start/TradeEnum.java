package com.boot.start;

/**
 * Created with IntelliJ IDEA.
 *
 * @author lgfcxx
 * @version 1.0
 * @desc
 * @createtime 2018/10/6 下午12:38
 * @see jdk 1.7
 **/
public class TradeEnum {

    public enum RestHttpEnum {

        USER("localhost", "users", "8080"),
        ORDER("localhost", "orders", "8081"),
        PRODUCT("localhost", "products", "8082");

        private String host;
        private String port;
        private String contextPath;


        RestHttpEnum(String host, String contextPath, String port) {
            this.host = host;
            this.port = port;
            this.contextPath = contextPath;
        }

        public String getPort() {
            return port;
        }

        public String getHost() {
            return host;
        }

        public String getContextPath() {
            return contextPath;
        }

        public String getServerUrl() {
            return "http://" + this.getHost() + ":" + getPort() + "/" + getContextPath() + "/";
        }

    }
}
