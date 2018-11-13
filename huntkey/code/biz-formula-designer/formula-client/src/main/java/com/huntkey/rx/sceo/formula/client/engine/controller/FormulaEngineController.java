package com.huntkey.rx.sceo.formula.client.engine.controller;

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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author zhangyu
 * @create 2017-06-14 17:27
 **/
@RestController
@RequestMapping("/formula")
public class FormulaEngineController {

    private static Logger log = LoggerFactory.getLogger(FormulaEngineController.class);

    @Autowired
    private FormulaEngineService formulaEngineService;

    @RequestMapping(value = "/saveFormula", method = RequestMethod.POST)
    public Result saveFormula(@RequestBody TfdFormula formula) {
        try {
            return formulaEngineService.saveFormula(formula);
        } catch (Exception e) {
            String errMsg = "保存公式错误！";
            log.error(errMsg,e);
            throw new RuntimeException(errMsg, e);
        }
    }

    @RequestMapping(value = "/updateFormula", method = RequestMethod.POST)
    public Result updateFormula(@RequestBody TfdFormula formula) {
        try {
            return formulaEngineService.updateFormula(formula);
        } catch (Exception e) {
            String errMsg = "更新公式错误！";
            log.error(errMsg,e);
            throw new RuntimeException(errMsg, e);
        }
    }

    @RequestMapping(value = "/calc/{formulaId}", method = RequestMethod.GET)
    public Result calc(@PathVariable String formulaId) {

        try {
            return formulaEngineService.calc(formulaId);
        } catch (Exception e) {
            String errMsg = "计算公式错误！";
            log.error(errMsg,e);
            throw new RuntimeException(errMsg, e);
        }
    }




    @RequestMapping(value = "/variantCalc/{variantId}", method = RequestMethod.POST)
    public Result variantCalc(@PathVariable String variantId, @RequestBody VariantParamPack paramPack) {

        try {
            return formulaEngineService.variantCalc(variantId, paramPack);
        } catch (Exception e) {
            String errMsg = "计算变量错误！";
            log.error(errMsg,e);
            throw new RuntimeException(errMsg, e);
        }
    }

    @RequestMapping(value = "/variantCalcBatch", method = RequestMethod.POST)
    public Result variantCalcBatch(@RequestBody VariantBatchParam batchParam) {
        try {
            return formulaEngineService.variantCalcBatch(batchParam);
        } catch (Exception e) {
            String errMsg = "计算变量错误！";
            log.error(errMsg,e);
            throw new RuntimeException(errMsg, e);
        }
    }




    @RequestMapping(value = "/formulaPreCalc", method = RequestMethod.POST)
    public Result formulaPreCalc(
            @RequestParam(value = "formulaContent") String formulaContent,
            @RequestParam Map<String, String> params) {

        try {
            return formulaEngineService.formulaPreCalc(formulaContent, params);
        } catch (Exception e) {
            String errMsg = "预计算公式错误！";
            log.error(errMsg,e);
            throw new RuntimeException(errMsg, e);
        }
    }

    @RequestMapping(value = "/validate", method = RequestMethod.POST)
    public Result validate(@RequestBody FormulaParamPack paramPack) {
        try {

            System.out.println("paramPack: " + paramPack);

            return formulaEngineService.validate(paramPack);
        } catch (Exception e) {
            String errMsg = "预计算公式错误！";
            log.error(errMsg,e);
            throw new RuntimeException(errMsg, e);
        }
    }

    /**
     *@desc 根据变量编号获取相关类、局部变量、计算公式、系统变量、常用函数、函数列表
     *@pars [varid]  变量编号 userid 用户id
     *@date 2017/7/1 0001 下午 2:53 lulx
     *@return com.huntkey.rx.commons.utils.rest.Result
     **/
    @RequestMapping(value = "/getFormulaElement/{varid}/{userid}", method = RequestMethod.GET)
    public Result getFormulaElement(@PathVariable("varid") String varid, @PathVariable("userid") String userid){
        try {
            Result result = formulaEngineService.getFormulaElement(varid, userid, "");
            return result;
        } catch (Exception e) {
            log.error("根据变量编号获取编辑公式要素接口错误:", e);
            throw new RuntimeException("根据变量编号获取编辑公式要素接口错误:", e);
        }
    }


