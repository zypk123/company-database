package com.huntkey.rx.sceo.method.provider.feign.hystrix;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.sceo.method.provider.feign.BizModelerService;
import com.huntkey.rx.sceo.method.provider.feign.FormulaService;
import org.springframework.stereotype.Component;

/**
 * Created by caojq on 2017/10/17 0014 上午 11:39
 */
@Component
public class FormulaHystrix implements FormulaService {

    private Result getHystrixResult() {
        Result result = new Result();
        result.setRetCode(Result.RECODE_ERROR);
        result.setErrMsg("调用服务降级处理逻辑！");
        return result;
    }

    @Override
    public Result analysisFormula(String prplId, String dataId) {
        return getHystrixResult();
    }
}
