package com.huntkey.rx.modeler.client.feign;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.modeler.client.feign.hystrix.EdmModelerHystrix;
import com.huntkey.rx.modeler.common.model.EdmModeler;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * Created by licj on 2017/4/16.
 */
@FeignClient(value = "modeler-provider", fallback = EdmModelerHystrix.class)
public interface EdmModelerService {
    @RequestMapping(value = "/modelers/{id}/classes", method = RequestMethod.GET)
    Result selectEdmClassesByMid(@PathVariable("id") String id);

    @RequestMapping(value = "/modelers", method = RequestMethod.GET)
    Result queryList(@RequestParam(required = false, value = "edmdVer") String edmdVer,
                     @RequestParam(required = false, value = "edmdUpdateDesc") String edmdUpdateDesc,
                     @RequestParam(required = false, defaultValue = "1", value = "pageNum") int pageNum,
                     @RequestParam(required = false, defaultValue = "10", value = "pageSize") int pageSize);

    @RequestMapping(value = "/modelers/{id}", method = RequestMethod.GET)
    Result queryObject(@PathVariable("id") String id);

    @RequestMapping(value = "/modelers/version", method = RequestMethod.GET)
    Result queryModelerVer();

    @RequestMapping(value = "/modelers/status", method = RequestMethod.GET)
    Result status();

    @RequestMapping(value="/modelers/edmdVer", method = RequestMethod.GET)
    Result checkData(@RequestParam(value = "edmdVer") String edmdVer);

    @RequestMapping(value="/modelers", method = RequestMethod.POST)
    Result add(@Valid @RequestBody EdmModeler edmModeler);

    @RequestMapping(value="/modelers", method = RequestMethod.PUT)
    Result update(@RequestBody EdmModeler edmModeler);

    @RequestMapping(value = "/modelers/{id}", method = RequestMethod.DELETE)
    Result delete(@PathVariable("id") String id);

    @RequestMapping(value = "/modelers/export",method = RequestMethod.GET)
    Result exportExcel(@RequestParam(value = "id") String id,
                       @RequestParam(value = "response") HttpServletResponse response);

    @RequestMapping(value = "/modelers/versions",method = RequestMethod.GET)
    Result getAllVers();


    @RequestMapping(value = "/modelers/queryClasses",method = RequestMethod.GET)
    Result queryIdByVer();
}
