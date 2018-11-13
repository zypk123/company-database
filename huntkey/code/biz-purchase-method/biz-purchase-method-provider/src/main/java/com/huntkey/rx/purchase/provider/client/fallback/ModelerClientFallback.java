package com.huntkey.rx.purchase.provider.client.fallback;

import com.alibaba.fastjson.JSONObject;
import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.purchase.provider.client.ModelerClient;
import org.springframework.stereotype.Component;

/**
 * @author zhangyu
 */
@Component
public class ModelerClientFallback implements ModelerClient {

    @Override
    public Result getNumbers(JSONObject object) {
        return getResult();
    }

    private Result getResult() {
        Result result = new Result();
        result.setRetCode(Result.RECODE_ERROR);
        result.setErrMsg("调用Modeler系统接口服务降级处理！");
        return result;
    }
}

