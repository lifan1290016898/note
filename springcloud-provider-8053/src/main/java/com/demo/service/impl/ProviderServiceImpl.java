package com.demo.service.impl;

import com.demo.mapper.ProviderMapper;
import com.demo.model.Users;
import com.demo.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProviderServiceImpl implements ProviderService {

    @Autowired
    private ProviderMapper mapper;

    @Override
    public List<Users> findUserByLikeName(String name) {
        return mapper.findUserByLikeName(name);
    }
}
