package com.huntkey.rx.sceo.common.service.client.feign;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.sceo.common.service.client.feign.hystrix.DictServiceHystrix;
import com.huntkey.rx.sceo.common.service.common.model.Dict;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @Description:描述
 * @author: DongHuan
 * @date: 2017年4月20日 下午4:52:14
 */
@FeignClient(value = "commonService-provider", fallback = DictServiceHystrix.class)
public interface DictService {

    @RequestMapping(value = "/dict", method = RequestMethod.GET)
    Result selAllDicts();

    @RequestMapping(value = "/dict/childs/{pid}", method = RequestMethod.GET)
    Result selDictsByParent(@PathVariable("pid") String pid);

    @RequestMapping(value = "/dict/{id}", method = RequestMethod.GET)
    Result selDictsById(@PathVariable("id") String id);

    @RequestMapping(value = "/dict", method = RequestMethod.POST)
    Result save(@RequestBody Dict dict);

    @RequestMapping(value = "/dict", method = RequestMethod.PUT)
    Result update(@RequestBody Dict dict);

    @RequestMapping(value = "/dict/{id}", method = RequestMethod.DELETE)
    Result delete(@PathVariable("id") String id);

    @RequestMapping(value = "/dict/childNode/{pid}", method = RequestMethod.GET)
    Result haveChildNode(@PathVariable("pid") String pid);

    @RequestMapping(value = "/dict/parent/{id}", method = RequestMethod.GET)
    Result getAllParent(@PathVariable("id") String id);

    @RequestMapping(value = "dict/parent_sort/{id}", method = RequestMethod.GET)
    Result getParentBySort(@PathVariable("id") String id);

    @RequestMapping(value = "/dict/code/{code}", method = RequestMethod.GET)
    Result getDictsByCode(@PathVariable("code") String code);

    @RequestMapping(value = "/dict/code", method = RequestMethod.GET)
    Result getDictsByCodes(@RequestParam(value = "codes[]") String[] codes);

    @RequestMapping(value = "/dict/codeVal", method = RequestMethod.GET)
    Result getDictByCodeVal(@RequestParam(value = "code") String code, @RequestParam(value = "value") String value);
}
