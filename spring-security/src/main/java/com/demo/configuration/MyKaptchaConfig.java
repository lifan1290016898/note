package com.demo.configuration;

import com.google.code.kaptcha.Producer;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * 图形验证码配置
 */
@Configuration
public class MyKaptchaConfig {

    @Bean
    public Producer producer() {
        Properties properties = new Properties();
        // 图片宽度
        properties.setProperty("kaptcha.img.width","150");
        // 图片长度
        properties.setProperty("kaptcha.img.height","50");
        // 字符集
        properties.setProperty("kaptcha.textproducer.char","qwertyuioplkjhgfdsazxcvbnmQWERTYUIOPLKJHGFDSAZXCVBNM1234567890");
        // 字符长度
        properties.setProperty("kaptcha.textproducer.char","4");
        Config config = new Config(properties);
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        defaultKaptcha.setConfig(config);
        return defaultKaptcha;
    }


}
