package com.huntkey.rx.sceo.formula.client.behavior.feign;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.sceo.formula.client.behavior.feign.hystrix.BehaivorTrackerHystrixImpl;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author lulx on 2017/6/14 0014.
 */
@FeignClient(value = "formula-provider", url = "${providerURL}", fallback = BehaivorTrackerHystrixImpl.class)
public interface BehaivorTrackerService {

    /**
     * loadCommonlyFormulas
     * @param userId
     * @return
     */
    @RequestMapping(value = "/behaivorTracker/loadCommonlyFormulas/{userId}", method = RequestMethod.GET)
    Result loadCommonlyFormulas(@PathVariable(value = "userId")String userId);

    /**
     * updateCommonlyFormulas
     * @param userId
     * @param funcName
     * @return
     */
    @RequestMapping(value = "/behaivorTracker/updateCommonlyFormulas/{userId}/{funcName}", method = RequestMethod.POST)
    Result updateCommonlyFormulas(@PathVariable(value = "userId")String userId, @PathVariable(value = "funcName") String funcName);
}
