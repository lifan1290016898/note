package com.demo.configuration;

import com.demo.configuration.rule.CustomIRule;
import com.netflix.loadbalancer.IRule;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Configuration
public class ConfigurationBean {

    @LoadBalanced
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    /**
     * 配置自定义负载均衡
     * @return
     */
    @Bean
    public IRule loadBalanceRule(){
        return new CustomIRule(Arrays.asList(8051));
    }

}
