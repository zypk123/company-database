package com.huntkey.rx.sceo.method.provider.entity;

import com.huntkey.rx.sceo.method.provider.util.RestUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by lulx on 2017/10/24 0024 下午 2:08
 */
public class MethodAsync implements Runnable {

    private Logger logger = LoggerFactory.getLogger(MethodAsync.class);

    private String methodUrl;
    private String reqType;
    private String params;

    public MethodAsync(String methodUrl, String reqType, String params) {
        this.methodUrl = methodUrl;
        this.reqType = reqType;
        this.params = params;
    }

    @Override
    public void run() {
        try{
            RestUtil.doExec(methodUrl, reqType, params);
        }catch (Exception e){
            logger.error("异步方法"+methodUrl+"执行出错："+ e.getMessage(), e);
        }
    }
}
