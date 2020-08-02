package com.demo.model.po;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginUserPo {

    @NotBlank(message = "用户名不能为空")
    private String name;

    @NotBlank(message = "密码不能为空")
    private String password;

    @NotBlank(message = "登录角色不能为空")
    private String role;

}
