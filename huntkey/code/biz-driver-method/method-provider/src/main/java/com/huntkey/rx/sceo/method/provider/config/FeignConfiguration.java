package com.huntkey.rx.sceo.method.provider.config;

import feign.Request;
import feign.Retryer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by xuyf on 2017/7/27.
 */
@Configuration
public class FeignConfiguration {

    @Bean
    Retryer retryer(){
        return Retryer.NEVER_RETRY;
    }

    @Bean
    Request.Options feignOptions() {
        return new Request.Options(/**connectTimeoutMillis**/10 * 1000, /** readTimeoutMillis **/20 * 1000);
    }
}
