package com.zongze.util;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class HttpUtil {

    //post发送json
    public static String postForJson(String url, String jsonData) {
        HttpClient httpClient = new HttpClient();
        PostMethod post = null;
        StringBuffer resultBuffer = new StringBuffer();
        try {
            post = new PostMethod(url);
            RequestEntity se = new StringRequestEntity(jsonData, "application/json", "utf-8");
            post.setRequestEntity(se);
            int statusCode = httpClient.executeMethod(post);
            if (statusCode != HttpStatus.SC_OK) {
                System.out.println("请求失败,响应状态码:" + statusCode);
            } else {
                return parseResult(post.getResponseBodyAsStream());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (post != null)
                post.releaseConnection();
        }
        return null;
    }


    //get请求
    public static String get(String url) {
        HttpClient httpClient = new HttpClient();
        GetMethod get = null;
        try {
            get = new GetMethod(url);
            int statusCode = httpClient.executeMethod(get);
            if (statusCode != HttpStatus.SC_OK) {
                System.out.println("请求失败,响应状态码:" + statusCode);
            } else {
                return parseResult(get.getResponseBodyAsStream());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (get != null)
                get.releaseConnection();
        }
        return null;
    }

    private static String parseResult(InputStream in) throws IOException {
        StringBuffer resultBuffer = new StringBuffer();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in, "utf-8"));
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            resultBuffer.append(line);
        }
        return resultBuffer.toString();
    }


}
