package com.huntkey.rx.sceo.method.provider.service.impl;

import com.alibaba.fastjson.JSON;
import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.commons.utils.string.StringUtil;
import com.huntkey.rx.sceo.method.provider.entity.*;
import com.huntkey.rx.sceo.method.provider.feign.BizModelerService;
import com.huntkey.rx.sceo.method.provider.feign.FormulaService;
import com.huntkey.rx.sceo.method.provider.service.ExecService;
import com.huntkey.rx.sceo.method.provider.util.MethodType;
import com.huntkey.rx.sceo.method.provider.util.RestUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by lulx on 2017/10/19 0019 上午 9:41
 */
@Service
public class ExecServiceimpl implements ExecService {
    private Logger logger = LoggerFactory.getLogger(ExecServiceimpl.class);

    @Value("${kafkaOozieTopic}")
    String kafkaOozieTopic;

    @Value("${methodAutoTopic}")
    String methodAutoTopic;

    @Autowired
    private FormulaService formulaService;

    @Autowired
    private BizModelerService modelerService;

    private static ExecutorService runnableService;

    static {
        runnableService = new ThreadPoolExecutor(5, 10, 200, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(50));
    }
    private static Set<ObjVo> objSets = new HashSet<ObjVo>();

    @Override
    public Result execObject(ParamsVo paramVo) {

        String className = paramVo.getClassName();

        String methodName = paramVo.getMethodName();

        final String params = JSON.toJSONString(paramVo.getParamObj());
        long start = System.currentTimeMillis();
        Result modelerResult = modelerService.getMethodInfo(className, methodName);
        long end = System.currentTimeMillis();
        logger.info(" [-modelerService-] Spend time : {}", (end - start));
        logger.info("className: "+className+"; methodName: "+methodName+"; modelerResult : " + modelerResult);

        String methodUrl;

        String edmmMethodType; // 方法类型 执行判断

        String edmmExecRate; // 自动方法频率

        String edmmStartTime; // 定时任务开始时间

        String edmmEndTime; // 定时任务结束时间

        String edmmRequestType; // 方法请求类型 get post delete put

        String edmmTriggerCond; // 方法绑定公式ID

        if(Result.RECODE_SUCCESS.equals(modelerResult.getRetCode()) && !StringUtil.isNullOrEmpty(modelerResult.getData())){
            Map<String,Object> retMap = (Map)modelerResult.getData();
            // TODO 临时代替 edmmArithmeticDesc
            methodUrl = retMap.containsKey("edmmArithmeticDesc") ? String.valueOf(retMap.get("edmmArithmeticDesc")) : "";
            edmmMethodType = retMap.containsKey("edmmMethodType") ? String.valueOf(retMap.get("edmmMethodType")) : "";
            edmmExecRate = retMap.containsKey("edmmExecRate") ? String.valueOf(retMap.get("edmmExecRate")) : "";
            edmmStartTime = retMap.containsKey("edmmStartTime") ? String.valueOf(retMap.get("edmmStartTime")) : "";
            edmmEndTime = retMap.containsKey("edmmEndTime") ? String.valueOf(retMap.get("edmmEndTime")) : "";
            edmmRequestType = retMap.containsKey("edmmRequestType") ? String.valueOf(retMap.get("edmmRequestType")) : "";
            edmmTriggerCond = retMap.containsKey("edmmTriggerCond") ? String.valueOf(retMap.get("edmmTriggerCond")) : "";
        }else{
            return getResultVo(Result.RECODE_ERROR, "调用modeler获取方法接口路径接口出错", "");
        }
        Result result = null;

        if(StringUtil.isNullOrEmpty(edmmMethodType) || StringUtil.isNullOrEmpty(methodUrl) || StringUtil.isNullOrEmpty(edmmRequestType)){
            return getResultVo(Result.RECODE_ERROR, "调用modeler获取方法接口路径接口出错", "");
        }

        if(MethodType.EXECSYNC.getMethodType().equalsIgnoreCase(edmmMethodType)){
            result = execSync(methodUrl, edmmRequestType, params);
        }else if(MethodType.EXECASYNC.getMethodType().equalsIgnoreCase(edmmMethodType)){
            result = execAsync(methodUrl, edmmRequestType, params);
        }else if(MethodType.EXECTIMING.getMethodType().equalsIgnoreCase(edmmMethodType)){
            result = execTiming(methodUrl, edmmRequestType, params, edmmStartTime, edmmEndTime);
        }else if(MethodType.EXECAUTO.getMethodType().equalsIgnoreCase(edmmMethodType)){
              result = execAuto(edmmTriggerCond, methodUrl, edmmRequestType, params, edmmExecRate);
        }else{
            return getResultVo(Result.RECODE_ERROR, "调用modeler获取方法接口获取错误的方法类型：" + edmmMethodType, "");
        }
        return result;
    }

