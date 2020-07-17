package com.demo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class ConsumerController {

    @Autowired
    private RestTemplate template;

    private final String REMOTE_URL = "http://PROVIDER";

    /**
     * RestTemplate调用方式
     * @return
     */
    @RequestMapping(value = "/get_remote_data", method = RequestMethod.GET)
    public List remote(){
        String url = this.REMOTE_URL + "/find/a";
        return template.getForObject(url, List.class);
    }


}
