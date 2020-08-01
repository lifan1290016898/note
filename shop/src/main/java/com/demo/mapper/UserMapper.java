package com.demo.mapper;

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


}
