package com.huntkey.rx.sceo.formula.provider.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.net.URL;

/**
 * @author chenfei on 2017/8/8.
 */
@Configuration
public class TempClassFileConfig {

    private Logger logger = LoggerFactory.getLogger(TempClassFileConfig.class);

    @Value("${tmpPath}")
    private String tmpPath;

    public String getTmpPath() {

        URL location = this.getClass().getProtectionDomain().getCodeSource().getLocation();
        logger.info("location: {}", location.getPath());
        if (".".equals(tmpPath)) {
            tmpPath = location.getPath();
        }

        logger.info("tmpPath: {}", tmpPath);
        return tmpPath;
    }
}
