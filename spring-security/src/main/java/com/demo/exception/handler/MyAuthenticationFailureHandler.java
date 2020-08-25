package com.demo.exception.handler;

import com.alibaba.fastjson.JSONObject;
import com.demo.exception.model.PasswordExceptionMessage;
import com.demo.result.Result;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Autowired
    private PasswordExceptionMessage message;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter writer = response.getWriter();
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        String message = JSONObject.toJSONString(Result.fail("登录失败"));
        if(StringUtils.isNotBlank(this.message.getMessage())){
            message = this.message.getMessage();
        }
        writer.write(message);
    }
}
