package com.huntkey.rx.sceo.formula.client.engine.feign.hystrix;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.sceo.formula.client.engine.feign.FormulaEngineService;
import com.huntkey.rx.sceo.formula.common.model.PPIFormula;
import com.huntkey.rx.sceo.formula.common.model.TfdFormula;
import com.huntkey.rx.sceo.formula.common.model.vo.FormulaVo;
import com.huntkey.rx.sceo.formula.common.model.vo.ShowFormulaVo;
import com.huntkey.rx.sceo.formula.common.model.vo.SimplePPIParasVo;
import com.huntkey.rx.sceo.formula.common.model.vo.SimpleParasVo;
import com.huntkey.rx.sceo.formula.common.params.FormulaParamPack;
import com.huntkey.rx.sceo.formula.common.params.VariantBatchParam;
import com.huntkey.rx.sceo.formula.common.params.VariantParamPack;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author zhangyu
 * @create 2017-06-14 17:13
 **/
@Component
public class FormulaEngineHystrixImpl implements FormulaEngineService {

    @Override
    public Result formulaPreCalc(String formulaContent, Map<String, String> params) {
        return getCommonDegratedLogic();
    }

    @Override
    public Result validate(FormulaParamPack paramPack) {
        return getCommonDegratedLogic();
    }

    @Override
    public Result getFormulaElement(String varid, String userid,String classId) {
        return getCommonDegratedLogic();
    }

    @Override
    public Result getFormulaFunction(String userid) {
        return getCommonDegratedLogic();
    }

    @Override
    public Result initFormula(SimpleParasVo params) {
        return getCommonDegratedLogic();
    }
    @Override
    public Result initPPIFormula(SimplePPIParasVo params) {
        return getCommonDegratedLogic();
    }

    @Override
    public Result savePPIFormula(PPIFormula formula) {
        return getCommonDegratedLogic();
    }

    @Override
    public Result updatePPIFormula(PPIFormula formula) {
        return getCommonDegratedLogic();
    }




    @Override
    public Result saveFormula(TfdFormula formula) {
        return getCommonDegratedLogic();
    }

    @Override
    public Result updateFormula(TfdFormula formula) {
        return getCommonDegratedLogic();
    }

    @Override
    public Result calc(String formulaId) {
        return getCommonDegratedLogic();
    }



    @Override
    public Result calcPPI(FormulaVo formulaVo) {
        return getCommonDegratedLogic();
    }

    @Override
    public Result calcShow(ShowFormulaVo showFormulaVo) {
        return getCommonDegratedLogic();
    }

    @Override
    public Result variantCalc(String variantId, VariantParamPack paramPack) {
        return getCommonDegratedLogic();
    }
    @Override
    public Result variantCalcBatch(VariantBatchParam batchParam) {
        return getCommonDegratedLogic();
    }

    private Result getCommonDegratedLogic() {
        Result result = new Result();
        result.setRetCode(Result.RECODE_ERROR);
        result.setErrMsg("调用服务降级处理逻辑！");
        return result;
    }

}
