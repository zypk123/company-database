package com.huntkey.rx.purchase.provider.client.fallback;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.purchase.provider.client.OrmClient;
import org.springframework.stereotype.Component;

/**
 * @author zhangyu
 */
@Component
public class OrmClientFallback implements OrmClient {

    @Override
    public Result add(String data) {
        return getResult();
    }

    @Override
    public Result delete(String data) {
        return getResult();
    }

    @Override
    public Result update(String data) {
        return getResult();
    }

    @Override
    public Result find(String data) {
        return getResult();
    }

    @Override
    public Result load(String data) {
        return getResult();
    }

    @Override
    public Result query(String sql) {
        return getResult();
    }

    @Override
    public Result getEnumsObj(String parCode, String code) {
        return getResult();
    }

    private Result getResult() {
        Result result = new Result();
        result.setRetCode(Result.RECODE_ERROR);
        result.setErrMsg("调用ORM系统接口服务降级处理！");
        return result;
    }

}
