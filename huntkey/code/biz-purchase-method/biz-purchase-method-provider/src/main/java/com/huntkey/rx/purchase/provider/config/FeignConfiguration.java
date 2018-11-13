package com.huntkey.rx.purchase.provider.config;

import feign.Request;
import feign.Retryer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhangyu
 */
@Configuration
public class FeignConfiguration {

    @Bean
    Retryer retryer() {
        return Retryer.NEVER_RETRY;
    }

    @Bean
    Request.Options feignOptions() {
        return new Request.Options(1 * 1000, 10 * 1000);
    }
    
}
