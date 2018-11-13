package com.huntkey.rx.sceo.formula.provider.config;

import feign.Request;
import feign.Retryer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author xuyf on 2017/7/27.
 */
@Configuration
public class FeignConfiguration {

    @Bean
    Retryer retryer(){
        return Retryer.NEVER_RETRY;
    }

    @Bean
    Request.Options feignOptions() {
        /**connectTimeoutMillis**/
        /** readTimeoutMillis **/
        return new Request.Options(1 * 1000, 30 * 1000);
    }
}
