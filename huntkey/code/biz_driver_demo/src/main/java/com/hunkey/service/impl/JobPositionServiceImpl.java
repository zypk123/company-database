package com.hunkey.service.impl;

import com.alibaba.fastjson.JSON;
import com.hunkey.entity.ParamsVo;
import com.hunkey.feign.DataSharingService;
import com.hunkey.service.JobPositionService;
import com.hunkey.util.ExecUtil;
import com.huntkey.rx.commons.utils.redis.RedisClusterUtils;
import com.huntkey.rx.commons.utils.rest.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by lulx on 2017/10/23 0023 上午 8:31
 */
@Service
public class JobPositionServiceImpl implements JobPositionService {

    private Logger logger = LoggerFactory.getLogger(JobPositionServiceImpl.class);

    @Autowired
    private DataSharingService dataSharingService;

    @Override
    public Result loadJobPosition(Map map, HttpServletRequest request) {
        String id = String.valueOf(map.get("id"));
        String data = "{\"edmName\":\"jobposition\",\"search\":{\"conditions\":[{\"attr\":\"id\",\"operator\":\"=\",\"value\":\""+ id +"\"}]}}";
        return dataSharingService.find(data);
    }

    @Override
    public Result reverseCall(String params) {
        ParamsVo paramsVo = new ParamsVo();
        paramsVo.setParamObj(JSON.parseObject(params, Map.class));
        paramsVo.setClassName("salesposition");
        paramsVo.setMethodName("execReverseCall");
        Result exec = ExecUtil.exec(paramsVo);
        System.out.println("reverseCall 异步方法调用返回：" + exec);
        return exec;
    }

    @Override
    public Result creatSession(Map map) {
        //将session值缓存入redis
        RedisClusterUtils rcu = new RedisClusterUtils(" 10.3.98.153:7000,10.3.98.153:7001,10.3.98.154:7002,10.3.98.154:7003,10.3.98.155:7004,10.3.98.155:7005");
        String cid = (String) map.get("complanyId");
        String jod = (String) map.get("jobId");
        String emd = (String) map.get("employId");
        String sessionValue = "12345";
        rcu.setValue(cid+"_"+jod+"_"+emd,sessionValue);
        //返回前台session
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        result.setData(cid+"_"+jod+"_"+emd);
        return result;
    }

    @Override
    public Result execAuto(String params) {
        logger.info("自动方法执行成功！！！！ 获取参数为：" + params);
        System.out.println("自动方法执行成功！！！！ 获取参数为：" + params);
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        result.setData("自动方法执行成功！！！！");
        return result;
    }

    @Override
    public Result execAsync(String params) {
        logger.info("异步方法执行成功！！！！ 获取参数为：" + params);
        System.out.println("异步方法执行成功！！！！ 获取参数为：" + params);
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        result.setData("异步方法执行成功！！！！");
        return result;
    }

    @Override
    public Result execReverseCall(String params) {
        logger.info("循环调用方法执行成功！！！！ 获取参数为：" + params);
        System.out.println("循环调用方法执行成功！！！！ 获取参数为：" + params);
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        result.setData("循环调用方法执行成功！！！！");
        return result;
    }

    @Override
    public Result execSync(String params) {
        logger.info("同步调用方法执行成功！！！！ 获取参数为：" + params);
        System.out.println("同步调用方法执行成功！！！！ 获取参数为：" + params);
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        result.setData("同步调用方法执行成功！！！！");
        return result;
    }
}
