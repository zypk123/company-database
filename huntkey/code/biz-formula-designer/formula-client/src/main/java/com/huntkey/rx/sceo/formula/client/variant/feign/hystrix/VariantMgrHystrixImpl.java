package com.huntkey.rx.sceo.formula.client.variant.feign.hystrix;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.sceo.formula.client.variant.feign.VariantMgrService;
import com.huntkey.rx.sceo.formula.common.model.TvmVariant;
import org.springframework.stereotype.Component;

/**
 * @author zhangyu
 * @create 2017-06-14 17:40
 **/
@Component
public class VariantMgrHystrixImpl implements VariantMgrService {

    @Override
    public Result loadSystemVariants(String varName) {
        return getHystrixResult();
    }

    @Override
    public Result loadSystemVariantsWithFormula(String formulaId) {
        return getHystrixResult();
    }

    @Override
    public Result loadLocalVariantsWithFormula(String formulaId) {
        return getHystrixResult();
    }

    @Override
    public Result queryVariants(String varName,String state) {
        return getHystrixResult();
    }

    @Override
    public Result removeVariant(String varId) {
        return getHystrixResult();
    }

    @Override
    public Result saveVariant(TvmVariant variant) {
        return getHystrixResult();
    }

    @Override
    public Result updateVariant(TvmVariant variant) {
        return getHystrixResult();
    }

    @Override
    public Result disableVariant(String varId) {
        return getHystrixResult();
    }

    @Override
    public Result saveOrUpdateLocalVariantsWithFormula(String[] localVarIdArr,String formulaId) {
        return getHystrixResult();
    }

    @Override
    public Result saveOrUpdateSystemVariantsWithFormula(String[] systemVarIdArr,String formulaId) {
        return getHystrixResult();
    }

    @Override
    public Result getSystemVariantsById(String vrntId) {
        return getHystrixResult();
    }

    @Override
    public Result querySystemVariants(String varName, String varStatus, String sortName, String sortOrder, int pageNum, int pageSize) {
        return getHystrixResult();
    }

    @Override
    public Result initLocalVariant(String formulaId) {
        return getHystrixResult();
    }

    @Override
    public Result checkSysVarIsEnabled(String sysVarId) {
        return getHystrixResult();
    }

    @Override
    public Result initSystVariant() {
        return getHystrixResult();
    }

    private Result getHystrixResult(){
        Result result = new Result();
        result.setRetCode(Result.RECODE_ERROR);
        result.setErrMsg("调用服务降级处理逻辑！");
        return result;
    }
}
