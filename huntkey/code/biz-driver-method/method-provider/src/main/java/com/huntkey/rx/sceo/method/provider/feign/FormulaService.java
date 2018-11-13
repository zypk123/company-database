package com.huntkey.rx.sceo.method.provider.feign;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.sceo.method.provider.feign.hystrix.BizModelerServiceHystrix;
import com.huntkey.rx.sceo.method.provider.feign.hystrix.FormulaHystrix;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * Created by caojq on 2017/10/17 0014 上午 11:38
 */
@FeignClient(value = "FORMULA-PROVIDER", fallback = FormulaHystrix.class)
public interface FormulaService {

    @RequestMapping(value = "/relatedConditions/relCondConfDataByPro/{prplId}/{dataId}", method = RequestMethod.GET)
    Result analysisFormula(@PathVariable("prplId")String prplId, @PathVariable("dataId")String dataId);

}
