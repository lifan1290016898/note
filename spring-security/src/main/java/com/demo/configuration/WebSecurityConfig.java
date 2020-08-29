package com.demo.configuration;

import com.alibaba.fastjson.JSONObject;
import com.demo.result.Result;
import com.demo.service.impl.MySecurityService;
import com.demo.session.MyInvalidSessionStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.session.security.SpringSessionBackedSessionRegistry;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private MySecurityService service;

    @Autowired
    private AuthenticationDetailsSource<HttpServletRequest, WebAuthenticationDetails> authenticationDetailsSource;

    @Autowired
    @Qualifier("captchaDaoAuthenticationProvider")
    private AuthenticationProvider captchaDaoAuthenticationProvider;

    @Autowired
    @Qualifier("passwordValidProvider")
    private AuthenticationProvider passwordValidProvider;

    @Autowired
    private AuthenticationFailureHandler failureHandler;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private FindByIndexNameSessionRepository mySessionRepository;
    @Bean
    public SpringSessionBackedSessionRegistry sessionRegistry(){
        return new SpringSessionBackedSessionRegistry(mySessionRepository);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 持久化令牌实现
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(this.dataSource);

        http
        .authorizeRequests()
        // 访问这些url的时候需要对应的权限
        .antMatchers("/admin/api/**").hasRole("ADMIN")
        .antMatchers("/user/api/**").hasRole("USER")
        // permitAll: 放行
        .antMatchers("/app/api").permitAll()
        .antMatchers("/captcha").permitAll()
        .antMatchers("/favicon.ico").permitAll()
        .anyRequest().authenticated()
        .and()
        .csrf().disable()
        // 表单登录配置
        .formLogin()
            .authenticationDetailsSource(authenticationDetailsSource)
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
            .failureHandler(failureHandler)
            .and().rememberMe().userDetailsService(service).tokenRepository(jdbcTokenRepository)
            // 会话并发控制,1个,阻止新建会话(还需要创建会话事件)
            .and().sessionManagement().maximumSessions(10).maxSessionsPreventsLogin(true).sessionRegistry(sessionRegistry())

//            .and()/*.invalidSessionStrategy(new MyInvalidSessionStrategy())*/.invalidSessionUrl("/myLogin")
        ; // 会话过期后跳转到该页面; // 自定义一个会话过期策略
        // 如果不使用这种方式验证请求中的验证码,那么,可以使用更加优雅的AuthenticationProvider方式
//        http.addFilterAfter(new VerificationCodeFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(service).passwordEncoder(passwordEncoder());
        auth.authenticationProvider(captchaDaoAuthenticationProvider);
        auth.authenticationProvider(passwordValidProvider);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }



}
