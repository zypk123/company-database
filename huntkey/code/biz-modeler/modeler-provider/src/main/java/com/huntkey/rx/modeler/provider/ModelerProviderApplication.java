package com.huntkey.rx.modeler.provider;

import com.huntkey.rx.commons.config.ConfigFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@SpringBootApplication
@EnableDiscoveryClient
@EnableRedisHttpSession
@EnableFeignClients(basePackages = "com.huntkey.rx.modeler.provider.feign")
//@EnableHystrix
public class ModelerProviderApplication {
	public static void main(String[] args) {
		// ConfigFactory.init("E:/IdeaProjects/biz-modeler/modeler-provider/conf/config.xml");
		SpringApplication.run(ModelerProviderApplication.class, args);
	}
}
