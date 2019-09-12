package com.zongze.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Create By xzz on 2018/11/1
 * 线程安全的时间工具类,每个线程单独保存一个dateformat实例
 */
public class DateUtil {

    public static final String DATE = "yyyy-MM-dd";
    public static final String DATE_TIME = "yyyy-MM-dd HH:mm:ss";

    private static final ThreadLocal<SimpleDateFormat> threadLocal = new InheritableThreadLocal<>();

    private DateUtil() {
    }

    static {
        threadLocal.set(new SimpleDateFormat(DATE_TIME));
    }

    private static SimpleDateFormat getInstance(String dateType) {
        SimpleDateFormat simpleDateFormat = threadLocal.get();
        simpleDateFormat.applyPattern(dateType);
        return simpleDateFormat;
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


    /**
     * 当前时间是否在指定的时间范围内
     */
    public static boolean isMiddleTime(Date startTime, Date endTime) {
        Calendar currentTime = Calendar.getInstance();
        Calendar start = Calendar.getInstance();
        start.setTime(startTime);
        Calendar end = Calendar.getInstance();
        end.setTime(endTime);
        return currentTime.after(start) && currentTime.before(end);
    }


    /**
     * 计算两个时间间隔多少时间
     */
    public static long diffTimeMillis(Date start, Date end) {
        long startTimeMillis = start.getTime();
        long endTimeMillis = end.getTime();
        return endTimeMillis - startTimeMillis;
    }

    /**
     * 计算两个时间间隔多少天
     */
    public static long diffDays(Date start, Date end) {
        long timeMillis = diffTimeMillis(start, end);
        return timeMillis / (24 * 3600 * 1000);
    }


    /**
     * 获取当天0点时间戳
     */
    public static Date getStartDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取当天23:59:59秒时间戳
     */
    public static Date getEndDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(getStartDate());
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + 1);
        calendar.setTimeInMillis(calendar.getTimeInMillis() - 1);
        return calendar.getTime();
    }


    /**
     * 获取指定天数后的起始时间
     */
    public static Date getStartDate(int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(getStartDate());
        calendar.add(Calendar.DAY_OF_YEAR, days);
        return calendar.getTime();
    }


    /**
     * 获取指定天数后的结束时间
     */
    public static Date getEndDate(int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(getEndDate());
        calendar.add(Calendar.DAY_OF_YEAR, days);
        return calendar.getTime();
    }

    public static void main(String[] args) {
        String start = "2019-05-27 14:13:30";
        String end = "2019-05-27 16:20:30";
        boolean betweenDate = DateUtil.isMiddleTime(DateUtil.parse(start, DateUtil.DATE_TIME), DateUtil.parse(end, DateUtil.DATE_TIME));
        System.out.println(betweenDate);
    }


}
