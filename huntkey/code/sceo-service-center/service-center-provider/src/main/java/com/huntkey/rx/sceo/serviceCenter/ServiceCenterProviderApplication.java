package com.huntkey.rx.sceo.serviceCenter;

import com.huntkey.rx.sceo.serviceCenter.provider.orm.config.DynamicDataSourceRegister;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Import;

/**
 * Created by Chenxiaojun on 2017/6/22.
 */
@SpringBootApplication
@EnableDiscoveryClient
@Import(DynamicDataSourceRegister.class)
@EnableFeignClients(basePackages = "com.huntkey.rx.sceo.serviceCenter.provider.client")
public class ServiceCenterProviderApplication {
	public static void main(String[] args) {
		SpringApplication.run(ServiceCenterProviderApplication.class, args);
	}
}
