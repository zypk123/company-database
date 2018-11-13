package com.hunkey.feign.hystrix;

import com.hunkey.entity.ParamsVo;
import com.hunkey.feign.DriverMethodService;
import com.huntkey.rx.commons.utils.rest.Result;
import org.springframework.stereotype.Component;

/**
 * Created by lulx on 2017/10/18 0018 下午 4:04
 */
@Component
public class DriverMethodServiceHystrix implements DriverMethodService{
    @Override
    public Result exec(ParamsVo params) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_ERROR);
        result.setErrMsg("调用服务降级处理逻辑！");
        return result;
    }
}
