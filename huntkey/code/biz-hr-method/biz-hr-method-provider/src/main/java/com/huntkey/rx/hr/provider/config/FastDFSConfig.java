package com.huntkey.rx.hr.provider.config;


import com.github.tobato.fastdfs.FdfsClientConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by licj on 2017/4/19.
 */
@Configuration
@Import(FdfsClientConfig.class)
public class FastDFSConfig {

}
