package org.http.client;

import org.apache.hc.client5.http.HttpRequestRetryStrategy;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.impl.DefaultHttpRequestRetryStrategy;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;
import org.apache.hc.core5.http.HttpResponse;
import org.apache.hc.core5.http.protocol.HttpContext;
import org.http.config.RequestConfigUtil;
import org.http.pool.HttpPoolUtil;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

/**
 * @author yangchong
 * @version 1.0
 * @date 2024/3/13 23:08
 */
public class HttpClientUtil {

    private static PoolingHttpClientConnectionManager POOLING_HTTP_CLIENT_CONNECTION_MANAGER = null;

    static {
        try {
            POOLING_HTTP_CLIENT_CONNECTION_MANAGER = HttpPoolUtil.init();
        } catch (NoSuchAlgorithmException | KeyStoreException | KeyManagementException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取http客户端连接
     * @return http客户端连接
     */
    public static CloseableHttpClient getHttpClient() {
        return getHttpClient(RequestConfigUtil.getRequestConfig());
    }

    /**
     * 获取http客户端连接
     * @param requestConfig 客户端连接配置
     * @return http客户端连接
     */
    public static CloseableHttpClient getHttpClient(RequestConfig requestConfig) {
        HttpRequestRetryStrategy retryStrategy = new DefaultHttpRequestRetryStrategy() {
            @Override
            public boolean retryRequest(HttpResponse response, int execCount, HttpContext context) {
                if (execCount > 3) {
                    // 如果重试次数超过3次，则放弃重试
                    return false;
                }
                int statusCode = response.getCode();
                // 如果遇到服务器错误状态码，则进行重试
                return statusCode == 500 || statusCode == 401;
                // 其他情况不进行重试
            }
        };
        return HttpClients.custom().setConnectionManager(POOLING_HTTP_CLIENT_CONNECTION_MANAGER)
                .setDefaultRequestConfig(requestConfig)
                .setRetryStrategy(retryStrategy)
                .setConnectionManagerShared(true)
                .build();
    }

}
