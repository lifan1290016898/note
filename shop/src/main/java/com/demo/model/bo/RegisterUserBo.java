package com.demo.model.bo;

import lombok.Data;

@Data
public class RegisterUserBo {

    /**
     * 用户名
     */
    private String name;

    /**
     * 密码
     */
    private String password;

    /**
     * 联系方式
     */
    private String phone;

    /**
     * 地址
     */
    private String address;

}
