package com.huntkey.rx.sceo.serviceCenter.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

/**
 * Created by licj on 2017/6/22.
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class ServiceCenterClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceCenterClientApplication.class,args);
    }
}
