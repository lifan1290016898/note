package com.demo.service;

import com.demo.model.Users;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MyClassFallbackFactory implements FallbackFactory<ConsumerService> {
    @Override
    public ConsumerService create(Throwable throwable) {
        return new ConsumerService() {
            @Override
            public List<Users> findUserByLikeName(String name) {
                List<Users> users = new ArrayList<>();
                Users user = new Users();
                user.setUserName("not found - class");
                users.add(user);
                return users;
            }
        };
    }
}
