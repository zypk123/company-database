package com.huntkey.rx.sceo.method.provider.feign;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.sceo.method.provider.feign.hystrix.BizModelerServiceHystrix;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * , url = "10.3.99.16:2001"
 * Created by lulx on 2017/10/14 0014 上午 10:38
 */
@FeignClient(value = "modeler-provider", fallback = BizModelerServiceHystrix.class)
public interface BizModelerService {

    @RequestMapping(value = "/classes/getMethod/{className}/{methodName}", method = RequestMethod.GET)
    Result getMethodInfo(@PathVariable("className")String className, @PathVariable("methodName")String methodName);

}
