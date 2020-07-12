package com.zongze.config;

import com.zongze.component.CounterLimiter;
import com.zongze.filter.RequestLimitFilter;
import com.zongze.util.EmailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Create By xzz on 2018/11/1
 */
@Configuration
public class WebAppConfig implements WebMvcConfigurer {


    /**
     * 跨域配置
     * @param:
     * @return:
     */
    @Bean
    public FilterRegistrationBean corsFilterRegister() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true); //允许携带token
        config.addAllowedOrigin("*"); //允许所有域名访问，如果需要精确控制的话：localhost:8080
        config.addAllowedHeader("*"); //允许所有请求头
        config.addAllowedMethod("*"); //允许所有请求方法
        source.registerCorsConfiguration("/**", config);
        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
        bean.setOrder(0); //该过滤器应该在最先执行，优先级最高
        return bean;
    }


    @Bean
    public FilterRegistrationBean requestLimitRegister(CounterLimiter counterLimiter){
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new RequestLimitFilter(counterLimiter));
        registration.addUrlPatterns("/*");
        registration.setName("commonDataFilter");
        registration.setOrder(1);
        return registration;
    }



    @Bean
    public EmailUtil emailUtil(){
        return new EmailUtil();
    }



}
