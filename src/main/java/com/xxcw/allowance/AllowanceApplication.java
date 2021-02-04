package com.xxcw.allowance;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@MapperScan("com.xxcw.allowance.mapper")
@ServletComponentScan
@SpringBootApplication
public class AllowanceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AllowanceApplication.class, args);
    }

}
