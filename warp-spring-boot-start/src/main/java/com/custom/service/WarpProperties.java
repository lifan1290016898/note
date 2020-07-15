package com.custom.service;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("custom.warp")
public class WarpProperties {

    private String befor;

    private String after;
}
