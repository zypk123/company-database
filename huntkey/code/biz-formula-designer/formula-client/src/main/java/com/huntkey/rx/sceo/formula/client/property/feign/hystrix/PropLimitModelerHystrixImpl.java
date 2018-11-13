package com.huntkey.rx.sceo.formula.client.property.feign.hystrix;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.sceo.formula.client.property.feign.PropLimitModelerService;
import org.springframework.stereotype.Component;

/**
 * @author nidongx on 2017/7/10.
 */
@Component
public class PropLimitModelerHystrixImpl implements PropLimitModelerService{
    @Override
    public Result getValueLimitById(String propertyId) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_ERROR);
        result.setErrMsg("调用服务降级处理逻辑！");
        return result;
    }

    @Override
    public Result updateValueLimit(String propertyId, String valueLimit) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_ERROR);
        result.setErrMsg("调用服务降级处理逻辑！");
        return result;
    }

    @Override
    public Result queryBaseInfoOfProp(String propertyId) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_ERROR);
        result.setErrMsg("调用服务降级处理逻辑！");
        return result;
    }
}
