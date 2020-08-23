package com.demo.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/api")
public class AdminController {


    @RequestMapping("/hello")
    public String hello(){
        return "hello admin";
    }


}
