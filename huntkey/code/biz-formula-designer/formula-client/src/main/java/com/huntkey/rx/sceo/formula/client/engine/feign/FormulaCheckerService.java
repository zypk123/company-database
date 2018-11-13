package com.huntkey.rx.sceo.formula.client.engine.feign;


import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.sceo.formula.client.engine.feign.hystrix.FormulaCheckerHystrixImpl;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author chenfei on 2017/5/15.
 */
@FeignClient(value = "formula-provider", url = "${providerURL}", fallback = FormulaCheckerHystrixImpl.class)
public interface FormulaCheckerService {

    /**
     * checkFormula
     * @param formulaStr
     * @return
     */
    @RequestMapping(value = "/formulaChecker/checkFormula", method = RequestMethod.POST)
    Result checkFormula(@RequestParam(required = false, defaultValue = "",value = "formulaStr")String formulaStr);
}
