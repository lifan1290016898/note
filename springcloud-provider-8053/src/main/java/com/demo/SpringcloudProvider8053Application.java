package com.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(basePackages = {"com.demo.mapper"})
@SpringBootApplication
public class SpringcloudProvider8053Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringcloudProvider8053Application.class, args);
    }

}
