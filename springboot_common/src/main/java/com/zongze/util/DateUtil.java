package com.zongze.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Create By xzz on 2018/11/1
 * 线程安全的时间工具类,每个线程单独保存一个dateformat实例
 */
public class DateUtil {

    public static final String DATE = "yyyy-MM-dd";
    public static final String DATE_TIME = "yyyy-MM-dd HH:mm:ss";

    private static final ThreadLocal<Map<String, SimpleDateFormat>> threadLocal = new InheritableThreadLocal<>();

    static {
        Map<String, SimpleDateFormat> threadLocalMap = new HashMap<>();
        threadLocalMap.put(DATE, new SimpleDateFormat(DATE));
        threadLocalMap.put(DATE_TIME, new SimpleDateFormat(DATE_TIME));
        threadLocal.set(threadLocalMap);
    }

    private static SimpleDateFormat getInstance(String dateType) {
        if (null == dateType) {
            return threadLocal.get().get(DATE_TIME);
        }
        return threadLocal.get().get(dateType);
    }


    public static Date parse(String date, String dateType) {
        try {
            return getInstance(dateType).parse(date);
        } catch (ParseException e) {
            System.out.println("-------------时间转换异常------------");
        }
        return null;
    }

    public static String format(Date date, String dateType) {
        return getInstance(dateType).format(date);
    }


    public static void main(String[] args) {
        String format = DateUtil.format(new Date(), DateUtil.DATE);
        System.out.println(format);

        Date parse = DateUtil.parse(format, DateUtil.DATE);
        System.out.println(DateUtil.format(parse, DateUtil.DATE_TIME));

    }


}
