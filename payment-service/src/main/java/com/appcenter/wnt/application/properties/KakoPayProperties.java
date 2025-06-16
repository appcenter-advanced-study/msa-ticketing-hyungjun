package com.appcenter.wnt.application.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "kakaopay")
public class KakoPayProperties {
    private String secretKey;
    private String cid;

    public String getSecretKey() {
        return secretKey;
    }

    public String getCid() {
        return cid;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }
}
