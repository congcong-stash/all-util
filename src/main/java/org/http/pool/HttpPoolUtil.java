package org.http.pool;

import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;
import org.apache.hc.client5.http.socket.ConnectionSocketFactory;
import org.apache.hc.client5.http.socket.PlainConnectionSocketFactory;
import org.apache.hc.client5.http.ssl.NoopHostnameVerifier;
import org.apache.hc.client5.http.ssl.SSLConnectionSocketFactory;
import org.apache.hc.client5.http.ssl.TrustAllStrategy;
import org.apache.hc.core5.http.config.Registry;
import org.apache.hc.core5.http.config.RegistryBuilder;
import org.apache.hc.core5.ssl.SSLContextBuilder;

import javax.net.ssl.SSLContext;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

/**
 * @author yangchong
 * @version 1.0
 * @date 2024/3/13 22:25
 */
public class HttpPoolUtil {

    /**
     * 默认最大连接数
     */
    private final static int DEFAULT_MAX_TOTAL_LINK = 100;
    /**
     * 默认最大路由数
     */
    private final static int DEFAULT_MAX_ROUTE = 10;

    public static PoolingHttpClientConnectionManager init() throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        SSLContextBuilder contextBuilder = new SSLContextBuilder();
        // 判断是否信任
        contextBuilder.loadTrustMaterial(null, TrustAllStrategy.INSTANCE);
        SSLContext sslContext = contextBuilder.build();
        SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(sslContext,
                new String[]{"SSLv2Hello", "SSLv3", "TLSv1", "TLSv1.1", "TLSv1.2"},
                null, NoopHostnameVerifier.INSTANCE);
        Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.INSTANCE)
                .register("https", sslConnectionSocketFactory)
                .build();
        PoolingHttpClientConnectionManager pool = new PoolingHttpClientConnectionManager(registry);
        pool.setMaxTotal(DEFAULT_MAX_TOTAL_LINK);
        pool.setDefaultMaxPerRoute(DEFAULT_MAX_ROUTE);
        return pool;
    }
}
