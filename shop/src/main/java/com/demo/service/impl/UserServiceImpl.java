package com.demo.service.impl;

import com.demo.mapper.UserMapper;
import com.demo.model.bo.RegisterUserBo;
import com.demo.model.po.RegisterUserPo;
import com.demo.result.Result;
import com.demo.service.UserService;
import com.demo.util.CodeMd5;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper mapper;

    @Override
    public boolean checkUser(String loginName, String passWord) {
        return true;
    }

    @Override
    public RegisterUserPo getUser(String loginName) {
        RegisterUserPo registerUserPo = new RegisterUserPo();
        registerUserPo.setName("李四");
        registerUserPo.setPassword("123");
        return registerUserPo;
    }

    @Override
    public Result register(RegisterUserPo registerUserPo) {
        if(registerUserPo == null){
            return Result.fail("参数不能为空");
        }
        RegisterUserBo bo = new RegisterUserBo();
        BeanUtils.copyProperties(registerUserPo, bo);
        bo.setPassword(CodeMd5.codeMd5(bo.getPassword()));
        String role = registerUserPo.getRole();
        if(StringUtils.equals(role, "buyers")){
            // 普通用户注册
            if(mapper.registerBuyers(bo) > 0){
                return Result.success();
            }
            return Result.fail();
        }
        // 商家注册
        if(mapper.registerSellers(bo) > 0){
            return Result.success();
        }
        return Result.fail();
    }
}
