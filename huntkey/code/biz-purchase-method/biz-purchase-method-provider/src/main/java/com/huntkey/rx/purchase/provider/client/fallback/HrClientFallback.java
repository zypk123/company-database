package com.huntkey.rx.purchase.provider.client.fallback;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.purchase.provider.client.HrClient;
import org.springframework.stereotype.Component;

/**
 * @author zhangyu
 */
@Component
public class HrClientFallback implements HrClient {

    @Override
    public Result queryEmployee(String searchName) {
        return getResult();
    }

    @Override
    public Result findEmployeeById(String employeeId, int type) {
        return getResult();
    }

    private Result getResult() {
        Result result = new Result();
        result.setRetCode(Result.RECODE_ERROR);
        result.setErrMsg("调用HR系统接口服务降级处理！");
        return result;
    }
}
