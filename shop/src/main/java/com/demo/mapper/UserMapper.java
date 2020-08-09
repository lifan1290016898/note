package com.demo.mapper;

import com.demo.model.bo.BusinessBo;
import com.demo.model.bo.CustomerBo;
import com.demo.model.bo.LoginUserBo;
import com.demo.model.bo.RegisterUserBo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    /**
     * 普通用户注册
     * @param bo
     * @return
     */
    int registerBuyers(RegisterUserBo bo);

    /**
     * 商家注册
     * @param bo
     * @return
     */
    int registerSellers(RegisterUserBo bo);

    /**
     * 普通用户登录
     * @param bo
     * @return
     */
    CustomerBo customerLogin(LoginUserBo bo);

    /**
     * 商家登录
     * @param bo
     * @return
     */
    BusinessBo businessLogin(LoginUserBo bo);
}
