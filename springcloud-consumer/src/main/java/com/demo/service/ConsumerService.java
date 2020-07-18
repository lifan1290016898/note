package com.demo.service;

import com.demo.model.Users;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

//@FeignClient(value = "PROVIDER", fallbackFactory = MyClassFallbackFactory.class)
@FeignClient(value = "PROVIDER", fallback = ConsumerServiceImpl.class)
public interface ConsumerService {

    @RequestMapping("/find/{name}")
    List<Users> findUserByLikeName(@PathVariable("name") String name);

}
