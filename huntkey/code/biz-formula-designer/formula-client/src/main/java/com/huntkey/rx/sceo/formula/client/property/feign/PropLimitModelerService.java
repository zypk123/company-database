package com.huntkey.rx.sceo.formula.client.property.feign;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.sceo.formula.client.property.feign.hystrix.PropLimitModelerHystrixImpl;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author nidongx on 2017/7/10.
 */
@FeignClient(value = "modeler-client", fallback = PropLimitModelerHystrixImpl.class)
public interface PropLimitModelerService {

    /**
     * getValueLimitById
     * @param propertyId
     * @return
     */
    @RequestMapping(value = "/v1/properties/getValueLimitById/{propertyId}", method = RequestMethod.GET)
    Result getValueLimitById(@RequestParam(required = false, value = "propertyId", defaultValue = "") String propertyId);

    /**
     * updateValueLimit
     * @param id
     * @param edmpValueLimit
     * @return
     */
    @RequestMapping(value = "/v1/properties/updateValueLimit", method = RequestMethod.PUT)
    Result updateValueLimit(@RequestParam(required = false, value = "id", defaultValue = "") String id,
                            @RequestParam(required = false, value = "edmpValueLimit", defaultValue = "") String edmpValueLimit);

    /**
     * queryBaseInfoOfProp
     * @param propertyId
     * @return
     */
    @RequestMapping(value = "/v1/properties/{propertyId}", method = RequestMethod.GET)
    Result queryBaseInfoOfProp(@RequestParam(required = false, value = "propertyId", defaultValue = "") String propertyId);
}
