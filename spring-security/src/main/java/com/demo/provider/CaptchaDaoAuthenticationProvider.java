package com.demo.provider;

import com.demo.exception.VerificationCodeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CaptchaDaoAuthenticationProvider extends DaoAuthenticationProvider {

    @Autowired
    @Qualifier("passwordValidProvider")
    private PasswordValidProvider passwordValidProvider;

    public CaptchaDaoAuthenticationProvider(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder){
        this.setUserDetailsService(userDetailsService);
        this.setPasswordEncoder(passwordEncoder);
    }

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        // 图片验证
        CaptchaWebAuthenticationDetails captchaWebAuthenticationDetails = (CaptchaWebAuthenticationDetails) authentication.getDetails();
        if(!captchaWebAuthenticationDetails.getCaptchaIsRight()){
            throw new VerificationCodeException();
        }
        // 密码验证
        passwordValidProvider.additionalAuthenticationChecks(userDetails, authentication);
    }
}
