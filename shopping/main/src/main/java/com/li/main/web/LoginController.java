package com.li.main.web;

import com.li.main.result.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/user")
public class LoginController {



    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Result login() {
        return null;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Result register(){
        return null;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public Result logout() {
        return null;
    }

}

