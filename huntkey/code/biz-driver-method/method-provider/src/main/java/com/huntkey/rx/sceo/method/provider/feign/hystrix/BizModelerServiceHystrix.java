package com.huntkey.rx.sceo.method.provider.feign.hystrix;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.sceo.method.provider.feign.BizModelerService;
import org.springframework.stereotype.Component;

/**
 * Created by lulx on 2017/10/14 0014 上午 10:39
 */
@Component
public class BizModelerServiceHystrix implements BizModelerService {
    @Override
    public Result getMethodInfo(String className, String methodName) {
        return getHystrixResult();
    }

    private Result getHystrixResult() {
        Result result = new Result();
        result.setRetCode(Result.RECODE_ERROR);
        result.setErrMsg("调用服务降级处理逻辑！");
        return result;
    }
}
