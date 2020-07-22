package com.zongze;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement(proxyTargetClass = true)
@MapperScan("com.zongze.mapper")
public class SpringbootTransactionPropagationApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootTransactionPropagationApplication.class, args);
    }
}
