package com.huntkey.rx.purchase.provider;

import com.huntkey.rx.sceo.method.register.plugin.annotation.EnableMethodRegisterScanner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

/**
 * 启动类
 *
 * @author zhangyu
 */
@SpringBootApplication(scanBasePackages = "com.huntkey.rx")
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.huntkey.rx.purchase.provider.client")
//@EnableMethodRegisterScanner(startApplicationClass = PurchaseProviderApplication.class,
//        edmServiceName = "${edmServiceName:modeler-provider}",
//        serviceApplicationName = "${spring.application.name}")
public class PurchaseProviderApplication {
    public static void main(String[] args) {
        SpringApplication.run(PurchaseProviderApplication.class, args);
        System.out.println("启动成功");
    }
}
