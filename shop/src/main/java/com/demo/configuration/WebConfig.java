package com.demo.configuration;

import com.demo.Interceptors.IdentityAuthInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private IdentityAuthInterceptor identityAuthInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 添加登录拦截器
        registry.addInterceptor(identityAuthInterceptor)
                .addPathPatterns("/**").excludePathPatterns("/user/login", "/user/logout");
    }

}
