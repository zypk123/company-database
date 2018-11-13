package com.huntkey.rx.modeler.provider.config;

import com.huntkey.rx.modeler.provider.filter.HTTPBearerAuthorizeAttribute;
import feign.Request;
import feign.Retryer;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

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
        return new Request.Options(/**connectTimeoutMillis**/1 * 1000, /** readTimeoutMillis **/50 * 1000);
    }

    /*@Bean
    public FilterRegistrationBean jwtFilterRegistrationBean(){
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        HTTPBearerAuthorizeAttribute httpBearerFilter = new HTTPBearerAuthorizeAttribute();
        registrationBean.setFilter(httpBearerFilter);
        List<String> urlPatterns = new ArrayList<String>();
//        urlPatterns.add("/*");

        registrationBean.setUrlPatterns(urlPatterns);
        return registrationBean;
    }*/
}
