package com.zongze.filter;
import com.zongze.util.DateUtil;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.servlet.Filter;
import java.util.Calendar;

/**
 * Create By xzz on 2019/2/22
 */
//@Configuration
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
        calendar.set(Calendar.DAY_OF_YEAR,calendar.get(Calendar.DAY_OF_YEAR)-1);
        System.out.println(DateUtil.format(calendar.getTime(),DateUtil.DATE_TIME));
    }


}
