package com.zongze.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Create By xzz on 2018/11/1
 * 线程安全的时间工具类,每个线程单独保存一个dateformat实例
 */
public class DateUtil {

    public static final String DATE = "yyyy-MM-dd";
    public static final String DATE_TIME = "yyyy-MM-dd HH:mm:ss";

    private static ThreadLocal<SimpleDateFormat> threadLocal = new ThreadLocal<>();


    private static ThreadLocal<SimpleDateFormat> getLocalMap(String dateType) {
        threadLocal.set(new SimpleDateFormat(dateType));
        return threadLocal;
    }


    public static Date parse(String date, String dateType) {
        try {
            return getLocalMap(dateType).get().parse(date);
        } catch (ParseException e) {
            System.out.println("-------------时间转换异常------------");
        }
        return null;
    }

    public static String format(Date date, String dateType) {
        return getLocalMap(dateType).get().format(date);
    }


    public static void main(String[] args) {
        String format = DateUtil.format(new Date(), DateUtil.DATE_TIME);
        System.out.println(format);

        Date parse = DateUtil.parse(format, DateUtil.DATE);
        System.out.println(DateUtil.format(parse,DateUtil.DATE_TIME));

    }


}
