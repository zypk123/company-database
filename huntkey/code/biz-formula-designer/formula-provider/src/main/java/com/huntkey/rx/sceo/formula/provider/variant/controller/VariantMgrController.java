package com.huntkey.rx.sceo.formula.provider.variant.controller;

import com.huntkey.rx.commons.utils.rest.Pagination;
import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.commons.utils.string.StringUtil;
import com.huntkey.rx.sceo.formula.common.model.TfdFormula;
import com.huntkey.rx.sceo.formula.common.model.TvmVariant;
import com.huntkey.rx.sceo.formula.provider.engine.service.FormulaEngineService;
import com.huntkey.rx.sceo.formula.provider.variant.service.VariantMgrService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhangyu
 * @create 2017-06-14 17:50
 **/
@RestController
@RequestMapping("/variantMgr")
public class VariantMgrController {

    private static Logger log = LoggerFactory.getLogger(VariantMgrController.class);

    @Autowired
    private VariantMgrService variantMgrService;

    @Autowired
    private FormulaEngineService formulaEngineService;

    @RequestMapping(value = "/loadSystemVariants", method = RequestMethod.GET)
    public Result loadSystemVariants(@RequestParam(required = false, defaultValue = "", value = "varName") String varName) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        try {
            List<TvmVariant> list = variantMgrService.loadSystemVariants(varName);
            result.setData(list);
        } catch (Exception e) {
            log.error("加载系统变量接口出错", e);
            throw new RuntimeException("加载系统变量接口", e);
        }
        return result;
    }

    @RequestMapping(value = "/loadSystemVariantsWithFormula", method = RequestMethod.GET)
    public Result loadSystemVariantsWithFormula(@RequestParam(required = false, defaultValue = "") String formulaId) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        try {
            List<TvmVariant> list = variantMgrService.loadSystemVariantsWithFormula(formulaId);
            result.setData(list);
        } catch (Exception e) {
            log.error("加载公式使用的系统变量接口出错", e);
            throw new RuntimeException("加载公式使用的系统变量接口出错", e);
        }
        return result;
    }

    @RequestMapping(value = "/loadLocalVariantsWithFormula", method = RequestMethod.GET)
    public Result loadLocalVariantsWithFormula(@RequestParam(required = false, defaultValue = "") String formulaId) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        try {
            List<TvmVariant> list = variantMgrService.loadLocalVariantsWithFormula(formulaId);
            result.setData(list);
        } catch (Exception e) {
            log.error("加载公式使用的局部变量接口出错", e);
            throw new RuntimeException("加载公式使用的局部变量接口出错", e);
        }
        return result;
    }

    @RequestMapping(value = "/queryVariants", method = RequestMethod.GET)
    public Result queryVariants(@RequestParam(required = false, defaultValue = "") String varName, @RequestParam(required = false, defaultValue = "") String state) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        try {
            List<TvmVariant> list = variantMgrService.queryVariants(varName, state);
            result.setData(list);
        } catch (Exception e) {
            log.error("变量查询接口出错", e);
            throw new RuntimeException("变量查询接口出错", e);
        }
        return result;
    }

    @RequestMapping(value = "/removeVariant/{varId}", method = RequestMethod.DELETE)
    public Result removeVariant(@PathVariable(value = "varId") String varId) {
        Result result = null;
        try {
            result = variantMgrService.removeVariant(varId);
        } catch (Exception e) {
            log.error("变量删除接口出错", e);
            throw new RuntimeException("变量删除接口出错", e);
        }
        return result;
    }

    @RequestMapping(value = "/saveVariant", method = RequestMethod.POST)
    public Result saveVariant(@RequestBody TvmVariant variant) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        try {
            result.setData(variantMgrService.saveVariant(variant));
        } catch (Exception e) {
            log.error("保存／更新变量接口出错", e);
            throw new RuntimeException("保存／更新变量接口出错", e);
        }
        return result;
    }

    @RequestMapping(value = "/updateVariant", method = RequestMethod.PUT)
    public Result updateVariant(@RequestBody TvmVariant variant) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        try {
            Result checkResult = variantMgrService.checkVarVal(variant);
            if(!Result.RECODE_SUCCESS.equals(checkResult.getRetCode())){
                return checkResult;
            }
            int retInt = variantMgrService.updateVariant(variant);
            result.setRetCode(retInt);
        } catch (Exception e) {
            log.error("保存／更新变量接口出错", e);
            throw new RuntimeException("保存／更新变量接口出错", e);
        }
        return result;
    }

    @RequestMapping(value = "/disableVariant/{varId}", method = RequestMethod.GET)
    public Result disableVariant(@PathVariable(value = "varId") String varId) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        try {
            int retInt = variantMgrService.disableVariant(varId);
            result.setRetCode(retInt);
        } catch (Exception e) {
            log.error("变量禁用接口出错", e);
            throw new RuntimeException("变量禁用接口出错", e);
        }
        return result;
    }

    @RequestMapping(value = "/{formulaId}/saveOrUpdateLocalVariantsWithFormula", method = RequestMethod.POST)
    public Result saveOrUpdateLocalVariantsWithFormula(@RequestParam(required = true, defaultValue = "", value = "localVarIdArr") String[] localVarIdArr, @PathVariable(value = "formulaId") String formulaId) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        try {
            if (!StringUtil.isNullOrEmpty(localVarIdArr) && localVarIdArr.length > 0) {
                variantMgrService.saveOrUpdateLocalVariantsWithFormula(localVarIdArr, formulaId);
            } else {
                result.setRetCode(Result.RECODE_ERROR);
                result.setErrMsg("localVarIdArr不能为空");
            }
        } catch (Exception e) {
            log.error("局部变量关联公式保存接口出错", e);
            throw new RuntimeException("局部变量关联公式保存接口出错", e);
        }
        return result;
    }

    @RequestMapping(value = "/{formulaId}/saveOrUpdateSystemVariantsWithFormula", method = RequestMethod.POST)
    public Result saveOrUpdateSystemVariantsWithFormula(@RequestParam(required = true, defaultValue = "", value = "systemVarIdArr") String[] systemVarIdArr, @PathVariable(value = "formulaId") String formulaId) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        try {
            if (!StringUtil.isNullOrEmpty(systemVarIdArr) && systemVarIdArr.length > 0) {
                variantMgrService.saveOrUpdateSystemVariantsWithFormula(systemVarIdArr, formulaId);
            } else {
                result.setRetCode(Result.RECODE_ERROR);
                result.setErrMsg("systemVarIdArr不能为空");
            }
        } catch (Exception e) {
            log.error("系统变量关联公式保存接口出错", e);
            throw new RuntimeException("系统变量关联公式保存接口出错", e);
        }
        return result;
    }

    /**
     * @return com.huntkey.rx.commons.utils.rest.Result
     * @desc 获取系统变量详细信息
     * @pars [vrntId] 系统变量编号
     * @date 2017/6/27 0027 上午 9:45 lulx
     **/
    @RequestMapping(value = "/getSystemVariantsById/{vrntId}", method = RequestMethod.GET)
    public Result getSystemVariantsById(@PathVariable(value = "vrntId") String vrntId) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        try {
            TvmVariant tvmVariant = variantMgrService.getVariantById(vrntId);
            TfdFormula formula = formulaEngineService.getTfdFormulaById(tvmVariant.getVrntFormulaId());
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("tvmVariant",tvmVariant);
            map.put("formula",formula);
            result.setData(map);
        } catch (Exception e) {
            log.error("获取系统变量详细信息接口出错", e);
            throw new RuntimeException("获取系统变量详细信息接口出错", e);
        }
        return result;
    }

    /**
     * @return com.huntkey.rx.commons.utils.rest.Result
     * @desc 分页查询系统变量
     * @pars [varName, varStatus, sortName, sortOrder, pageNum, pageSize]
     * 变量名 变量状态 排序字段 排序状态
     * @date 2017/6/27 0027 上午 10:00 lulx
     **/
    @RequestMapping(value = "/querySystemVariants", method = RequestMethod.GET)
    public Result querySystemVariants(@RequestParam(required = false, value = "varName") String varName,
                                      @RequestParam(required = false, value = "varStatus") String varStatus,
                                      @RequestParam(required = false, value = "sortName") String sortName,
                                      @RequestParam(required = false, value = "sortOrder") String sortOrder,
                                      @RequestParam(required = false, defaultValue = "1", value = "pageNum") int pageNum,
                                      @RequestParam(required = false, defaultValue = "10", value = "pageSize") int pageSize) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        try {
            Pagination<TvmVariant> list = variantMgrService.querySystemVariants(varName, varStatus, sortName, sortOrder, pageNum, pageSize);
            result.setData(list);
        } catch (Exception e) {
            log.error("分页查询系统变量接口出错", e);
            throw new RuntimeException("分页查询系统变量接口出错", e);
        }
        return result;
    }

    /**
     * 在删除局部变量之前校验
     *
     * @param varId 待删除的局部变量的ID
     * @return
     */
    @RequestMapping(value = "/checkDeletelocalVar/{varId}", method = RequestMethod.GET)
    public Result checkDeletelocalVar(@PathVariable("varId") String varId) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        String errMsg = null;
        try {
            errMsg = variantMgrService.existLocalVar(varId);
            if (null != errMsg) {
                result.setRetCode(Result.RECODE_VALIDATE_ERROR);
                result.setErrMsg(errMsg);
                return result;
            }
            errMsg = variantMgrService.existRelatedClass(varId);
            if (null != errMsg) {
                result.setRetCode(Result.RECODE_VALIDATE_ERROR);
                result.setErrMsg(errMsg);
                return result;
            }
        } catch (Exception e) {
            log.error("在删除局部变量之前校验接口发生错误", e);
            throw new RuntimeException("在删除局部变量之前校验接口发生错误", e);
        }
        return result;
    }

    @RequestMapping(value = "/initLocalVariant/{formulaId}", method = RequestMethod.GET)
    public Result initLocalVariant(@PathVariable("formulaId") String formulaId){
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        try {
            result.setData(variantMgrService.initLocalVariant(formulaId));
        } catch (Exception e) {
            log.error("根据formulaId初始化局部变量接口出错", e);
            throw new RuntimeException("根据formulaId初始化局部变量接口出错", e);
        }
        return result;
    }

    @RequestMapping(value = "/checkSysVarInUse/{sysVarId}", method = RequestMethod.GET)
    public Result checkSysVarInUse(@PathVariable("sysVarId") String sysVarId){
        Result result = null;
        try {
            result = variantMgrService.checkSysVarIsEnabled(sysVarId);
        } catch (Exception e) {
            String errMsg = "检查系统变量是否使用中接口出错！!";
            log.error(errMsg, e);
            throw new RuntimeException(errMsg, e);
        }
        return result;
    }

    @RequestMapping(value = "/initSystVariant", method = RequestMethod.GET)
    public Result initSystVariant(){
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        try {
            TvmVariant variant = variantMgrService.initSystVariant();
            result.setData(variant);
        } catch (Exception e) {
            log.error("初始化系统变量接口出错", e);
            throw new RuntimeException("初始化系统变量接口出错", e);
        }
        return result;
    }
}
