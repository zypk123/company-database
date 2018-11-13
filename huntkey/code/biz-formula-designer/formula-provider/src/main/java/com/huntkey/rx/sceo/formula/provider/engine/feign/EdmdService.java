package com.huntkey.rx.sceo.formula.provider.engine.feign;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.sceo.formula.provider.engine.feign.hystrix.EdmdServiceHystrixImpl;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author chenfei on 2017/8/16 0016.
 */
@Component
@FeignClient(value = "modeler-provider", fallback = EdmdServiceHystrixImpl.class, url = "http://10.3.98.154:2002")
public interface EdmdService {

    /**
     * getEdmDataType
     *
     * @param edmdVer
     * @param name
     * @return
     */
    @RequestMapping(value = "/classes/getEdmDataType", method = RequestMethod.POST)
    Result getEdmDataType(@RequestParam(value = "edmdVer") String edmdVer,
                          @RequestParam(value = "name") String name);

    /**
     * 根据id获取属性
     *
     * @param id
     * @return RResult
     */
    @RequestMapping(value = "/properties/{id}", method = RequestMethod.GET)
    Result getProperty(@PathVariable(value = "id") String id);
}
