package com.huntkey.rx.sceo.formula.provider.config;

import org.I0Itec.zkclient.ZkClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author chenfei on 2017/7/25.
 */
@Configuration
public class ZkClientConfig {

    @Value("${zkClient.servers}")
    private String zkServers;

    @Bean
    public ZkClient createZKClient() {

        return new ZkClient(zkServers);
    }

}
