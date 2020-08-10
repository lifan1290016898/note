package com.demo.service;

import com.demo.model.po.LoginUserPo;
import com.demo.model.po.RegisterUserPo;
import com.demo.result.Result;

import javax.servlet.http.HttpServletRequest;

public interface UserService {

    /**
     * 用户登录
     * @param po
     * @return
     */
    Result login(LoginUserPo po, HttpServletRequest request);

    /**
     * 用户注册
     * @return
     */
    Result register(RegisterUserPo registerUserPo);

}


