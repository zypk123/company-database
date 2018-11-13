package com.huntkey.rx.modeler.provider.feign.hystrix;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.modeler.provider.feign.OrmClient;
import org.springframework.stereotype.Component;

/**
 * Created by licj on 2017/7/20.
 */
@Component
public class OrmHystrix implements OrmClient {

    @Override
    public Result find(String data) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_ERROR);
        result.setErrMsg("Orm调用服务降级处理逻辑！");
        return result;
    }

    @Override
    public Result load(String data) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_ERROR);
        result.setErrMsg("Orm调用服务降级处理逻辑！");
        return result;
    }

    @Override
    public Result update(String data) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_ERROR);
        result.setErrMsg("Orm调用服务降级处理逻辑！");
        return result;
    }
}
