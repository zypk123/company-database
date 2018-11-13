package com.hunkey.feign;

import com.hunkey.feign.hystrix.DataSharingServiceHystrix;
import com.huntkey.rx.commons.utils.rest.Result;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by chenfei on 2017/8/1.
 */
@Component
@FeignClient(value = "SERVICECENTER-PROVIDER", fallback = DataSharingServiceHystrix.class)
public interface DataSharingService {

    @RequestMapping(value = "/servicecenter/find", method = RequestMethod.POST)
    Result find(@RequestBody String data);

    @RequestMapping(value = "/servicecenter/load", method = RequestMethod.POST)
    Result load(@RequestBody String data);
}
