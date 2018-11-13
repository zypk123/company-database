package com.huntkey.rx.modeler.client.config;

import com.huntkey.rx.modeler.client.filter.MyRequestInterceptor;
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
        return new Request.Options(/**connectTimeoutMillis**/1 * 1000, /** readTimeoutMillis **/500 * 1000);
    }
  /*  @Bean
    public MyRequestInterceptor requestInterceptor(){
        return new MyRequestInterceptor();
    }*/
}
