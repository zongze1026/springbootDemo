package com.zongze;

import com.zongze.component.ApplicationContextHolder;
import com.zongze.utils.RedisUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.core.RedisTemplate;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class SpringbootRedisApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootRedisApplication.class, args);
        RedisUtil.setRedisTemplate(ApplicationContextHolder.getBean(RedisTemplate.class));
    }

}

