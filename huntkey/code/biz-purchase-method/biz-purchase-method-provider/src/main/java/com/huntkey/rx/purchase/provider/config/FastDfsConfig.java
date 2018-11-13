package com.huntkey.rx.purchase.provider.config;


import com.github.tobato.fastdfs.FdfsClientConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author licj on 2017/4/19.
 */
@Configuration
@Import(FdfsClientConfig.class)
public class FastDfsConfig {

}
