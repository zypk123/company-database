package com.huntkey.rx.sceo.method.provider.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huntkey.rx.sceo.mpf.common.MsgClient;
import com.huntkey.rx.sceo.mpf.common.MsgClientFactory;
import com.huntkey.rx.sceo.mpf.exception.MsgException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

/**
 * Created by lulx on 2017/10/23 0023 上午 11:06
 */
public class MsgUtil {

    private static Logger logger = LoggerFactory.getLogger(MsgUtil.class);

    private static MsgClient client ;
    static {
        Properties prop = new Properties();
        try {
            client = MsgClientFactory.createClient(prop);
        } catch (MsgException e) {
            logger.error("创建MsgClient执行出错："+ e.getMessage(), e);
        }
    }

    /**
     *  发送kafka消息
     * @param topic 消息主题
     * @param msg 消息
     */
    public static void sendMsg(String topic, String msg){
        try {
            client.sendMessage(topic, msg);
        } catch (Exception e) {
            logger.error("发送消息 msg: "+msg+" ;topic : "+topic+" 执行出错："+ e.getMessage(), e);
        }
    };

    /**
     * 根据返回的结果组装任务调度系统需要的json格式
     * @return
     */
    public static JSONObject convertScheduleJson(String starTime, String endTime){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        JSONObject  jsonObject = new JSONObject();
        JSONObject  baseInfo = new JSONObject();
        baseInfo.put("jobId","00f686444d984e669c5cb25f2af19ccd");
        jsonObject.put("baseInfo",baseInfo);
        JSONArray jobSteps =  new JSONArray();
        JSONObject  jobStep = new JSONObject();
        jobStep.put("jobStepName","步骤1");
        //jobStep.put("jobStepPath","M00/00/00/CgNiSFnlX8WADTuFAA9OXmgxppU724.jar");
        jobStep.put("jobStepPath","M00/00/00/CgNiSVnm662APaiFAA9OZcIY8TE450.jar");
        jobStep.put("jobStepFail","exit");
        jobStep.put("jobStepOrder",1);
        jobStep.put("jobFileName","SparkTest.jar");
        jobStep.put("jobFileType","spark");
        jobSteps.add(jobStep);
        jsonObject.put("jobSteps",jobSteps);
        JSONArray jobPlans =  new JSONArray();
        JSONObject  jobPlan = new JSONObject();
        jobPlan.put("jobPlanName","计划1");
        jobPlan.put("jobPlanType","execute_repeat");
        jobPlan.put("isUse","1");
        jobPlan.put("jobPlanStartDate",dateFormat.format(new Date()));
        jobPlan.put("jobPlanPeriodType","day");
        jobPlan.put("jobPlanPeriod","1");
        //jobPlan.put("jobPlanOnceTime","");
        jobPlan.put("onceOrTimes",1);
        jobPlan.put("jobPlanStartTime",starTime);
        jobPlan.put("jobPlanEndTime",endTime);
        jobPlan.put("jobPlanPeriod2",5);
        jobPlan.put("period2Unit","minute");
        jobPlans.add(jobPlan);
        jsonObject.put("jobPlans",jobPlans);
        return jsonObject;
    }
}
