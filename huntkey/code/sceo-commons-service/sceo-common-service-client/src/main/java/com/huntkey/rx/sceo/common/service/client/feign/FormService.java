package com.huntkey.rx.sceo.common.service.client.feign;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.sceo.common.service.client.feign.hystrix.FormServiceHystrix;
import com.huntkey.rx.sceo.common.service.common.model.Form;
import com.huntkey.rx.sceo.common.service.common.model.Form.FormType;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @Description:自定义表单
 * @author: Wangning
 * @date: 2017年5月3日18:26:11
 */
@FeignClient(value = "commonService-provider", fallback = FormServiceHystrix.class)
public interface FormService {

    /**
     * 查询数据库表单设计器所需要的表
     *
     * @param db 数据库名称
     * @return
     */
    @RequestMapping(value = "/form/table/{db}", method = RequestMethod.GET)
    Result getFormTables(@PathVariable("db") String db);

    @RequestMapping(value = "/form/table/{db}/{table}", method = RequestMethod.GET)
    Result getFormTableColumn(@PathVariable("db") String db, @PathVariable("table") String table);

    @RequestMapping(value = "/form/list/{type}", method = RequestMethod.GET)
    Result listByType(@PathVariable("type") FormType type);

    @RequestMapping(value = "/form/{id}", method = RequestMethod.GET)
    Result get(@PathVariable("id") String id);

    @RequestMapping(value = "/form/save", method = RequestMethod.POST)
    Result save(@RequestBody Form form);

    @RequestMapping(value = "/form/formDataSubmit", method = RequestMethod.POST)
    Result formDataSubmit(@RequestParam("formId") String formId, @RequestParam("data") String data);

}
