package com.zongze;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class SpringbootRedissionApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootRedissionApplication.class, args);
    }

}
