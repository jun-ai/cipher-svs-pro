package com.koalii.svs.client.util;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import java.io.IOException;
import java.util.Map;

/**
 * httpclient请求工具类
 */
@Slf4j
public class HttpClientUtils {
    /**
     * Post请求,传参为Map
     *
     * @param url
     * @param param
     * @return
     */
    public static String doPost(String url, Map<String, String> param) {
        // 创建Httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //设置请求超时时间
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(10000)
                .setConnectionRequestTimeout(10000)
                .setSocketTimeout(10000)
                .build();
        CloseableHttpResponse response = null;
        String resultString = "";
        try {
            // 创建Http Post请求
            HttpPost httpPost = new HttpPost(url);
            httpPost.setConfig(requestConfig);
            // 创建参数列表
            if (param != null) {
                StringEntity entity = new StringEntity(JSONObject.toJSONString(param), ContentType.APPLICATION_JSON);
                httpPost.setEntity(entity);
            }
            // 执行http请求
            response = httpClient.execute(httpPost);

            if (response.getEntity() != null) {
                resultString = EntityUtils.toString(response.getEntity(), "utf-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return resultString;
    }

    /**
     * Post请求,传参为Map
     *
     * @param url
     * @param header
     * @param param
     * @return
     */
    public static String doPost(String url, Map<String, String> header, Map<String, String> param) {
        // 创建Httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //设置请求超时时间
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(10000)
                .setConnectionRequestTimeout(10000)
                .setSocketTimeout(10000)
                .build();
        CloseableHttpResponse response = null;
        String resultString = "";
        try {
            // 创建Http Post请求
            HttpPost httpPost = new HttpPost(url);
            httpPost.setConfig(requestConfig);
            //添加header参数
            if (header != null && !header.isEmpty()) {
                header.forEach((key, value) -> {
                    httpPost.addHeader(key, value);
                });
            }
            // 创建参数列表
            if (param != null) {
                StringEntity entity = new StringEntity(JSONObject.toJSONString(param), ContentType.APPLICATION_JSON);
                httpPost.setEntity(entity);
            }
            // 执行http请求
            response = httpClient.execute(httpPost);

            if (response.getEntity() != null) {
                resultString = EntityUtils.toString(response.getEntity(), "utf-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return resultString;
    }

    /**
     * Post请求,传参为json字符串
     *
     * @param url
     * @param json
     * @return
     */
    public static String doPostJson(String url, String json) {
        // 创建Httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();

        //设置请求超时时间
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(10000)
                .setConnectionRequestTimeout(10000)
                .setSocketTimeout(10000)
                .build();
        CloseableHttpResponse response = null;
        String resultString = "";
        try {
            // 创建Http Post请求
            HttpPost httpPost = new HttpPost(url);
            httpPost.setConfig(requestConfig);
            // 创建请求内容 ，发送json数据需要设置contentType
            if (json != null && !"".equals(json)) {
                StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
                httpPost.setEntity(entity);
            }
            log.info("请求url:{}"+url);
            log.info("doPostJson请求参数为:{}"+json.toString());
            // 执行http请求
            response = httpClient.execute(httpPost);

            if (response.getEntity() != null) {
                resultString = EntityUtils.toString(response.getEntity(), "utf-8");
                log.info("resultString信息为:{}"+resultString.toString());
            }
        } catch (Exception e) {
            log.error("doPostJson请求报错:{}"+e);
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                log.error("doPostJson IO 请求报错:{}"+e);
            }
        }
        return resultString;
    }

    /**
     * Post请求,传参为json字符串
     * @param url
     * @param header
     * @param json
     * @return
     */
    public static String doPostJson(String url, Map<String, String> header, String json) {
        // 创建Httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();

        //设置请求超时时间
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(10000)
                .setConnectionRequestTimeout(10000)
                .setSocketTimeout(10000)
                .build();
        CloseableHttpResponse response = null;
        String resultString = "";
        try {
            // 创建Http Post请求
            HttpPost httpPost = new HttpPost(url);
            httpPost.setConfig(requestConfig);
            //添加header参数
            if (header != null && !header.isEmpty()) {
                header.forEach((key, value) -> {
                    httpPost.addHeader(key, value);
                });
            }
            // 创建请求内容 ，发送json数据需要设置contentType
            if (json != null && !"".equals(json)) {
                StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
                httpPost.setEntity(entity);
            }
            log.info("请求url:{}"+url);
            log.info("doPostJson请求参数为:{}"+json.toString());
            // 执行http请求
            response = httpClient.execute(httpPost);

            if (response.getEntity() != null) {
                resultString = EntityUtils.toString(response.getEntity(), "utf-8");
                log.info("验签返回结果为"+resultString.toString());
            }
        } catch (Exception e) {
            log.error("doPostJson请求报错:{}"+e);
            //e.printStackTrace();
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                log.error("doPostJson IO 请求报错:{}"+e);
                // TODO Auto-generated catch block
                //e.printStackTrace();
            }
        }
        return resultString;
    }
}
