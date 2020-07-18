package com.demo.web;

import com.demo.model.Users;
import com.demo.service.ConsumerService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ConsumerController {

    @Autowired
    private RestTemplate template;

    private final String REMOTE_URL = "http://PROVIDER";

    @Autowired
    private ConsumerService feignService;

    /**
     * RestTemplate调用方式
     * @return
     */
    @RequestMapping(value = "/get_remote_data", method = RequestMethod.GET)
    public List remote(){
        String url = this.REMOTE_URL + "/find/a";
        return template.getForObject(url, List.class);
    }

    // 为每一个设置不同的时间
    @HystrixCommand(fallbackMethod = "getHystrixMethod", commandProperties = @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "4000"))
    @RequestMapping(value = "/find/{name}")
    public Map<String, Object> getRemoteByOpenFeign(@PathVariable("name") String name){
        Map<String, Object> result = new HashMap<>();
        result.put("data", feignService.findUserByLikeName(name));
        result.put("message", "ok");
        return result;
    }

    public Map<String, Object> getHystrixMethod(@PathVariable("name") String name) {
        Map<String, Object> result = new HashMap<>();
        result.put("message", "not found - method");
        result.put("data", null);
        return result;
    }


}
