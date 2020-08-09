package com.demo.configuration;

import com.demo.label.ThSysDialect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LabelConfig {

    @Bean
    public ThSysDialect thSysDialect(){
        return new ThSysDialect();
    }


}
