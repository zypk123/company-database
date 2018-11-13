package com.huntkey.rx.sceo.formula.client.variant.controller;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.sceo.formula.client.variant.feign.VariantMgrService;
import com.huntkey.rx.sceo.formula.common.model.TvmVariant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author zhangyu
 * @create 2017-06-14 17:50
 * 变量管理
 **/
@RestController
@RequestMapping("/variantMgr")
public class VariantMgrController {

    private static Logger log = LoggerFactory.getLogger(VariantMgrController.class);

    @Autowired
    private VariantMgrService variantMgrService;

    /**
     * @return com.huntkey.rx.commons.utils.rest.Result
     * @desc 加载系统变量接口
     * @pars [varName]  变量名
     * @date 2017/6/22 0022 下午 3:42 lulx
     **/
    @RequestMapping(value = "/loadSystemVariants", method = RequestMethod.GET)
    public Result loadSystemVariants(@RequestParam(required = false, defaultValue = "") String varName) {
        Result result = null;
        try {
            result = variantMgrService.loadSystemVariants(varName);
        } catch (Exception e) {
            log.error("加载系统变量接口出错", e);
            throw new RuntimeException("加载系统变量接口出错", e);
        }
        return result;
    }

    /**
     * @return com.huntkey.rx.commons.utils.rest.Result
     * @desc 加载公式使用的系统变量接口
     * @pars [formulaId] 公式编号
     * @date 2017/6/22 0022 下午 3:44 lulx
     **/
    @RequestMapping(value = "/loadSystemVariantsWithFormula", method = RequestMethod.GET)
    public Result loadSystemVariantsWithFormula(@RequestParam(required = false, defaultValue = "") String formulaId) {
        Result result = null;
        try {
            result = variantMgrService.loadSystemVariantsWithFormula(formulaId);
        } catch (Exception e) {
            log.error("加载公式使用的系统变量接口出错", e);
            throw new RuntimeException("加载公式使用的系统变量接口出错", e);
        }
        return result;
    }

    /**
     * @return com.huntkey.rx.commons.utils.rest.Result
     * @desc 加载公式使用的局部变量接口
     * @pars [formulaId] 公式编号
     * @date 2017/6/22 0022 下午 3:46 lulx
     **/
    @RequestMapping(value = "/loadLocalVariantsWithFormula", method = RequestMethod.GET)
    public Result loadLocalVariantsWithFormula(@RequestParam(required = false, defaultValue = "") String formulaId) {
        Result result = null;
        try {
            result = variantMgrService.loadLocalVariantsWithFormula(formulaId);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("加载公式使用的局部变量接口出错", e);
            throw new RuntimeException("加载公式使用的局部变量接口出错", e);
        }
        return result;
    }

    /**
     * @return com.huntkey.rx.commons.utils.rest.Result
     * @desc 变量查询接口
     * @pars [varName, state] 变量名 变量状态
     * @date 2017/6/22 0022 下午 3:46 lulx
     **/
    @RequestMapping(value = "/queryVariants", method = RequestMethod.GET)
    public Result queryVariants(@RequestParam(required = false, defaultValue = "") String varName, @RequestParam(required = false, defaultValue = "") String state) {
        Result result = null;
        try {
            result = variantMgrService.queryVariants(varName, state);
        } catch (Exception e) {
            log.error("变量查询接口出错", e);
            throw new RuntimeException("变量查询接口出错", e);
        }
        return result;
    }

    /**
     * @return com.huntkey.rx.commons.utils.rest.Result
     * @desc 变量删除接口
     * @pars [varId] 变量编号
     * @date 2017/6/22 0022 下午 3:48 lulx
     **/
    @RequestMapping(value = "/removeVariant/{varId}", method = RequestMethod.DELETE)
    public Result removeVariant(@PathVariable(value = "varId") String varId) {
        Result result = null;
        try {
            result = variantMgrService.removeVariant(varId);
        } catch (Exception e) {
            log.error("变量删除接口接口出错", e);
            throw new RuntimeException("变量删除接口接口出错", e);
        }
        return result;
    }

    /**
     * @return com.huntkey.rx.commons.utils.rest.Result
     * @desc 保存变量接口
     * @pars [variant] 变量
     * @date 2017/6/22 0022 下午 3:49 lulx
     **/
    @RequestMapping(value = "/saveVariant", method = RequestMethod.POST)
    public Result saveVariant(@RequestBody TvmVariant variant) {
        Result result = null;
        try {
            result = variantMgrService.saveVariant(variant);
        } catch (Exception e) {
            log.error("保存变量接口出错", e);
            throw new RuntimeException("保存变量接口出错", e);
        }
        return result;
    }

    /**
     *@desc 更新变量接口
     *@pars [variant] 变量
     *@date 2017/7/4 0004 下午 4:03 lulx
     *@return com.huntkey.rx.commons.utils.rest.Result
     **/
    @RequestMapping(value = "/updateVariant", method = RequestMethod.PUT)
    public Result updateVariant(@RequestBody TvmVariant variant) {
        Result result = null;
        try {
            result = variantMgrService.updateVariant(variant);
        } catch (Exception e) {
            log.error("更新变量接口出错", e);
            throw new RuntimeException("更新变量接口出错", e);
        }
        return result;
    }



