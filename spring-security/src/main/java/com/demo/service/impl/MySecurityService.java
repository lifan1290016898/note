package com.demo.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.demo.mapper.UserMapper;
import com.demo.model.User;
import com.demo.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MySecurityService implements UserDetailsService {

    @Autowired
    private UserMapper mapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = mapper.findUserByName(s);
        if(user == null){
            throw new UsernameNotFoundException(JSONObject.toJSONString(Result.fail("用户不存在")));
        }
        user.setAuthorities(AuthorityUtils.commaSeparatedStringToAuthorityList(user.getRoles()));
        return user;
    }
}
