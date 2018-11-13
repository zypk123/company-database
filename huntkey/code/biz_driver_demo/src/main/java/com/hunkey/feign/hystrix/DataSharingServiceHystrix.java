package com.hunkey.feign.hystrix;

import com.hunkey.feign.DataSharingService;
import com.huntkey.rx.commons.utils.rest.Result;
import org.springframework.stereotype.Component;

/**
 * Created by chenfei on 2017/8/1.
 */
@Component
public class DataSharingServiceHystrix implements DataSharingService {

    @Override
    public Result find(String data) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_ERROR);
        result.setErrMsg("调用服务降级处理逻辑！");
        return result;
    }

    @Override
    public Result load(String data) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_ERROR);
        result.setErrMsg("调用服务降级处理逻辑！");
        return result;
    }
}
