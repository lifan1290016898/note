package com.custom.service;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("custom.warp")
public class WarpProperties {

    private String befor;

    private String after;

    public String getBefor() {
        return befor;
    }

    public void setBefor(String befor) {
        this.befor = befor;
    }

    public String getAfter() {
        return after;
    }

    public void setAfter(String after) {
        this.after = after;
    }
}
