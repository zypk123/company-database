package com.huntkey.rx.modeler.client.feign;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.modeler.client.feign.hystrix.EdmNumberHystrix;
import com.huntkey.rx.modeler.common.model.EdmNumber;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "modeler-provider", fallback = EdmNumberHystrix.class)
public interface EdmNumberService {
    @RequestMapping(value="/numbers", method = RequestMethod.POST)
    Result update(@RequestBody EdmNumber edmNumber);
}
