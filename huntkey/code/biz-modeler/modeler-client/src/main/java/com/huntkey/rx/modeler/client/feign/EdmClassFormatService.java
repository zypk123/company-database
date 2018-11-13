package com.huntkey.rx.modeler.client.feign;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.modeler.client.feign.hystrix.EdmClassFormatHystrix;
import com.huntkey.rx.modeler.common.model.EdmClassFormat;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "modeler-provider", fallback = EdmClassFormatHystrix.class)
public interface EdmClassFormatService {

    @RequestMapping(value = "/classFormats", method = RequestMethod.GET)
    Result queryList(@RequestParam(value = "edmcId") String edmfEdmcId);

    @RequestMapping(value = "/classFormats/insertBatch", method = RequestMethod.POST)
    Result insertBatch(@RequestBody List<EdmClassFormat> edmClassFormatList);

    @RequestMapping(value = "/classFormats/deleteBatch/{ids}", method = RequestMethod.PUT)
    Result deleteBatch(@PathVariable("ids") String[] ids);

    @RequestMapping(value = "/classFormats/getCharacterAndFormat", method = RequestMethod.GET)
    Result getCharacterAndFormat(@RequestParam(value = "classId") String classId);

    @RequestMapping(value = "/classFormats/getAppearAndEnumObject", method = RequestMethod.GET)
    Result getAppearAndEnumObject(@RequestParam(value = "classId") String classId,@RequestParam(required = false,value = "wordName")String wordName,@RequestParam(required = false,value = "pageNum",defaultValue = "1") int pageNum,
                                  @RequestParam(required = false,value = "pageSize",defaultValue = "15") int pageSize);

    @RequestMapping(value = "/classFormats/selectWordlists", method = RequestMethod.GET)
    Result selectWordlists(@RequestParam(value = "wordName",required = false)String wordName,
                           @RequestParam(value = "pageNum",required = false, defaultValue = "1")int pageNum,@RequestParam(value = "pageSize",required = false, defaultValue = "15")int pageSize);
}
