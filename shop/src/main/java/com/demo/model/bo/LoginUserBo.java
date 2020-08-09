package com.demo.model.bo;

import lombok.Data;

@Data
public class LoginUserBo {
    /**
     * 用户名
     */
    private String name;
    /**
     * 密码
     */
    private String password;

}
