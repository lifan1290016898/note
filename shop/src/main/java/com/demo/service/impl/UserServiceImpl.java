package com.demo.service.impl;

import com.demo.mapper.UserMapper;
import com.demo.model.bo.BaseUserBo;
import com.demo.model.bo.LoginUserBo;
import com.demo.model.bo.RegisterUserBo;
import com.demo.model.po.LoginUserPo;
import com.demo.model.po.RegisterUserPo;
import com.demo.result.Result;
import com.demo.service.UserService;
import com.demo.util.CodeMd5;
import com.demo.util.JwtUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper mapper;

    private Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public Result login(LoginUserPo po, HttpServletRequest request) {
        if(po == null){
            return Result.fail("参数不能为空");
        }
        String role = po.getRole();
        LoginUserBo bo = new LoginUserBo();
        BeanUtils.copyProperties(po, bo);
        bo.setPassword(CodeMd5.codeMd5(bo.getPassword()));
        BaseUserBo baseUserBo = null;
        // 普通用户登录
        if(StringUtils.equals(role, "isBuyers")){
            baseUserBo = mapper.customerLogin(bo);
        } else if(StringUtils.equals(role, "isSellers")){
            // 商家登录
            baseUserBo = mapper.businessLogin(bo);
        }
        String token = this.generateUserToken(baseUserBo);
        // TODO 此处当做前端的处理(全局header)
        ServletContext servletContext = request.getServletContext();
        servletContext.setAttribute("token", token);
        Result result = StringUtils.isNotBlank(token) && baseUserBo != null ?  Result.success("登录成功", token) : Result.fail();
        return result;
    }

    @Override
    public Result register(RegisterUserPo registerUserPo) {
        if(registerUserPo == null){
            return Result.fail("参数不能为空");
        }
        RegisterUserBo bo = new RegisterUserBo();
        BeanUtils.copyProperties(registerUserPo, bo);
        bo.setPassword(CodeMd5.codeMd5(bo.getPassword()));
        String role = registerUserPo.getRole();
        if(StringUtils.equals(role, "buyers")){
            // 普通用户注册
            if(mapper.registerBuyers(bo) > 0){
                return Result.success();
            }
            return Result.fail();
        }
        // 商家注册
        if(mapper.registerSellers(bo) > 0){
            return Result.success();
        }
        return Result.fail();
    }

    private String generateUserToken(BaseUserBo bo){
        try {
            return JwtUtil.createToken(bo.getId(), bo.getName(), bo.getPassword());
        } catch (Exception e) {
            log.debug(e.getMessage(), e);
        }
        return null;
    }


}
