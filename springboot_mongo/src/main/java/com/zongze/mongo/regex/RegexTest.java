package com.zongze.mongo.regex;

import java.util.regex.Pattern;

/**
 * @Date 2021/1/21 11:57
 * @Created by xiezz
 */
public class RegexTest {


    public static void main(String[] args) {

        String regex = "^王.$";

        boolean b = Pattern.matches( regex,"王老");

        System.out.println(b);


    }





}
