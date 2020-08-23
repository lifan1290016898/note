package com.demo.filter;

import com.demo.exception.VerificationCodeException;
import com.demo.exception.handler.MyAuthenticationFailureHandler;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class VerificationCodeFilter extends OncePerRequestFilter {

    private AuthenticationFailureHandler authenticationFailureHandler = new MyAuthenticationFailureHandler();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if ("/auth/form".equals(request.getRequestURI())) {
            try {
                this.verificationCode(request);
            } catch (VerificationCodeException e) {
                authenticationFailureHandler.onAuthenticationFailure(request, response, e);
            }
        }
        filterChain.doFilter(request, response);
    }

    private void verificationCode(HttpServletRequest request) throws VerificationCodeException {
        String captcha = request.getParameter("captcha");
        HttpSession session = request.getSession();
        String saveCode = (String) session.getAttribute("captcha");
        if (StringUtils.isNotBlank(saveCode)) {
            session.removeAttribute("captcha");
        }

        if (StringUtils.isBlank(captcha) || StringUtils.isBlank(saveCode) || !StringUtils.equals(captcha, saveCode)) {
            throw new VerificationCodeException();
        }


    }


}
