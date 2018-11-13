package com.huntkey.rx.modeler.client.feign;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.modeler.client.feign.hystrix.EdmConvoluteHystrix;
import com.huntkey.rx.modeler.common.model.EdmConvolute;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;


/**
 * Created by linziy on 2017/4/17.
 */
@FeignClient(value = "modeler-provider", fallback = EdmConvoluteHystrix.class)
public interface EdmConvoluteService {
    @RequestMapping( value = "/convolutes",method = RequestMethod.POST)
    public Result add(@RequestBody EdmConvolute edmConvolute);

    @RequestMapping(value = "/convolutes/{id}", method = RequestMethod.DELETE)
    public Result delete(@PathVariable("id") String id) ;

    @RequestMapping( value = "/convolutes",method = RequestMethod.PUT)
    public Result update(@RequestBody EdmConvolute edmConvolute);

    //分页查询
    @RequestMapping( value = "/convolutes",method = RequestMethod.GET)
    public Result select(@RequestParam(required = false, value = "edcoEdmpId")String edcoEdmpId,
                        @RequestParam(required = false, value = "edcoUpdateType")Byte edcoUpdateType,
                        @RequestParam(required = false, value = "edcoUpdateTimeliness")String edcoUpdateTimeliness,
                        @RequestParam(required = false, value = "edcoUpdateTime")String edcoUpdateTime,
                        @RequestParam(required = false, value = "edcoConvoluteFormula")String edcoConvoluteFormula,
                        @RequestParam(required = false, defaultValue ="1", value = "pageNum")int pageNum,
                         @RequestParam(required = false,defaultValue = "10",value = "pageSize") int pageSize);

    //根据公式类型查询模型相关信息
    @RequestMapping(value = "/convolutes/properties", method = RequestMethod.GET)
    public Result queryResultWithModeler(@RequestParam(required = false, value = "edcoConvoluteFormula") String edcoConvoluteFormula,
                                         @RequestParam(required = false, value = "edmpName") String edmpName,
                                         @RequestParam(required = false, value = "edmcName") String edmcName,
                                         @RequestParam(required = false, value = "edmdVer") String edmdVer
    );

    /**
     * 根据属性ID取属性的扩展属性中的卷积公式
     * @param edmpId
     * @return
     */
    @RequestMapping(value = "/convolutes/formula/{edmpId}", method = RequestMethod.GET)
    public Result selectConvoluteFormulaByEdmpId(@PathVariable("edmpId") String edmpId);
}
