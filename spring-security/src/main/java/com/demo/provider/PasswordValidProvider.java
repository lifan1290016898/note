package com.demo.provider;

import com.alibaba.fastjson.JSONObject;
import com.demo.exception.model.PasswordExceptionMessage;
import com.demo.result.Result;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

@Component
public class PasswordValidProvider extends AbstractUserDetailsAuthenticationProvider {

    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * 此对象解决BadCredentialsException异常的信息为 Bad credentials
     */
    @Autowired
    private PasswordExceptionMessage message;

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {
        String password = usernamePasswordAuthenticationToken.getCredentials().toString();
        if(usernamePasswordAuthenticationToken.getCredentials() == null || StringUtils.isBlank(password)){
            message.setMessage(JSONObject.toJSONString(Result.fail("密码不能为空")));
            throw new BadCredentialsException(this.messages.getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials", JSONObject.toJSONString(Result.fail("密码不能为空"))));
        } else {
            password = BCrypt.hashpw(password, BCrypt.gensalt());
            if(!StringUtils.equals(password, userDetails.getPassword())){
                message.setMessage(JSONObject.toJSONString(Result.fail("密码错误")));
                throw new BadCredentialsException(this.messages.getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials", JSONObject.toJSONString(Result.fail("密码错误"))));
            }
        }
    }

    @Override
    protected UserDetails retrieveUser(String s, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {
        return userDetailsService.loadUserByUsername(s);
    }
}
