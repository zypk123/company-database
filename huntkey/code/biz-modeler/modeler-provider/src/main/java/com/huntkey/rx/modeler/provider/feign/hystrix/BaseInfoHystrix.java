package com.huntkey.rx.modeler.provider.feign.hystrix;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.modeler.provider.feign.BaseInfoClient;
import com.huntkey.rx.modeler.provider.feign.FormulaClient;
import com.huntkey.rx.sceo.formula.common.params.VariantBatchParam;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by licj on 2017/7/20.
 */
@Component
public class BaseInfoHystrix implements BaseInfoClient {


    @Override
    public Result selectByInfoCodes(List<String> infoCodes) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_ERROR);
        result.setErrMsg("基础信息服务降级！");
        return result;
    }

    @Override
    public Result selectWordlists(String wordName, int pageNum, int pageSize) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_ERROR);
        result.setErrMsg("基础信息服务降级！");
        return result;
    }
}
