package com.huntkey.rx.purchase.provider.config;

import com.huntkey.rx.commons.utils.filter.HTTPBearerAuthorizeAttributeFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.ArrayList;
import java.util.List;

/**
 * 跨域问题解决
 *
 * @author zhangyu
 */
@Configuration
public class CorsConfig {
    private CorsConfiguration buildConfig() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("*");
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedMethod("*");
        corsConfiguration.setAllowCredentials(true);
        return corsConfiguration;
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", buildConfig());
        return new CorsFilter(source);
    }

    @Bean
    public FilterRegistrationBean jwtFilterRegistrationBean() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        HTTPBearerAuthorizeAttributeFilter httpBearerFilter = new HTTPBearerAuthorizeAttributeFilter();
        registrationBean.setFilter(httpBearerFilter);
        List<String> urlPatterns = new ArrayList<>();
        //过滤url先写死，后面根据需求再修改
        urlPatterns.add("/hr/positiondefinition/*todo");
        registrationBean.setUrlPatterns(urlPatterns);
        return registrationBean;
    }
}
