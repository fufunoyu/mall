package com.rhinoceros.mall.manager.impl.config;
/* created at 8:30 PM 3/17/2018  */

import com.lorne.alipay.config.AliPayConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AlipayConfig {

    @Value("#{alipayConfig['alipay.appId']}")
    private String appId;

    @Value("#{alipayConfig['alipay.appPrivateKey']}")
    private String appPrivateKey;

    @Value("#{alipayConfig['alipay.alipayPublicKey']}")
    private String alipayPublicKey;
    @Value("#{alipayConfig['alipay.signType']}")
    private String signType;

    @Bean
    public AliPayConfig aliPayConfig() {
        AliPayConfig config = new AliPayConfig();
        config.setAppId(appId);
        config.setAlipayPublicKey(alipayPublicKey);
        config.setAppPrivateKey(appPrivateKey);
        config.setSignType(signType);
        return config;
    }

}
