package com.demo.web;

import com.google.code.kaptcha.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Controller
public class CaptchaController {

    @Autowired
    private Producer producer;

    @GetMapping("/captcha")
    public void getCaptcha(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("img/jpeg");
        // 创建验证码
        String capText = producer.createText();
        // 设置验证码到session中
        request.getSession().setAttribute("captcha", capText);
        // 创建img图片
        BufferedImage image = producer.createImage(capText);
        // 获取输出流
        ServletOutputStream outputStream = response.getOutputStream();
        ImageIO.write(image,"jpg", outputStream);
        try {
          outputStream.flush();
        } finally {
            outputStream.close();
        }
    }


}
