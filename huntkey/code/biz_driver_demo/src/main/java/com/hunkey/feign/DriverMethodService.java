package com.hunkey.feign;

import com.hunkey.entity.ParamsVo;
import com.hunkey.feign.hystrix.DriverMethodServiceHystrix;
import com.huntkey.rx.commons.utils.rest.Result;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by lulx on 2017/10/18 0018 下午 4:04
 */
@Component
@FeignClient(value = "METHOD-PROVIDER", fallback = DriverMethodServiceHystrix.class)
public interface DriverMethodService {

    @RequestMapping(value = "/methodExec/exec", method = RequestMethod.POST)
    Result exec(@RequestBody ParamsVo params);
}
