package com.zongze.util;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @Date 2020/12/4 15:37
 * @Created by xiezz
 */
public class DateUtil {

    private static final String FORMAT_DATE = "yyyy-MM-dd";
    private static final String FORMAT_DATE_TIME = "yyyy-MM-dd HH:mm:ss";


    /**
     * 日期格式化：2020-12-04
     *
     * @param date
     */
    public static String formatDate(Date date) {
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return DateTimeFormatter.ofPattern(FORMAT_DATE).format(localDate);
    }


    /**
     * 时间格式化：2020-12-04 15:30:25
     *
     * @param date
     */
    public static String formatTime(Date date) {
        LocalDateTime localDateTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        return DateTimeFormatter.ofPattern(FORMAT_DATE_TIME).format(localDateTime);
    }


    /**
     * 计算时间相差多少个小时
     *
     * @param start
     * @param end
     */
    public static double diffHours(Date start, Date end) {
        LocalDateTime timeStart = start.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        LocalDateTime timeEnd = end.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        Duration between = Duration.between(timeStart, timeEnd);
        long totalMinutes = between.toMinutes();
        BigDecimal bigDecimal = new BigDecimal(String.valueOf(totalMinutes));
        BigDecimal b = new BigDecimal("60");
        return bigDecimal.divide(b, 2, BigDecimal.ROUND_DOWN).doubleValue();
    }


    /**
     * 计算时间相差多少分钟
     *
     * @param start
     * @param end
     */
    public static long diffMinutes(Date start, Date end) {
        LocalDateTime timeStart = start.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        LocalDateTime timeEnd = end.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        Duration duration = Duration.between(timeStart, timeEnd);
        return duration.toMinutes();
    }


    public static void main(String[] args) throws ParseException {
        String str = "2020-12-04 06:00:00";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(FORMAT_DATE_TIME);
        System.out.println(DateUtil.diffHours(simpleDateFormat.parse(str), new Date()));
    }


}
