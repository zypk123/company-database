package com.huntkey.rx.sceo.formula.client.engine.feign;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.sceo.formula.client.engine.feign.hystrix.FormulaEngineHystrixImpl;
import com.huntkey.rx.sceo.formula.common.model.PPIFormula;
import com.huntkey.rx.sceo.formula.common.model.TfdFormula;
import com.huntkey.rx.sceo.formula.common.model.vo.FormulaVo;
import com.huntkey.rx.sceo.formula.common.model.vo.ShowFormulaVo;
import com.huntkey.rx.sceo.formula.common.model.vo.SimplePPIParasVo;
import com.huntkey.rx.sceo.formula.common.model.vo.SimpleParasVo;
import com.huntkey.rx.sceo.formula.common.params.FormulaParamPack;
import com.huntkey.rx.sceo.formula.common.params.VariantBatchParam;
import com.huntkey.rx.sceo.formula.common.params.VariantParamPack;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author zhangyu
 * @create 2017-06-14 17:13
 **/
@FeignClient(value = "formula-provider", url = "${providerURL}", fallback = FormulaEngineHystrixImpl.class)
public interface FormulaEngineService {

    /**
     * saveFormula
     * @param formula
     * @return
     */
    @RequestMapping(value = "/formula/saveFormula", method = RequestMethod.POST)
    Result saveFormula(@RequestBody TfdFormula formula);

    /**
     * updateFormula
     * @param formula
     * @return
     */
    @RequestMapping(value = "/formula/updateFormula", method = RequestMethod.POST)
    Result updateFormula(@RequestBody TfdFormula formula);

    /**
     * calc
     * @param formulaId
     * @return
     */
    @RequestMapping(value = "/formula/calc/{formulaId}", method = RequestMethod.GET)
    Result calc(@PathVariable(value = "formulaId") String formulaId);

    /**
     * variantCalc
     * @param variantId
     * @param paramPack
     * @return
     */
    @RequestMapping(value = "/formula/variantCalc/{variantId}", method = RequestMethod.POST)
    Result variantCalc(@PathVariable(value = "variantId") String variantId, @RequestBody VariantParamPack paramPack);

    /**
     * variantCalcBatch
     * @param batchParam
     * @return
     */
    @RequestMapping(value = "/formula/variantCalcBatch", method = RequestMethod.POST)
    Result variantCalcBatch(@RequestBody VariantBatchParam batchParam);

    /**
     * formulaPreCalc
     * @param formulaContent
     * @param params
     * @return
     */
    @RequestMapping(value = "/formula/formulaPreCalc", method = RequestMethod.POST)
    Result formulaPreCalc(
            @RequestParam(value = "formulaContent") String formulaContent,
            @RequestParam Map<String, String> params);

    /**
     * validate
     * @param paramPack
     * @return
     */
    @RequestMapping(value = "/formula/validate", method = RequestMethod.POST)
    Result validate(@RequestBody FormulaParamPack paramPack);

    /**
     * getFormulaElement
     * @param varid
     * @param userid
     * @param classId
     * @return
     */
    @RequestMapping(value = "/formula/getFormulaElement/{varid}/{userid}", method = RequestMethod.GET)
    Result getFormulaElement(@PathVariable("varid") String varid, @PathVariable("userid") String userid,@RequestParam(value = "classId") String classId);

    /**
     * getFormulaFunction
     * @param userid
     * @return
     */
    @RequestMapping(value = "/formula/getFormulaFunction/{userid}", method = RequestMethod.GET)
    Result getFormulaFunction( @PathVariable("userid") String userid);

    /**
     * initFormula
     * @param params
     * @return
     */
    @RequestMapping(value = "/formula/initFormula", method = RequestMethod.POST)
    Result initFormula(@RequestBody SimpleParasVo params);

    /**
     * initPPIFormula
     * @param params
     * @return
     */
    @RequestMapping(value = "/formula/initPPIFormula", method = RequestMethod.POST)
    Result initPPIFormula(@RequestBody SimplePPIParasVo params);

    /**
     * savePPIFormula
     * @param formula
     * @return
     */
    @RequestMapping(value = "/formula/savePPIFormula", method = RequestMethod.POST)
    Result savePPIFormula(@RequestBody PPIFormula formula);

    /**
     * updatePPIFormula
     * @param formula
     * @return
     */
    @RequestMapping(value = "/formula/updatePPIFormula", method = RequestMethod.POST)
    Result updatePPIFormula(@RequestBody PPIFormula formula);

    /**
     * calcPPI
     * @param formulaVo
     * @return
     */
    @RequestMapping(value = "/formula/calcPPI", method = RequestMethod.POST)
    Result calcPPI(@RequestBody FormulaVo formulaVo);


    /**
     * calcPPI
     * @param showFormulaVo
     * @return
     */
    @RequestMapping(value = "/formula/calcShow", method = RequestMethod.POST)
    Result calcShow(@RequestBody ShowFormulaVo showFormulaVo);
}
