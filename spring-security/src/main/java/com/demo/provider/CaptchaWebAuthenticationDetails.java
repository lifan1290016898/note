package com.demo.provider;

import org.apache.commons.lang.StringUtils;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class CaptchaWebAuthenticationDetails extends WebAuthenticationDetails {

    private boolean captchaIsRight = true;

    public boolean getCaptchaIsRight(){
        return this.captchaIsRight;
    }


    public CaptchaWebAuthenticationDetails(HttpServletRequest request) {
        super(request);
        String captcha = request.getParameter("captcha");
        HttpSession session = request.getSession();
        String saveCode = (String) session.getAttribute("captcha");
        if(StringUtils.isNotBlank(saveCode)){
            session.removeAttribute("captcha");
            if(!StringUtils.equals(captcha, saveCode)){
                this.captchaIsRight = false;
            }
        }
    }
}
