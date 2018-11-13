package com.huntkey.rx.sceo.formula.provider;

import com.huntkey.rx.sceo.method.register.plugin.annotation.EnableDriverMethod;
import com.huntkey.rx.sceo.method.register.plugin.annotation.EnableMethodRegisterScanner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author lulx on 2017/6/14 0014.
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
//@EnableMethodRegisterScanner(startApplicationClass = FormulaProviderApplication.class,
//        edmServiceName = "${edmServiceName:modeler-provider}",
//        serviceApplicationName = "formula-provider")
@EnableDriverMethod
public class FormulaProviderApplication {

    @Bean
    @LoadBalanced
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public static void main(String[] args){
        SpringApplication.run(FormulaProviderApplication.class, args);
    }
}
