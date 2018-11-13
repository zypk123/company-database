package com.huntkey.rx.sceo.formula.provider.config;

import com.huntkey.rx.sceo.formula.engine.function.FunctionHandlerBuildIn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author chenfei on 2017/7/19.
 */
@Configuration
public class FunctionHandlerConfig {

    private Logger logger = LoggerFactory.getLogger(FunctionHandlerConfig.class);

    @Bean
    public FunctionHandlerBuildIn createFunctionHandlerBuildIn() {

        return new FunctionHandlerBuildIn();
    }
}
