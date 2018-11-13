package com.huntkey.rx.sceo.formula.provider.engine.feign.hystrix;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.sceo.formula.common.constant.FormulaCode;
import com.huntkey.rx.sceo.formula.provider.engine.feign.DataSharingService;
import org.springframework.stereotype.Component;

/**
 * @author chenfei on 2017/8/1.
 */
@Component
public class DataSharingServiceHystrixImpl implements DataSharingService {

    @Override
    public Result find(String data) {
        Result result = new Result();
        result.setRetCode(FormulaCode.ORM_QUERY_DATA_ERR.getStateCode());
        result.setErrMsg("调用服务降级处理逻辑！");
        return result;
    }

    @Override
    public Result load(String data) {
        Result result = new Result();
        result.setRetCode(FormulaCode.ORM_QUERY_DATA_ERR.getStateCode());
        result.setErrMsg("调用服务降级处理逻辑！");
        return result;
    }
}
