package com.demo.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app/api")
public class AppController {

    @RequestMapping("/hello")
    public String hello(){
        return "hello app";
    }


}
