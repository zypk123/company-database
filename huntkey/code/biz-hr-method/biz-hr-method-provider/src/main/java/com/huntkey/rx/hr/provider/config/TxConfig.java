package com.huntkey.rx.hr.provider.config;

import com.netflix.loadbalancer.PollingServerListUpdater;
import com.netflix.loadbalancer.ServerListUpdater;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TxConfig {
    @Bean
    ServerListUpdater serverListUpdater(){
        return new PollingServerListUpdater();
    }
}
