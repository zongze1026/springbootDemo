package com.zongze.mongo;

import com.zongze.mongo.util.ApplicationContextHolder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringbootMongoApplication {


    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(SpringbootMongoApplication.class, args);
        ApplicationContextHolder.setApplicationContext(applicationContext);
    }

}
