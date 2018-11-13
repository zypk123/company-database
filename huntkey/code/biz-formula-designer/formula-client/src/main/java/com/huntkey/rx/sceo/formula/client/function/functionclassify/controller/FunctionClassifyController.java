package com.huntkey.rx.sceo.formula.client.function.functionclassify.controller;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.sceo.formula.client.function.functionclassify.feign.FunctionClassifyService;
import com.huntkey.rx.sceo.formula.common.model.FtmFunctionClassify;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 函数分类Controller
 *
 * @author zhangyu
 * @create 2017-07-27 11:50
 **/
@RestController
@RequestMapping("/functionClassify")
public class FunctionClassifyController {

    private static Logger log = LoggerFactory.getLogger(FunctionClassifyController.class);

    @Autowired
    FunctionClassifyService functionClassifyService;

    /**
     * 新建函数分类
     *
     * @param ftmFunctionClassify
     * @return
     */
    @RequestMapping(value = "/createFunctionClassify", method = RequestMethod.POST)
    public Result createFunctionClassify(@RequestBody FtmFunctionClassify ftmFunctionClassify) {
        Result result = null;
        try {
            result = functionClassifyService.createFunctionClassify(ftmFunctionClassify);
        } catch (Exception e) {
            log.error("新建函数分类接口出现错误:", e);
            throw new RuntimeException("新建函数分类接口出现错误:", e);
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
        Result result = null;
        try {
            result = functionClassifyService.deleteFunctionClassify(fnccId);
        } catch (Exception e) {
            log.error("删除函数分类接口出现错误:", e);
            throw new RuntimeException("删除函数分类接口出现错误:", e);
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
        Result result = null;
        try {
            result = functionClassifyService.updateFunctionClassify(ftmFunctionClassify);
        } catch (Exception e) {
            log.error("修改函数分类出现错误:", e);
            throw new RuntimeException("修改函数分类接口出现错误:", e);
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
        Result result = null;
        try {
            result = functionClassifyService.getFtmFunctionClassifyList();
        } catch (Exception e) {
            log.error("获取函数分类列表出现错误:", e);
            throw new RuntimeException("获取函数分类列表接口出现错误:", e);
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
    public Result selectByFnccId(@PathVariable("fnccId") String fnccId) {
        Result result = null;
        try {
            result = functionClassifyService.selectByFnccId(fnccId);
        } catch (Exception e) {
            log.error("根据ID获取函数分类出现错误:", e);
            throw new RuntimeException("根据ID获取函数分类接口出现错误:", e);
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
            result = functionClassifyService.getClassifyNameList();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("call functionClassify getClassifyNameList service fail", e);
            throw new RuntimeException("获取有效状态的函数分类的名称接口发生错误!", e);
        }
        return result;
    }

}
