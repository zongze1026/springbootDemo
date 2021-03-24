package com.zongze.mybatisplug;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.zongze.mybatisplug.mapper"})
public class MybatisPlugApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisPlugApplication.class, args);
    }

}
