package com.huntkey.rx.modeler.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

/**
 * Created by zhaomj on 2017/4/5.
 */
@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
@EnableHystrix
public class ModelerClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(ModelerClientApplication.class,args);
    }
}
