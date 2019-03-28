package com.zongze;
import com.zongze.component.ApplicationContextHolder;
import com.zongze.util.RedisUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class SpringbootShiroApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootShiroApplication.class, args);
        RedisUtil.setTemplate(ApplicationContextHolder.getBean(RedisTemplate.class));
//        SystemStar.start();
    }

}

