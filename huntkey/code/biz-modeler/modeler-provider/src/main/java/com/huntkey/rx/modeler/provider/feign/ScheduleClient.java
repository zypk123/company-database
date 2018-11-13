package com.huntkey.rx.modeler.provider.feign;

import com.alibaba.fastjson.JSONObject;
import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.modeler.provider.feign.hystrix.OrmHystrix;
import com.huntkey.rx.modeler.provider.feign.hystrix.ScheduleHystrix;
import com.huntkey.rx.sceo.schedule.commom.model.JobInfo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by licj on 2017/7/20.
 */
@FeignClient(value = "sceo-schedule-provider",fallback = ScheduleHystrix.class)
public interface ScheduleClient {

    @RequestMapping(value = "/schedule/checkPlan", method = RequestMethod.GET)
    Result checkPlan(@RequestParam(value = "jobId") String jobId,@RequestParam(value = "planName") String planName);


    @RequestMapping(value = "/schedule/commit", method = RequestMethod.POST)
    Result commit(@RequestBody JobInfo jobInfo);

    @RequestMapping(value = "/jobs/registerByEDM", method = RequestMethod.POST)
    Result registerByEDM(@RequestParam(value = "methodName") String methodName, @RequestParam(value = "planId") String planId,@RequestParam(value = "edmmId") String edmmId);
}
