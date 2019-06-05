package com.zongze.filter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.servlet.Filter;

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

    @Bean
    public FilterRegistrationBean filter2() {
        FilterRegistrationBean<Filter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(filter02());
        registrationBean.addUrlPatterns("/*");
        registrationBean.setOrder(10);
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean filter3() {
        FilterRegistrationBean<Filter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(filter03());
        registrationBean.addUrlPatterns("/*");
        registrationBean.setOrder(20);
        return registrationBean;
    }

    @Bean
    public Filter filter01() {
        return new Filter1();
    }

    @Bean
    public Filter filter02() {
        return new Filter2();
    }

    @Bean
    public Filter filter03() {
        return new Filter3();
    }




}
