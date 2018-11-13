package com.huntkey.rx.modeler.client.feign;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.modeler.client.feign.hystrix.EdmCodeHystrix;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by liangh on 2017/4/16.
 */
@FeignClient(value = "modeler-provider", fallback = EdmCodeHystrix.class)
public interface EdmCodeService {

    @RequestMapping(value = "/codes/{codeType}", method = RequestMethod.GET)
    Result queryEdmCodeListByCodeType(@PathVariable("codeType") String codeType);

    @RequestMapping(value = "/codes/industries", method = RequestMethod.GET)
    Result queryIndustries();

    @RequestMapping(value = "/codes/dataType/{codeValue}", method = RequestMethod.GET)
    Result getDataTypeByPropertyValue(@PathVariable("codeValue") String codeValue);

    @RequestMapping(value = "/codes/code", method = RequestMethod.GET)
    Result getDictsByCodes(@RequestParam(value="codeTypes") String[] codeTypes);

    @RequestMapping(value="/codes/tree",method = RequestMethod.GET)
    Result getTreeData(@RequestParam(value = "codeType") String codeType);

}
