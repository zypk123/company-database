package com.cy.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import com.cy.web.handler.SystemWebSocketHandler;
import com.cy.web.interceptor.HandshakeInterceptor;

//@Configuration
//@EnableWebSocket
/**
* @Title: WebSocketConfig.java 
* @Package com.cy.web.config 
* @Description: 暂时不用，后续需要可以启用
* @author lil@cychina.cn
* @date 2015年9月9日 下午3:20:19 
* @version V1.0   
 */
public class WebSocketConfig extends WebMvcConfigurationSupport implements
        WebSocketConfigurer {

    public WebSocketConfig(){
    	
    }
    
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(systemWebSocketHandler(), "/websckServer").addInterceptors(new HandshakeInterceptor());
        registry.addHandler(systemWebSocketHandler(), "/sockjs/websckServer/info").addInterceptors(new HandshakeInterceptor());
    }
    
    @Bean
    public WebSocketHandler systemWebSocketHandler() {
        return new SystemWebSocketHandler();
    }
 
}
