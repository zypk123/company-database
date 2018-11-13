package com.huntkey.rx.modeler.provider.feign.hystrix;

import com.huntkey.rx.commons.utils.rest.Result;

import com.huntkey.rx.modeler.provider.feign.OrmClient;
import com.huntkey.rx.modeler.provider.feign.ScheduleClient;
import com.huntkey.rx.sceo.schedule.commom.model.JobInfo;
import org.springframework.stereotype.Component;

/**
 * Created by licj on 2017/7/20.
 */
@Component
public class ScheduleHystrix implements ScheduleClient {


    @Override
    public Result checkPlan(String jobId, String planName) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_ERROR);
        result.setErrMsg("Orm调用服务降级处理逻辑！");
        return result;
    }

    @Override
    public Result commit(JobInfo jobInfo) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_ERROR);
        result.setErrMsg("Orm调用服务降级处理逻辑！");
        return result;
    }

    @Override
    public Result registerByEDM(String methodName, String planId, String edmmId) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_ERROR);
        result.setErrMsg("Orm调用服务降级处理逻辑！");
        return result;
    }


}
