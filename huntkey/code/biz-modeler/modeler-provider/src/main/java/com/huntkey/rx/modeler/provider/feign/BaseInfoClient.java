package com.huntkey.rx.modeler.provider.feign;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.modeler.provider.feign.hystrix.BaseInfoHystrix;
import com.huntkey.rx.modeler.provider.feign.hystrix.OrmHystrix;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by licj on 2017/7/20.
 */
@FeignClient(value = "information-provider",fallback = BaseInfoHystrix.class)
public interface BaseInfoClient {

    @RequestMapping(value = "/wordList/selectByInfoCodes",method = RequestMethod.GET)
    Result selectByInfoCodes(@RequestParam(value = "infoCodes",required = false)List<String> infoCodes);

    @RequestMapping(value = "/wordList/selectWordlists",method = RequestMethod.GET)
    Result selectWordlists(@RequestParam(value = "wordName",required = false)String wordName,
                           @RequestParam(value = "pageNum",required = false, defaultValue = "1")int pageNum,@RequestParam(value = "pageSize",required = false, defaultValue = "15")int pageSize);

}
