package com.huntkey.rx.modeler.provider.feign;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.modeler.provider.feign.hystrix.OrmHystrix;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * Created by licj on 2017/7/20.
 */
@FeignClient(value = "serviceCenter-provider",fallback = OrmHystrix.class)
public interface OrmClient {

    @RequestMapping(value = "/servicecenter/find", method = RequestMethod.POST)
    Result find(@RequestBody String data);

    @RequestMapping(value = "/servicecenter/load", method = RequestMethod.POST)
    Result load(@RequestBody String data);

    @RequestMapping(value = "/servicecenter/update",method = RequestMethod.POST)
    Result update(@RequestBody String data);

}
