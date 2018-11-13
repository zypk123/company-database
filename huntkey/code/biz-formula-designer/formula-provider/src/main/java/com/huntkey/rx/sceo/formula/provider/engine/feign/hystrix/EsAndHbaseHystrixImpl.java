package com.huntkey.rx.sceo.formula.provider.engine.feign.hystrix;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.sceo.formula.common.constant.FormulaCode;
import com.huntkey.rx.sceo.formula.provider.engine.feign.EsAndHbaseService;
import org.springframework.stereotype.Component;

/**
 * @author lulx on 2017/7/4 0004 下午 2:09
 */
@Component
public class EsAndHbaseHystrixImpl implements EsAndHbaseService {

    @Override
    public Result queryFromEsAndHbase(String datas) {
        Result result = new Result();
        result.setRetCode(FormulaCode.ORM_QUERY_DATA_ERR.getStateCode());
        result.setErrMsg("调用服务降级处理逻辑！");
        return result;
    }
}
