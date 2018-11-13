package com.huntkey.rx.sceo.formula.provider.audit.feign.edm;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.sceo.formula.provider.audit.feign.EdmInterface;
import com.huntkey.rx.sceo.formula.provider.audit.feign.EdmModelerClientInterface;
import org.springframework.stereotype.Component;

/**
 * @author zhouyou on 2017/10/24.
 */
@Component
public class EdmHystrix implements EdmInterface,EdmModelerClientInterface {
    @Override
    public Result addResources(String data) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_ERROR);
        result.setErrMsg("调用服务降级处理逻辑！");
        return result;
    }

    @Override
    public Result getResources(String data) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_ERROR);
        result.setErrMsg("调用服务降级处理逻辑！");
        return result;
    }

    @Override
    public Result selectGroups(String edmcId, String edmpId, String isSource) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_ERROR);
        result.setErrMsg("调用服务降级处理逻辑！");
        return result;
    }

    @Override
    public Result getProperties(String edmcId) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_ERROR);
        result.setErrMsg("调用服务降级处理逻辑！");
        return result;
    }

    @Override
    public Result getEdmClass(String edmcId) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_ERROR);
        result.setErrMsg("调用服务降级处理逻辑！");
        return result;
    }

    @Override
    public Result getPsClass(String classId) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_ERROR);
        result.setErrMsg("调用服务降级处理逻辑！");
        return result;
    }

    @Override
    public Result getEdmProp(String edmpId) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_ERROR);
        result.setErrMsg("调用服务降级处理逻辑！");
        return result;
    }
}
