package com.huntkey.rx.modeler.client.feign.hystrix;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.modeler.client.feign.EdmNumberService;
import com.huntkey.rx.modeler.common.model.EdmNumber;
import org.springframework.stereotype.Component;

@Component
public class EdmNumberHystrix implements EdmNumberService{

    @Override
    public Result update(EdmNumber edmNumber) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_ERROR);
        result.setErrMsg("调用服务降级处理逻辑！");
        return result;
    }
}
