package com.huntkey.rx.sceo.method.provider.entity;

import com.huntkey.rx.sceo.method.provider.util.MsgUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by lulx on 2017/10/24 0024 下午 2:25
 */
public class MethodTiming implements Runnable {

    private static Logger logger = LoggerFactory.getLogger(MethodTiming.class);

    private String topicName;
    private String methodUrl;
    private String reqType;
    private String params;
    private String startTime;
    private String endTime;

    public MethodTiming(String topicName, String methodUrl, String reqType, String params, String startTime, String endTime) {
        this.topicName = topicName;
        this.methodUrl = methodUrl;
        this.reqType = reqType;
        this.params = params;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public void run() {
        try {
            MsgUtil.sendMsg(topicName,MsgUtil.convertScheduleJson(startTime,endTime).toJSONString());
        }catch (Exception e){
            logger.error("定时方法"+methodUrl+"执行出错："+ e.getMessage(), e);
        }
    }
}
