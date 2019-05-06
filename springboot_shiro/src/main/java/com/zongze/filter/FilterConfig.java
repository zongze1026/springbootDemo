package com.zongze.filter;

import com.zongze.util.DateUtil;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Create By xzz on 2019/2/22
 */
@Configuration
public class FilterConfig {


    @Bean
    public FilterRegistrationBean filter1() {
        FilterRegistrationBean<Filter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(filter01());
        registrationBean.addUrlPatterns("/*");
        registrationBean.setOrder(100);
        return registrationBean;
    }

//    @Bean
//    public FilterRegistrationBean filter2() {
//        FilterRegistrationBean<Filter> registrationBean = new FilterRegistrationBean<>();
//        registrationBean.setFilter(filter02());
//        registrationBean.addUrlPatterns("/test/*");
//        registrationBean.setOrder(10);
//        return registrationBean;
//    }
//
//    @Bean
//    public FilterRegistrationBean filter3() {
//        FilterRegistrationBean<Filter> registrationBean = new FilterRegistrationBean<>();
//        registrationBean.setFilter(filter03());
//        registrationBean.addUrlPatterns("/admin/*");
//        registrationBean.setOrder(20);
//        return registrationBean;
//    }

    @Bean("filter")
    public Filter filter01() {
        return new Filter1();
    }

//    @Bean("filter2")
//    public Filter filter02() {
//        return new Filter2();
//    }
//
//    @Bean("filter3")
//    public Filter filter03() {
//        return new Filter3();
//    }


    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR));
        calendar.set(Calendar.HOUR_OF_DAY, 9);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        System.out.println(DateUtil.format(calendar.getTime(), DateUtil.DATE_TIME));
        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + 1);
        calendar2.set(Calendar.HOUR_OF_DAY, 03);
        calendar2.set(Calendar.MINUTE, 0);
        calendar2.set(Calendar.SECOND, 0);
        System.out.println(DateUtil.format(calendar2.getTime(), DateUtil.DATE_TIME));

        System.out.println(DateUtil.isMiddleTime(calendar, calendar2));


        System.out.println(DateUtil.format(DateUtil.getStartDate(),DateUtil.DATE_TIME));
        System.out.println(DateUtil.format(DateUtil.getEndDate(),DateUtil.DATE_TIME));

        System.out.println(DateUtil.format(DateUtil.getStartDate(2),DateUtil.DATE_TIME));
        System.out.println(DateUtil.format(DateUtil.getEndDate(2),DateUtil.DATE_TIME));

        SimpleDateFormat format = new SimpleDateFormat(DateUtil.DATE_TIME);
        format.applyPattern(DateUtil.DATE);
        System.out.println(format.format(new Date()));


    }


}
