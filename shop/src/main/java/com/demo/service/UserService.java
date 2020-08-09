package com.demo.service;

import com.demo.model.po.LoginUserPo;
import com.demo.model.po.RegisterUserPo;
import com.demo.result.Result;

public interface UserService {

    /**
     * 用户登录
     * @param po
     * @return
     */
    Result login(LoginUserPo po);

    /**
     * 用户注册
     * @return
     */
    Result register(RegisterUserPo registerUserPo);

}


