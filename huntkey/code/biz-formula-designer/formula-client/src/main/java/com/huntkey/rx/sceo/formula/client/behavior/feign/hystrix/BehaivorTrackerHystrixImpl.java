package com.huntkey.rx.sceo.formula.client.behavior.feign.hystrix;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.sceo.formula.client.behavior.feign.BehaivorTrackerService;
import org.springframework.stereotype.Component;

/**
 * @author lulx on 2017/6/14 0014.
 */
@Component
public class BehaivorTrackerHystrixImpl implements BehaivorTrackerService {
    @Override
    public Result loadCommonlyFormulas(String userId) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_ERROR);
        result.setErrMsg("调用服务降级处理逻辑！");
        return result;
    }

    @Override
    public Result updateCommonlyFormulas(String userId,String funcName) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_ERROR);
        result.setErrMsg("调用服务降级处理逻辑！");
        return result;
    }
}
