package com.huntkey.rx.sceo.formula.client.engine.feign.hystrix;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.sceo.formula.client.engine.feign.DataSharingService;
import com.huntkey.rx.sceo.formula.common.constant.FormulaCode;
import org.springframework.stereotype.Component;

/**
 * @author chenfei on 2017/5/15.
 */
@Component
public class DataSharingServiceHystrixImpl implements DataSharingService {

    @Override
    public Result findWordListById(String id) {
        Result result = new Result();
        result.setRetCode(FormulaCode.ORM_QUERY_DATA_ERR.getStateCode());
        result.setErrMsg("调用服务降级处理逻辑！");
        return result;
    }

    @Override
    public Result getClassById(String id) {
        Result result = new Result();
        result.setRetCode(FormulaCode.ORM_QUERY_DATA_ERR.getStateCode());
        result.setErrMsg("调用服务降级处理逻辑！");
        return result;
    }
}
