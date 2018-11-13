package com.huntkey.rx.sceo.common.service.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

import com.huntkey.rx.commons.framework.AppContext;

@SpringBootApplication
@EnableDiscoveryClient
@Configuration
@ComponentScan
@EnableAutoConfiguration
@EnableRedisHttpSession
public class CommonServiceProviderApplication {
	public static void main(String[] args) {	
		ApplicationContext applicationContext = SpringApplication.run(CommonServiceProviderApplication.class, args);
		AppContext.setApplicationContext(applicationContext);
		System.out.println("项目启动完成！");
	}
}
