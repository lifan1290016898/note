package com.test.controller;

import com.custom.service.WarpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private WarpService service;


    @RequestMapping("/test/{param}")
    public String test(@PathVariable("param") String param){
        return service.warpString(param);
    }


}
