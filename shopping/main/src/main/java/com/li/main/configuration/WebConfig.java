package com.li.main.configuration;

import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@Configuration
public class WebConfig implements WebMvcConfigurer {


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 添加登录拦截器
//        registry.addInterceptor(identityAuthInterceptor)
//                .addPathPatterns("/**").excludePathPatterns("/user/login", "/user/logout");
    }

}
