package com.zongze.config;

import com.zongze.interceptor.Login2Interceptor;
import com.zongze.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Create By xzz on 2018/11/1
 */
@Configuration
public class WebAppConfig implements WebMvcConfigurer {


    @Bean
    public LoginInterceptor getLoginInterceptor() {
        return new LoginInterceptor();
    }

    @Bean
    public Login2Interceptor getLogin2Interceptor() {
        return new Login2Interceptor();
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        String[] exclude = {"/token/expire", "/user/login", "/open/**"};
        registry.addInterceptor(getLoginInterceptor()).addPathPatterns("/**").excludePathPatterns(exclude).order(Integer.MIN_VALUE - 1);
        registry.addInterceptor(getLogin2Interceptor()).addPathPatterns("/**").excludePathPatterns(exclude).order(Integer.MAX_VALUE - 2);
    }

    /**
     * 跨域解决方案
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedMethods("GET", "POST");
    }


    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }


}
