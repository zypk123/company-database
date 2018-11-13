package com.huntkey.rx.sceo.formula.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

/**
 * @author lulx on 2017/6/14 0014.
 */
@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
@EnableHystrix
public class FormulaClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(FormulaClientApplication.class, args);
    }
}