    /**
     *  同步方法执行
     * @param methodUrl  业务接口 http://driver-service-demo/jobPosition/loadJobPosition
     *                  driver-service-demo : eureka 中注册的服务名
     * @param reqType 请求类型 get post delete put patch
     * @param params 前端传入的参数直接转发
     *  @return Result
     */
    private Result execSync(String methodUrl, String reqType, String params) {
        return RestUtil.doExec(methodUrl, reqType, params);
    }

    /**
     *  异步方法执行
     * @param methodUrl 业务接口 http://driver-service-demo/jobPosition/loadJobPosition
     *                  driver-service-demo : eureka 中注册的服务名
     * @param reqType 请求类型
     * @param params 方法参数
     * @return Result
     */
    private Result execAsync(final String methodUrl, final String reqType, final String params) {
        MethodAsync methodAsync = new MethodAsync(methodUrl, reqType, params);
        runnableService.execute(methodAsync);
        return getResultVo(Result.RECODE_SUCCESS, "异步方法执行！！！", "");
    }

    /**
     *  定时执行(发送kafka信息)
     * @param methodUrl 方法实现接口url
     * @param reqType 方法接口请求类型
     * @param params 参数
     * @param startTime 定时任务开始时间 hh:mm:ss
     * @param endTime 定时任务结束时间 hh:mm:ss
     */
    public Result execTiming(String methodUrl, String reqType, String params, String startTime, String endTime){
        MethodTiming methodTiming = new MethodTiming(kafkaOozieTopic,methodUrl, reqType, params, startTime, endTime);
        runnableService.execute(methodTiming);
        return getResultVo(Result.RECODE_SUCCESS, "定时方法执行！！！", "");
    }

    /**
     * 自动执行
     * @param formulaId 公式ID
     * @param methodUrl 方法实现接口url
     * @param reqType 方法接口请求类型
     * @param params 参数
     * @param execRate 执行频率 一次/多次 0/1
     */
    public Result execAuto(final String formulaId, final String methodUrl, final String reqType, final String params, String execRate) {
        MethodAuto methodAuto = new MethodAuto(objSets, formulaService, methodAutoTopic, formulaId, methodUrl, reqType, params, execRate);
        runnableService.execute(methodAuto);
        return getResultVo(Result.RECODE_SUCCESS, "自动方法执行！！！", "");
    }

    /**
     * 组装Result
     * @param retCode 默认值 Result.RECODE_ERROR
     * @param msg
     * @param data
     * @return
     */
    private static Result getResultVo(int retCode, String msg, Object data){
        Result result = new Result();

        if(!StringUtil.isNullOrEmpty(msg)){
            result.setErrMsg(msg);
        }
        if(!StringUtil.isNullOrEmpty(data)){
            result.setData(data);
        }
        if(!StringUtil.isNullOrEmpty(retCode)){
            result.setRetCode(retCode);
        }else{
            result.setRetCode(Result.RECODE_ERROR);
        }
        return result;
    }
}
