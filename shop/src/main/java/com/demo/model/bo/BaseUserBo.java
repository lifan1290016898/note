package com.demo.model.bo;

import lombok.Data;

@Data
public class BaseUserBo {

    private Integer id;

    private String name;

    private String password;

    private String phone;

    private String address;
}
