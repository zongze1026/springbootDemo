package com.zongze.util;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

/**
 * Create By xzz on 2019/5/28
 */
public class HttpUtil {

    private static RestTemplate restTemplate;

    public static void setRestTemplate(RestTemplate restTemplate) {
        HttpUtil.restTemplate = restTemplate;
    }


    /**
     * post请求
     */
    public static <T> T sendPost(String url, Object param, Class<T> clazz) {
        //设置请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());

        //请求体
        HttpEntity entity = HttpEntity.EMPTY;
        if (param != null) {
            entity = new HttpEntity<>(param, headers);
        }
        //发送请求
        try {
            return restTemplate.postForObject(url, entity, clazz);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
