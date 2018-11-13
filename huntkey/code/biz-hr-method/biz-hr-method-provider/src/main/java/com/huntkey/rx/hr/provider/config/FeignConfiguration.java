package com.huntkey.rx.hr.provider.config;

import com.codingapi.tx.springcloud.feign.TransactionRestTemplateInterceptor;
import feign.Request;
import feign.RequestInterceptor;
import feign.Retryer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfiguration {
    @Bean
    Retryer retryer(){
        return Retryer.NEVER_RETRY;
    }
    @Bean
    Request.Options feignOptions() {
        return new Request.Options(1 * 1000, 10 * 1000);
    }

    @Bean
    public RequestInterceptor requestInterceptor(){
        return new TransactionRestTemplateInterceptor();
    }
}
