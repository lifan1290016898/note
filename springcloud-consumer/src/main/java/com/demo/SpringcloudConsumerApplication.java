package com.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringCloudApplication
public class SpringcloudConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringcloudConsumerApplication.class, args);
    }

}
