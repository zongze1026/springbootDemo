package com.zongze.bigdata.properties;

import java.io.*;
import java.util.Properties;

/**
 * Created by xieZZ on 2019/5/30
 * 测试properties作用
 */
public class TestProperties {


    public static void main(String[] args) {
        try {
            InputStream in = ClassLoader.getSystemResourceAsStream("test.properties");
            InputStreamReader reader = new InputStreamReader(in,"gbk");
            Properties properties = new Properties();
            properties.load(reader);
            String name = properties.getProperty("name");
            String age = properties.getProperty("age");
            System.out.println(name+"="+age);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}