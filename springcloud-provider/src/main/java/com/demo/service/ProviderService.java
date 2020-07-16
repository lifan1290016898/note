package com.demo.service;

import com.demo.model.Users;

import java.util.List;

public interface ProviderService {

    List<Users> findUserByLikeName(String name);


}
