package com.demo.web;

import com.demo.model.Users;
import com.demo.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProviderController {

    @Autowired
    private ProviderService service;

    @RequestMapping(value = "/find/{name}", method = RequestMethod.GET)
    public List<Users> findUserByLikeName(@PathVariable(value = "name", required = false) String name){
        return service.findUserByLikeName(name);
    }



}
