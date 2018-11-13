package com.huntkey.rx.sceo.formula.provider.config;

import com.huntkey.rx.sceo.formula.provider.utils.FreeMarkerUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * freeMarker配置文件
 *
 * @author zhangyu
 * @create 2017-07-27 10:26
 **/
@Configuration
public class FreeMarkerConfig {

    @Value("${tmpPath}")
    private String srcFilePath;

    @Bean
    public FreeMarkerUtils freeMarkerUtils() {
        return new FreeMarkerUtils(srcFilePath);
    }
}
