package com.huntkey.rx.sceo.formula.client.data.feign.hystrix;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.sceo.formula.client.data.feign.DataGraperService;
import org.springframework.stereotype.Component;

/**
 * @author lulx on 2017/6/14 0014.
 */
@Component
public class DataGraperHystrixImpl implements DataGraperService {

    @Override
    public Result grapData() {
        Result result = new Result();
        result.setRetCode(Result.RECODE_ERROR);
        result.setErrMsg("调用服务降级处理逻辑！");
        return result;
    }
}
