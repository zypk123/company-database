package com.huntkey.rx.modeler.client.feign.hystrix;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.modeler.client.feign.EdmConvoluteService;
import com.huntkey.rx.modeler.common.model.EdmConvolute;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

/**
 * Created by linziy on 2017/4/18.
 */
@Component
public class EdmConvoluteHystrix implements EdmConvoluteService {
    //增
    @Override
    public Result add(@RequestBody EdmConvolute edmConvolute) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_ERROR);
        result.setErrMsg("调用服务降级处理逻辑！");
        return result;
    }
    //删
    @Override
    public Result delete(@PathVariable("id") String id)
    {
        Result result = new Result();
        result.setRetCode(Result.RECODE_ERROR);
        result.setErrMsg("调用服务降级处理逻辑！");
        return result;
    }
    //改
    @Override
    public Result update(@RequestBody EdmConvolute edmConvolute)
    {
        Result result = new Result();
        result.setRetCode(Result.RECODE_ERROR);
        result.setErrMsg("调用服务降级处理逻辑！");
        return result;
    }

    @Override
    public Result select(@RequestParam(required =false,defaultValue="") String edcoEdmpId,
                        @RequestParam(required = false,defaultValue ="")Byte edcoUpdateType,
                        @RequestParam(required = false,defaultValue ="")String edcoUpdateTimeliness,
                        @RequestParam(required = false,defaultValue = "")String edcoUpdateTime,
                        @RequestParam(required = false,defaultValue = "")String edcoConvoluteFormula,
                        @RequestParam(required =false,defaultValue="1")int pageNum,@RequestParam(required =false,defaultValue = "10") int pageSize){
        Result result = new Result();
        result.setRetCode(Result.RECODE_ERROR);
        result.setErrMsg("调用服务降级处理逻辑！");
        return result;
    }

    @Override
    public Result queryResultWithModeler(@RequestParam(required = false,defaultValue = "",value = "edcoConvoluteFormula") String edcoConvoluteFormula,
                                         @RequestParam(required = false, defaultValue = "", value = "edmpName") String edmpName,
                                         @RequestParam(required = false, defaultValue = "", value = "edmcName") String edmcName,
                                         @RequestParam(required = false, defaultValue = "", value = "edmdVer") String edmdVer
    ){
        Result result = new Result();
        result.setRetCode(Result.RECODE_ERROR);
        result.setErrMsg("调用服务降级处理逻辑！");
        return result;
    }

    @Override
    public Result selectConvoluteFormulaByEdmpId(@PathVariable String edmpId) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_ERROR);
        result.setErrMsg("调用服务降级处理逻辑！");
        return result;
    }
}
