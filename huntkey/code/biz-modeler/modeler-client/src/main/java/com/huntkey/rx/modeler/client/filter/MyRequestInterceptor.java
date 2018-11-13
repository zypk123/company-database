package com.huntkey.rx.modeler.client.filter;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

public class MyRequestInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate arg0) {
        RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes servletRequest = (ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes();
        HttpServletRequest req = servletRequest.getRequest();
        String auth = req.getHeader("Authorization");
        //String token = (String) req.getSession().getAttribute("token");
        arg0.header("Authorization", auth);
    }
}