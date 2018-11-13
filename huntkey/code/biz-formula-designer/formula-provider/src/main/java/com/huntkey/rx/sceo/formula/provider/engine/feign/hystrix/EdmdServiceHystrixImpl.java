package com.huntkey.rx.sceo.formula.provider.engine.feign.hystrix;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.sceo.formula.common.constant.FormulaCode;
import com.huntkey.rx.sceo.formula.provider.engine.feign.EdmdService;
import org.springframework.stereotype.Component;

/**
 * @author chenfei on 2017/8/16 0016.
 */
@Component
public class EdmdServiceHystrixImpl implements EdmdService {

    @Override
    public Result getEdmDataType(String edmdVer, String name) {
        Result result = new Result();
        result.setRetCode(FormulaCode.EDM_QUERY_DATA_TYPE_ERR.getStateCode());
        result.setErrMsg("调用服务降级处理逻辑！");
        return result;
    }
    @Override
    public  Result getProperty(String id){
        Result result = new Result();
        result.setRetCode(FormulaCode.EDM_QUERY_PROPERTY_ERR.getStateCode());
        result.setErrMsg("调用服务降级处理逻辑！");
        return result;
    }
}
