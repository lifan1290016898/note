package com.demo.service;

import com.demo.model.Users;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Component
@RequestMapping("/fallback")
public class ConsumerServiceImpl implements ConsumerService {
    @Override
    public List<Users> findUserByLikeName(String name) {
        List<Users> users = new ArrayList<>();
        Users user = new Users();
        user.setUserName("not found - class2");
        users.add(user);
        return users;
    }
}
