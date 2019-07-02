package com.zongze.service;

import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Create By xzz on 2019/7/2
 */
@Service
public class SignCheckService {

    private static String token = "wxtoken";
    char[] chars = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    public String checkSign(String sign) {
        StringBuffer buffer = new StringBuffer();
        try {
            MessageDigest digest = MessageDigest.getInstance("sha1");
            byte[] bytes = digest.digest(sign.getBytes());
            for (byte b : bytes) {
                buffer.append(chars[(b >> 4) & 15]);
                buffer.append(chars[b & 15]);
            }
            return buffer.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    //https请求方式: GET
    //https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET
    public static String accessToken() {
        try {
            URL url = new URL("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wx38f204cdd185e445&secret=3efa0ea0d29f3cfa2b46a157cf52759c");
            URLConnection urlConnection = url.openConnection();
            InputStream inputStream = urlConnection.getInputStream();
            int len = 0;
            byte[] buffer = new byte[1024];
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            while ((len=(inputStream.read(buffer)))>0) {
                outputStream.write(buffer,0,len);
            }
            return new String(outputStream.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static void main(String[] args) {
        String s = accessToken();
        System.out.println(s);
    }


}
