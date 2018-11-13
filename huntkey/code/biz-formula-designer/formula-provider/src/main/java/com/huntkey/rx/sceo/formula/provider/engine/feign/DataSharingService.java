package com.huntkey.rx.sceo.formula.provider.engine.feign;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.sceo.formula.provider.engine.feign.hystrix.DataSharingServiceHystrixImpl;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author chenfei on 2017/8/1.
 */
@Component
@FeignClient(value = "SERVICECENTER-PROVIDER", fallback = DataSharingServiceHystrixImpl.class, url = "http://10.3.98.155:2008")
public interface DataSharingService {

    /**
     * find
     *
     * @param data
     * @return
     */
    @RequestMapping(value = "/servicecenter/find", method = RequestMethod.POST)
    Result find(@RequestBody String data);

    /**
     * load
     *
     * @param data
     * @return
     */
    @RequestMapping(value = "/servicecenter/load", method = RequestMethod.POST)
    Result load(@RequestBody String data);
}