    /**
     * @return com.huntkey.rx.commons.utils.rest.Result
     * @desc 变量禁用接口
     * @pars [varId] 变量编号
     * @date 2017/6/22 0022 下午 3:50 lulx
     **/
    @RequestMapping(value = "/disableVariant/{varId}", method = RequestMethod.GET)
    public Result disableVariant(@PathVariable(value = "varId") String varId) {
        Result result = null;
        try {
            result = variantMgrService.disableVariant(varId);
        } catch (Exception e) {
            log.error("变量禁用接口出错", e);
            throw new RuntimeException("变量禁用接口出错", e);
        }
        return result;
    }

    /**
     * @return com.huntkey.rx.commons.utils.rest.Result
     * @desc 局部变量关联公式保存接口
     * @pars [localVarIdArr, formulaId] 局部变量编号数组 公式编号
     * @date 2017/6/22 0022 下午 3:51 lulx
     **/
    @RequestMapping(value = "/{formulaId}/saveOrUpdateLocalVariantsWithFormula", method = RequestMethod.POST)
    public Result saveOrUpdateLocalVariantsWithFormula(@RequestParam(required = false, defaultValue = "", value = "localVarIdArr") String[] localVarIdArr, @PathVariable(value = "formulaId") String formulaId) {
        Result result = null;
        try {
            result = variantMgrService.saveOrUpdateLocalVariantsWithFormula(localVarIdArr, formulaId);
        } catch (Exception e) {
            log.error("局部变量关联公式保存接口出错", e);
            throw new RuntimeException("局部变量关联公式保存接口出错", e);
        }
        return result;
    }

    /**
     * @return com.huntkey.rx.commons.utils.rest.Result
     * @desc 系统变量关联公式保存接口
     * @pars [systemVarIdArr, formulaId] 系统变量编号数组 公式编号
     * @date 2017/6/22 0022 下午 3:52 lulx
     **/
    @RequestMapping(value = "/{formulaId}/saveOrUpdateSystemVariantsWithFormula", method = RequestMethod.POST)
    public Result saveOrUpdateSystemVariantsWithFormula(@RequestParam(required = false, defaultValue = "", value = "systemVarIdArr") String[] systemVarIdArr, @PathVariable(value = "formulaId") String formulaId) {
        Result result = null;
        try {
            result = variantMgrService.saveOrUpdateSystemVariantsWithFormula(systemVarIdArr, formulaId);
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
     * @date 2017/6/27 0027 上午 9:44 lulx
     **/
    @RequestMapping(value = "/getSystemVariantsById/{vrntId}", method = RequestMethod.GET)
    public Result getSystemVariantsById(@PathVariable(value = "vrntId") String vrntId) {
        Result result = null;
        try {
            result = variantMgrService.getSystemVariantsById(vrntId);
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
        Result result = null;
        try {
            result = variantMgrService.querySystemVariants(varName, varStatus, sortName, sortOrder, pageNum, pageSize);
        } catch (Exception e) {
            log.error("分页查询系统变量接口出错", e);
            throw new RuntimeException("分页查询系统变量接口出错", e);
        }
        return result;
    }

    /**
     *@desc 根据formulaId初始化局部变量
     *@pars [formulaId] 公式id
     *@date 2017/7/6 0006 下午 2:16 lulx
     *@return com.huntkey.rx.commons.utils.rest.Result
     **/
    @RequestMapping(value = "/initLocalVariant/{formulaId}", method = RequestMethod.GET)
    public Result initLocalVariant(@PathVariable("formulaId") String formulaId){
        Result result = null;
        try {
            result = variantMgrService.initLocalVariant(formulaId);
        } catch (Exception e) {
            log.error("根据formulaId初始化局部变量接口出错", e);
            throw new RuntimeException("根据formulaId初始化局部变量接口出错", e);
        }
        return result;
    }

    /**
     *@desc 检查系统变量是否使用中
     *@pars [sysVarId] 系统变量编号
     *@date 2017/7/7 0007 上午 10:00 lulx
     *@return com.huntkey.rx.commons.utils.rest.Result
     **/
    @RequestMapping(value = "/checkSysVarInUse/{sysVarId}", method = RequestMethod.GET)
    public Result checkSysVarInUse(@PathVariable("sysVarId") String sysVarId){
        Result result = null;
        try {
            result = variantMgrService.checkSysVarIsEnabled(sysVarId);
        } catch (Exception e) {
            String errMsg = "检查系统变量是否使用中接口出错！";
            log.error(errMsg, e);
            throw new RuntimeException(errMsg, e);
        }
        return result;
    }

    /**
     *@desc 初始化系统变量接口
     *@pars []
     *@date 2017/7/12 0012 下午 2:29 lulx
     *@return com.huntkey.rx.commons.utils.rest.Result
     **/
    @RequestMapping(value = "/initSystVariant", method = RequestMethod.GET)
    public Result initSystVariant(){
        Result result = null;
        try {
            result = variantMgrService.initSystVariant();
        } catch (Exception e) {
            log.error("初始化系统变量接口出错", e);
            throw new RuntimeException("初始化系统变量接口出错", e);
        }
        return result;
    }
}
