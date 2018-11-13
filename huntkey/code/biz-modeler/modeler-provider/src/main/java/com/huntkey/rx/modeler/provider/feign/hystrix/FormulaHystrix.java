package com.huntkey.rx.modeler.provider.feign.hystrix;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.modeler.provider.feign.FormulaClient;
import com.huntkey.rx.modeler.provider.feign.OrmClient;
import com.huntkey.rx.sceo.formula.common.params.VariantBatchParam;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * Created by licj on 2017/7/20.
 */
@Component
public class FormulaHystrix implements FormulaClient {

    @Override
    public Result queryFormulasAndPropertyLimits(List<String> prprIds, List<String> formulaIds) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_ERROR);
        result.setErrMsg("公式调用服务降级处理逻辑！");
        return result;
    }

    @Override
    public Result selectByPrimaryKey(String prplId) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_ERROR);
        result.setErrMsg("公式调用服务降级处理逻辑！");
        return result;
    }

    @Override
    public Result getPrplConditionDescByPrplIdArr(List<String> prplIdArr1, List<String> prplIdArr2) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_ERROR);
        result.setErrMsg("公式调用服务降级处理逻辑！");
        return result;
    }

    @Override
    public Result variantCalcBatch(VariantBatchParam variantBatchParam) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_ERROR);
        result.setErrMsg("公式调用服务降级处理逻辑！");
        return result;
    }

    @Override
    public Result delRelTrigger(String prplId) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_ERROR);
        result.setErrMsg("公式调用服务降级处理逻辑！");
        return result;
    }

    @Override
    public Result removeCondition(String cndtId) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_ERROR);
        result.setErrMsg("公式调用服务降级处理逻辑！");
        return result;
    }

}
