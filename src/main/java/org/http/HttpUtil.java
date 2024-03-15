package org.http;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.HttpResponse;
import org.apache.hc.core5.http.HttpStatus;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.http.client.HttpClientUtil;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author yangchong
 * @version 1.0
 * @date 2024/3/13 23:35
 */
public class HttpUtil {

    public static String postHeaderParamJson(String url, Map<String, String> headers, Map<String, Object> params) throws Exception {
        HttpPost httpPost = new HttpPost(url);
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(15, TimeUnit.SECONDS)
                .setConnectionRequestTimeout(15, TimeUnit.SECONDS)
                .build();
        CloseableHttpClient client = HttpClientUtil.getHttpClient(requestConfig);
        String respContent = null;
        if (headers != null) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                httpPost.setHeader(entry.getKey(), entry.getValue());
            }
        }
        String dataJson = JSONObject.toJSONString(params, SerializerFeature.WriteDateUseDateFormat);
        //解决中文乱码问题
        StringEntity entity = new StringEntity(dataJson, StandardCharsets.UTF_8);
        httpPost.setEntity(entity);
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-type", "application/json");
        CloseableHttpResponse resp = null;
        try {
            resp = client.execute(httpPost);
            if (resp.getCode() == HttpStatus.SC_OK) {
                HttpEntity he = resp.getEntity();
                respContent = EntityUtils.toString(he, StandardCharsets.UTF_8);
            }
        } finally {
            if (resp != null) {
                resp.close();
            }
        }
        return respContent;
    }
}
