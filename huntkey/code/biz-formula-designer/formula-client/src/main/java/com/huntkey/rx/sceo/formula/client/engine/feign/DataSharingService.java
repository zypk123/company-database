package com.huntkey.rx.sceo.formula.client.engine.feign;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.sceo.formula.client.engine.feign.hystrix.DataSharingServiceHystrixImpl;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

/**
 * @author chenfei on 2017/5/15.
 */
@Component
@FeignClient(value = "formula-provider", fallback = DataSharingServiceHystrixImpl.class)
public interface DataSharingService {

    /**
     * findWordListById
     * @param id
     * @return
     */
    @RequestMapping(value = "/dataGraper/findWordListById", method = RequestMethod.POST)
    Result findWordListById(@RequestParam(required = false, defaultValue = "", value = "id") String id);

    /**
     * 获取类的基本信息
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/dataGraper/findClassById/{id}", method = RequestMethod.GET)
    Result getClassById(@PathVariable("id") String id);
}
