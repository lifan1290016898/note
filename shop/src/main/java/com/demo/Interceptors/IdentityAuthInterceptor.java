package com.demo.Interceptors;

import com.auth0.jwt.interfaces.Claim;
import com.demo.util.JwtUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Component
public class IdentityAuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        Map<String, Claim> claimMap = JwtUtil.verifyToken(token);
        String id = claimMap.get("id").toString();
        return StringUtils.isNotBlank(id);
    }
}
