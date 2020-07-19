package com.demo.config;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootConfiguration
public class ConfigurationBean {
    /**
     * 网管对其他服务的负载均衡策略
     * @return
     */
    @Bean
    public IRule loadBalancer(){
        return new RandomRule();
    }


}
