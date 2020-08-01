package com.demo.service;

import com.demo.model.po.RegisterUserPo;
import com.demo.result.Result;

public interface UserService {

    /**
     * 校验用户信息
     *
     * @param loginName
     * @param passWord
     * @return
     */
    boolean checkUser(String loginName, String passWord);

    /**
     * 查询用户信息
     *
     * @param loginName
     * @return
     */
    RegisterUserPo getUser(String loginName);

    /**
     * 用户注册
     * @return
     */
    Result register(RegisterUserPo registerUserPo);

}


