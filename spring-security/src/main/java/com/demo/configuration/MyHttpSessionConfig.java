package com.demo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@EnableRedisHttpSession
public class MyHttpSessionConfig {

    @Bean
    public RedisConnectionFactory connectionFactory(){
        return new JedisConnectionFactory();
    }

    @Bean
    public HttpSessionEventPublisher httpSessionEventPublisher(){
        // 创建会话事件
        return new HttpSessionEventPublisher();
    }

}
