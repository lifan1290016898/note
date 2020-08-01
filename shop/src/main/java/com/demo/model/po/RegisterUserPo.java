package com.demo.model.po;

import com.demo.annotation.Phone;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterUserPo {

    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空")
    private String name;

    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空")
    private String password;

    /**
     * 联系方式
     */
    @Phone(type = "Phone")
    private String phone;

    /**
     * 地址
     */
    private String address;

    /**
     * 注册的角色
     */
    @NotBlank(message = "注册角色")
    private String role;


}

