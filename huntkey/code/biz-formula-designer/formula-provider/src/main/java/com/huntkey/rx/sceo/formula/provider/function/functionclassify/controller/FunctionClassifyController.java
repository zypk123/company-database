package com.huntkey.rx.sceo.formula.provider.function.functionclassify.controller;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.commons.utils.string.StringUtil;
import com.huntkey.rx.sceo.formula.common.model.FtmFunctionClassify;
import com.huntkey.rx.sceo.formula.provider.function.functionclassify.service.FunctionClassifyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 函数分类Controller
 *
 * @author zhangyu
 * @create 2017-07-26 11:50
 **/
@RestController
@RequestMapping("/functionClassify")
public class FunctionClassifyController {

    private static Logger log = LoggerFactory.getLogger(FunctionClassifyController.class);

    @Autowired
    private FunctionClassifyService functionClassifyService;

    /**
     * 新建函数分类
     *
     * @param ftmFunctionClassify
     * @return
     */
    @RequestMapping(value = "/createFunctionClassify", method = RequestMethod.POST)
    public Result createFunctionClassify(@RequestBody FtmFunctionClassify ftmFunctionClassify) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        try {
            log.info("call functionClassify createFunctionClassify service");
            String s1 = functionClassifyService.checkNameUnique(ftmFunctionClassify.getFnccClassifyName());
            if (!StringUtil.isNullOrEmpty(s1)) {
                result.setRetCode(Result.RECODE_VALIDATE_ERROR);
                result.setErrMsg(s1);
                return result;
            }
            String s2 = functionClassifyService.checkCodeUnique(ftmFunctionClassify.getFnccClassifyCode());
            if (!StringUtil.isNullOrEmpty(s2)) {
                result.setRetCode(Result.RECODE_VALIDATE_ERROR);
                result.setErrMsg(s2);
                return result;
            }
            functionClassifyService.createFunctionClassify(ftmFunctionClassify);
            result.setData(ftmFunctionClassify.getFnccId());
        } catch (Exception e) {
            e.printStackTrace();
            log.error("call functionClassify createFunctionClassify service fail", e);
            throw new RuntimeException("新建函数分类接口发生错误!", e);
        }
        return result;
    }

    /**
     * 删除函数分类
     *
     * @param fnccId
     * @return
     */
    @RequestMapping(value = "/deleteFunctionClassify/{fnccId}", method = RequestMethod.DELETE)
    public Result deleteFunctionClassify(@PathVariable("fnccId") String fnccId) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        try {
            log.info("call functionClassify deleteFunctionClassify service");
            int retInt = functionClassifyService.deleteFunctionClassify(fnccId);
            if (retInt == -1) {
                result.setRetCode(Result.RECODE_ERROR);
                result.setErrMsg("该函数分类下有子类，不允许删除！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("call functionClassify deleteFunctionClassify service fail", e);
            throw new RuntimeException("删除函数分类发生错误!", e);
        }
        return result;
    }

    /**
     * 修改函数分类
     *
     * @param ftmFunctionClassify
     * @return
     */
    @RequestMapping(value = "/updateFunctionClassify", method = RequestMethod.PUT)
    public Result updateFunctionClassify(@RequestBody FtmFunctionClassify ftmFunctionClassify) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        try {
            log.info("call functionClassify updateFunctionClassify service");
            functionClassifyService.updateFunctionClassify(ftmFunctionClassify);
            result.setData(ftmFunctionClassify.getFnccId());
        } catch (Exception e) {
            e.printStackTrace();
            log.error("call functionClassify updateFunctionClassify service fail", e);
            throw new RuntimeException("修改函数分类接口发生错误!", e);
        }
        return result;
    }

    /**
     * 获取函数分类列表
     *
     * @return
     */
    @RequestMapping(value = "/getFtmFunctionClassifyList", method = RequestMethod.GET)
    public Result getFtmFunctionClassifyList() {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        try {
            log.info("call functionClassify getFtmFunctionClassifyList service");
            List<FtmFunctionClassify> ftmFunctionClassifyList = functionClassifyService.getFtmFunctionClassifyList();
            result.setData(ftmFunctionClassifyList);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("call functionClassify getFtmFunctionClassifyList service fail", e);
            throw new RuntimeException("获取函数分类列表接口发生错误!", e);
        }
        return result;
    }

    /**
     * 根据ID获取函数分类
     *
     * @param fnccId
     * @return
     */
    @RequestMapping(value = "/selectByFnccId/{fnccId}", method = RequestMethod.GET)
    public Result selectById(@PathVariable String fnccId) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        try {
            log.info("call functionClassify selectById service");
            FtmFunctionClassify functionClassify = functionClassifyService.selectByFnccId(fnccId);
            result.setData(functionClassify);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("call functionClassify selectById service fail", e);
            throw new RuntimeException("根据ID获取函数分类接口发生错误!", e);
        }
        return result;
    }

    /**
     * 获取有效状态的函数分类的名称
     *
     * @return
     */
    @RequestMapping(value = "/getClassifyNameList", method = RequestMethod.GET)
    public Result getClassifyNameList() {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        try {
            log.info("call functionClassify getClassifyNameList service");
            List<FtmFunctionClassify> classifyNameList = functionClassifyService.getClassifyNameList();
            result.setData(classifyNameList);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("call functionClassify getClassifyNameList service fail", e);
            throw new RuntimeException("获取有效状态的函数分类的名称接口发生错误!", e);
        }
        return result;
    }

}
