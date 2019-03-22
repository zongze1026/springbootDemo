package com.zongze.test;

import com.alibaba.fastjson.JSON;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Create By xzz on 2019/3/21
 */
@Setter
@Getter
public class test02 extends Test01 {

    private Date birthday;


    public static void main(String[] args) {
//        method1();

        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher("aaa2223bb");
        System.out.println(m.find());//匹配2223
        System.out.println(m.start());//返回3
        System.out.println(m.end());//返回7,返回的是2223后的索引号
        System.out.println(m.group());//返回2223
        System.out.println("===============================================");
        Matcher m2 = p.matcher("2223bb");
        System.out.println(m2.lookingAt());   //匹配2223
        System.out.println(m2.start());   //返回0,由于lookingAt()只能匹配前面的字符串,所以当使用lookingAt()匹配时,start()方法总是返回0
        System.out.println(m2.end()); //返回4
        System.out.println(m2.group());   //返回2223
        System.out.println("===============================================");
        Matcher m3 = p.matcher("2223bb");
        System.out.println(m.matches());   //匹配整个字符串
        System.out.println(m.start());   //返回0,原因相信大家也清楚了
        System.out.println(m.end());   //返回6,原因相信大家也清楚了,因为matches()需要匹配所有字符串
        System.out.println(m.group());   //返回2223bb

    }

    private static void method1() {
        // 要验证的字符串
        String str = "ioza";
        // 邮箱验证规则
        String regEx = "oza\\b";
        // 编译正则表达式
        Pattern pattern = Pattern.compile(regEx);
        // 忽略大小写的写法
        // Pattern pat = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(str);
        // 字符串是否与正则表达式相匹配
        boolean rs = matcher.matches();
        System.out.println(rs);
    }


}
