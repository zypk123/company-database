package com.huntkey.rx.purchase.provider.client.fallback;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.purchase.provider.client.InformationClient;
import org.springframework.stereotype.Component;

/**
 * @author zhangyu
 * @create 2018-01-15 16:17
 **/
@Component
public class InformationClientFallback implements InformationClient {

    @Override
    public Result getNumbers(String nbrlCode, String params) {
        return getResult();
    }

    private Result getResult() {
        Result result = new Result();
        result.setRetCode(Result.RECODE_ERROR);
        result.setErrMsg("调用编号生成器接口服务降级处理！");
        return result;
    }
}