    /**
     *@desc 获取常用函数、函数列表
     *@pars  userid 用户id
     *@date 2017/7/1 0001 下午 2:53 lulx
     *@return com.huntkey.rx.commons.utils.rest.Result
     **/
    @RequestMapping(value = "/getFormulaFunction/{userid}", method = RequestMethod.GET)
    public Result getFormulaFunction(@PathVariable("userid") String userid){
        try {
            Result result = formulaEngineService.getFormulaFunction(userid);
            return result;
        } catch (Exception e) {
            log.error("根据变量编号获取编辑公式要素接口错误:", e);
            throw new RuntimeException("根据变量编号获取编辑公式要素接口错误:", e);
        }
    }

    /**
     *@desc 初始化公式变量id,公式id
     *@pars [propId, varId] 属性ID  变量ID（可为空）
     *@date 2017/7/6 0006 上午 9:47 lulx
     *@return com.huntkey.rx.commons.utils.rest.Result
     **/
    @RequestMapping(value = "/initFormula", method = RequestMethod.POST)
    public Result initFormula(@RequestBody SimpleParasVo params){
        Result result = null;
        try{
            result = formulaEngineService.initFormula(params);
        } catch (Exception e) {
            log.error("初始化公式变量id,公式id接口错误:", e);
            throw new RuntimeException("初始化公式变量id,公式id接口错误:", e);
        }
        return result;
    }

    /**
     *@desc 初始化公式变量id,公式id
     *@pars [propId, varId] 属性ID  变量ID（可为空）
     *@date 2017/7/6 0006 上午 9:47 lulx
     *@return com.huntkey.rx.commons.utils.rest.Result
     **/
    @RequestMapping(value = "/initPPIFormula", method = RequestMethod.POST)
    public Result initPPIFormula(@RequestBody SimplePPIParasVo params){
        Result result = null;
        try{
            result = formulaEngineService.initPPIFormula(params);
        } catch (Exception e) {
            log.error("初始化PPI公式变量id,公式id接口错误:", e);
            throw new RuntimeException("初始化PPI公式变量id,公式id接口错误:", e);
        }
        return result;
    }

    @RequestMapping(value = "/savePPIFormula", method = RequestMethod.POST)
    public Result saveFormula(@RequestBody PPIFormula formula) {
        try {
            return formulaEngineService.savePPIFormula(formula);
        } catch (Exception e) {
            String errMsg = "保存公式错误！";
            log.error(errMsg,e);
            throw new RuntimeException(errMsg, e);
        }
    }

    @RequestMapping(value = "/updatePPIFormula", method = RequestMethod.POST)
    public Result updateFormula(@RequestBody PPIFormula formula) {
        try {
            return formulaEngineService.updatePPIFormula(formula);
        } catch (Exception e) {
            String errMsg = "更新公式错误！";
            log.error(errMsg,e);
            throw new RuntimeException(errMsg, e);
        }
    }


    @RequestMapping(value = "/calcPPI", method = RequestMethod.POST)
    public Result calcPPI(@RequestBody FormulaVo formulaVo) {

        try {
            return formulaEngineService.calcPPI(formulaVo);
        } catch (Exception e) {
            String errMsg = "计算PPI公式错误！";
            log.error(errMsg,e);
            throw new RuntimeException(errMsg, e);
        }
    }

    /**
     *呈现对象公式计算
     */
    @RequestMapping(value = "/calcShow",method = RequestMethod.POST)
    public Result calcShow(@RequestBody ShowFormulaVo showFormulaVo){

        try {
            return formulaEngineService.calcShow(showFormulaVo);
        } catch (Exception e) {
            String errMsg = "计算对象呈现公式错误！";
            log.error(errMsg,e);
            throw new RuntimeException(errMsg, e);
        }
        }

}
