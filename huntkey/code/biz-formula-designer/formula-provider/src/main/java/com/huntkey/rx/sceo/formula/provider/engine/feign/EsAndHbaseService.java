package com.huntkey.rx.sceo.formula.provider.engine.feign;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.sceo.formula.provider.engine.feign.hystrix.EsAndHbaseHystrixImpl;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author lulx on 2017/7/4 0004 下午 2:08
 */
@FeignClient(value = "modelerCommon-client", fallback = EsAndHbaseHystrixImpl.class)
public interface EsAndHbaseService {

    /**
     * queryFromEsAndHbase
     * @param datas
     * @return
     */
    @RequestMapping(value = "/v1/esAndhbase", method = RequestMethod.GET)
    Result queryFromEsAndHbase(@RequestParam(value = "datas") String datas);
}
