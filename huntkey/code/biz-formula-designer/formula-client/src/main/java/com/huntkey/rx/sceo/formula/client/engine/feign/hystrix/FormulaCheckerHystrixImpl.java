package com.huntkey.rx.sceo.formula.client.engine.feign.hystrix;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.sceo.formula.client.engine.feign.FormulaCheckerService;
import org.springframework.stereotype.Component;

/**
 * @author chenfei on 2017/5/15.
 */
@Component
public class FormulaCheckerHystrixImpl implements FormulaCheckerService {

    @Override
    public Result checkFormula(String formulaStr) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_ERROR);
        result.setErrMsg("调用服务降级处理逻辑！");
        return result;
    }
}
