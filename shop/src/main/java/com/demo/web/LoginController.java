package com.demo.web;

import com.demo.model.po.LoginUserPo;
import com.demo.model.po.RegisterUserPo;
import com.demo.result.Result;
import com.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/user")
public class LoginController {

    @Autowired
    private UserService userService;


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Result login(@Validated LoginUserPo po, HttpServletRequest request) {
        return userService.login(po, request);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Result register(@Validated RegisterUserPo registerUserPo){
        return userService.register(registerUserPo);
    }


}

