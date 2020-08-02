package com.demo.web;

import com.alibaba.fastjson.JSONObject;
import com.demo.model.po.LoginUserPo;
import com.demo.model.po.RegisterUserPo;
import com.demo.result.Result;
import com.demo.service.UserService;
import com.demo.util.JwtUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class LoginController {

    @Autowired
    private UserService userService;


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Result login(LoginUserPo po) {
        String loginName = po.getName();
        String passWord = po.getPassword();
        //身份验证
        boolean isSuccess = userService.checkUser(loginName, passWord);
        if (isSuccess) {
            //模拟数据库查询
            RegisterUserPo registerUserPo = userService.getUser(loginName);
            if (registerUserPo != null) {
                //返回token
                String token = JwtUtil.sign(loginName, passWord);
                if (StringUtils.isNotBlank(token)) {
                    return Result.success("成功", token);
                }
            }
        }
        return Result.fail();
    }

    @RequestMapping(value = "getUser", method = RequestMethod.POST)
    public Result getUserInfo(HttpServletRequest request, @RequestBody Map<String, String> map) {
        String loginName = map.get("loginName");
        String token = request.getHeader("token");
        boolean verity = JwtUtil.verity(token);
        if (verity) {
            RegisterUserPo registerUserPo = userService.getUser(loginName);
            if (registerUserPo != null) {
                return Result.success("成功", JSONObject.toJSONString(registerUserPo));
            }
        }
        return Result.fail();
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Result register(@Validated RegisterUserPo registerUserPo){
        return userService.register(registerUserPo);
    }


}

