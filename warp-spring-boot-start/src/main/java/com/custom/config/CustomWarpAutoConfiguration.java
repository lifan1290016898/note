package com.custom.config;

import com.custom.service.WarpProperties;
import com.custom.service.WarpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(WarpProperties.class)
@ConditionalOnClass(WarpService.class)
public class CustomWarpAutoConfiguration {

    @Autowired
    private WarpProperties properties;

    @Bean
    @ConditionalOnMissingBean
    public WarpService warpBean(){
        return new WarpService(properties.getBefor(),properties.getAfter());
    }


}
