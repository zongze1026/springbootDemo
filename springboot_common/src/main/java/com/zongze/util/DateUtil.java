package com.zongze.util;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Create By xzz on 2018/11/1
 * 线程安全的时间工具类,每个线程单独保存一个dateformat实例
 */
public class DateUtil {
    private static ThreadLocal<SimpleDateFormat> threadLocal = new ThreadLocal<SimpleDateFormat>();

    public static Date parse(String dateStr) {
        try {
            return getLocalMap().get().parse(dateStr);
        } catch (ParseException e) {
            System.out.println("-------------时间转换异常------------");
        }
        return null;
    }

    public static String format(Date date) {
        return getLocalMap().get().format(date);
    }

    private static ThreadLocal<SimpleDateFormat> getLocalMap() {
        threadLocal.set(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        return threadLocal;
    }


    public static void main(String[] args) {
        System.out.println(DateUtil.format(new Date()));
        System.out.println(DateUtil.parse("2018-11-01 13:43:10").getClass());
    }


}
