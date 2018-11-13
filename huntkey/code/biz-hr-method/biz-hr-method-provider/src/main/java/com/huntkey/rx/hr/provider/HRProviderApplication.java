package com.huntkey.rx.hr.provider;

import com.huntkey.rx.sceo.method.register.plugin.annotation.EnableMethodRegisterScanner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = "com.huntkey.rx")
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.huntkey.rx.hr.provider.client")
//@EnableMethodRegisterScanner(startApplicationClass = HRProviderApplication.class,
//        edmServiceName = "${edmServiceName:modeler-provider}",
//        serviceApplicationName = "${spring.application.name}")
public class HRProviderApplication {
    public static void main(String[] args) {
        SpringApplication.run(HRProviderApplication.class, args);
    }

}
