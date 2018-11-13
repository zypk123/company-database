package com.huntkey.rx.commons.utils.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by zhaomj on 2017/5/26.
 */
public class CookieRequestInterceptor implements RequestInterceptor {
	public void apply(RequestTemplate requestTemplate) {
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes();
		if (requestAttributes != null) {
			HttpServletRequest request = requestAttributes.getRequest();
			Cookie cookies[] = request.getCookies();
			StringBuilder sb = new StringBuilder();
			if (cookies != null) {
				for (int i = 0; i < cookies.length; i++) {
					if (i > 0) {
						sb.append("; ");
					}
					sb.append(cookies[i].getName()).append("=").append(cookies[i].getValue());
				}
				requestTemplate.header("Cookie", sb.toString());
			}
		}
	}
}
