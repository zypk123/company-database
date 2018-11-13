package com.huntkey.rx.sceo.formula.client.function.functionclassify.feign.hystrix;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.sceo.formula.client.function.functionclassify.feign.FunctionClassifyService;
import com.huntkey.rx.sceo.formula.common.model.FtmFunctionClassify;
import org.springframework.stereotype.Component;

/**
 * 函数分类Hystrix
 *
 * @author zhangyu
 * @create 2017-07-27 11:50
 **/
@Component
public class FunctionClassifyHystrixImpl implements FunctionClassifyService {

    @Override
    public Result createFunctionClassify(FtmFunctionClassify ftmFunctionClassify) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_ERROR);
        result.setErrMsg("调用服务降级处理逻辑！");
        return result;
    }

    @Override
    public Result deleteFunctionClassify(String fnccId) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_ERROR);
        result.setErrMsg("调用服务降级处理逻辑！");
        return result;
    }

    @Override
    public Result updateFunctionClassify(FtmFunctionClassify ftmFunctionClassify) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_ERROR);
        result.setErrMsg("调用服务降级处理逻辑！");
        return result;
    }

    @Override
    public Result getFtmFunctionClassifyList() {
        Result result = new Result();
        result.setRetCode(Result.RECODE_ERROR);
        result.setErrMsg("调用服务降级处理逻辑！");
        return result;
    }

    @Override
    public Result selectByFnccId(String fnccId) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_ERROR);
        result.setErrMsg("调用服务降级处理逻辑！");
        return result;
    }

    @Override
    public Result getClassifyNameList() {
        Result result = new Result();
        result.setRetCode(Result.RECODE_ERROR);
        result.setErrMsg("调用服务降级处理逻辑！");
        return result;
    }
}
