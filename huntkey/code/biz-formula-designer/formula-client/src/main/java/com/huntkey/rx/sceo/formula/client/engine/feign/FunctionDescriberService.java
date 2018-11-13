package com.huntkey.rx.sceo.formula.client.engine.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.sceo.formula.client.engine.feign.hystrix.FunctionDescriberHystrixImpl;

/**
 * @author chenfei on 2017/5/15.
 */
@FeignClient(value = "formula-provider", url = "${providerURL}", fallback = FunctionDescriberHystrixImpl.class)
public interface FunctionDescriberService {

    /**
     * loadAllFunctions
     * @return
     */
    @RequestMapping(value = "/functionDescriber/loadAllFunctions", method = RequestMethod.GET)
    Result loadAllFunctions();

    /**
     * loadCustomizeFunctions
     * @return
     */
    @RequestMapping(value = "/functionDescriber/loadCustomizeFunctions", method = RequestMethod.GET)
    Result loadCustomizeFunctions();


}
