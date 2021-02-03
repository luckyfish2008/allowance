package com.xxcw.allowance;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.xxcw.allowance.mapper")
@SpringBootApplication
public class AllowanceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AllowanceApplication.class, args);
    }

}
