package com.demo.configuration;

import com.alibaba.fastjson.JSONObject;
import com.demo.filter.VerificationCodeFilter;
import com.demo.exception.handler.MyAuthenticationFailureHandler;
import com.demo.result.Result;
import com.demo.service.impl.MySecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private MySecurityService service;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        .authorizeRequests()
        // 访问这些url的时候需要对应的权限
        .antMatchers("/admin/api/**").hasRole("ADMIN")
        .antMatchers("/user/api/**").hasRole("USER")
        // permitAll: 放行
        .antMatchers("/app/api").permitAll()
        .antMatchers("/captcha").permitAll()
        .anyRequest().authenticated()
        .and()
        .csrf().disable()
        // 表单登录配置
        .formLogin()
            .loginPage("/myLogin")
            .loginProcessingUrl("/auth/form").permitAll()
            .successHandler(new AuthenticationSuccessHandler() {
                // 处理登录成功(返回json到前台)
                @Override
                public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                    response.setContentType("application/json;charset=utf-8");
                    PrintWriter writer = response.getWriter();
                    writer.write(JSONObject.toJSONString(Result.success("登录成功!")));
                }
            })
            .failureHandler(new MyAuthenticationFailureHandler());
        http.addFilterAfter(new VerificationCodeFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(service).passwordEncoder(new BCryptPasswordEncoder());
    }
}
