package com.demo.exception;

import com.alibaba.fastjson.JSONObject;
import com.demo.result.Result;
import org.springframework.security.core.AuthenticationException;


public class VerificationCodeException extends AuthenticationException {

    public VerificationCodeException(){
        super(JSONObject.toJSONString(Result.fail("验证码错误")));
    }


}
