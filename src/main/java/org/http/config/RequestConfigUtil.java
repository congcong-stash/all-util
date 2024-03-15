package org.http.config;

import org.apache.hc.client5.http.config.RequestConfig;

import java.util.concurrent.TimeUnit;

/**
 * @author yangchong
 * @version 1.0
 */
public class RequestConfigUtil {

    /**
     * 默认连接超时时间
     */
    private final static int DEFAULT_CONNECT_TIMEOUT = 15;
    /**
     * 默认读取超时时间
     */
    private final static int DEFAULT_SOCKET_TIMEOUT = 15;
    /**
     * 默认从连接池里面获取connection的时间
     */
    private final static int DEFAULT_CONNECTION_REQUEST_TIMEOUT = 15;

    /**
     * 获取http连接客户端配置
     * @return http连接客户端配置
     */
    public static RequestConfig getRequestConfig() {
        return getRequestConfig(DEFAULT_CONNECT_TIMEOUT, DEFAULT_SOCKET_TIMEOUT, DEFAULT_CONNECTION_REQUEST_TIMEOUT);
    }

    /**
     * 获取http连接客户端配置
     * @param connectTimeout 连接超时时间
     * @return http连接客户端配置
     */
    public static RequestConfig getRequestConfig(int connectTimeout) {
        return getRequestConfig(connectTimeout, DEFAULT_SOCKET_TIMEOUT, DEFAULT_CONNECTION_REQUEST_TIMEOUT);
    }

    /**
     * 获取http连接客户端配置
     * @param connectTimeout 连接超时时间
     * @param socketTimeout 读取超时时间
     * @return http连接客户端配置
     */
    public static RequestConfig getRequestConfig(int connectTimeout, int socketTimeout) {
        return getRequestConfig(connectTimeout, socketTimeout, DEFAULT_CONNECTION_REQUEST_TIMEOUT);
    }

    /**
     * 获取http连接客户端配置
     * @param connectTimeout 连接超时时间
     * @param socketTimeout 读取超时时间
     * @param connectionRequestTimeout 从连接池里面获取connection的时间
     * @return http连接客户端配置
     */
    public static RequestConfig getRequestConfig(int connectTimeout, int socketTimeout, int connectionRequestTimeout) {
        return RequestConfig.custom()
                // .setProxy(proxy)
                // 连接超时,完成tcp3次握手的时间上限
                .setConnectTimeout(connectTimeout, TimeUnit.SECONDS)
                // 指的从连接池里面获取connection的时间
                .setConnectionRequestTimeout(connectionRequestTimeout, TimeUnit.SECONDS)
                .build();
    }
}
