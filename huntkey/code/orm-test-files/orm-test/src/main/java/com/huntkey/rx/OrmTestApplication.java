package com.huntkey.rx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class OrmTestApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrmTestApplication.class, args);
    }
}
