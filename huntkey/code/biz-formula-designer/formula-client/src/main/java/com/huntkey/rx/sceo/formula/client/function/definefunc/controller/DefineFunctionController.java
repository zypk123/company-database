package com.huntkey.rx.sceo.formula.client.function.definefunc.controller;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.sceo.formula.client.function.definefunc.feign.DefineFunctionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * 自定义函数Controller
 *
 * @author nidongx
 * @create 2017-07-26
 **/
@RestController
@RequestMapping("/defineFunction")
public class DefineFunctionController {

    private Logger logger = LoggerFactory.getLogger(DefineFunctionController.class);

    @Autowired
    DefineFunctionService defineFunctionService;

    /**
     * 查询自定义函数列表
     *
     * @param fundFunCatagoryId,fundName,isEnable,pageNum,pageSize
     * @return boolean
     */
    @RequestMapping(value = "/queryDefineFunction", method = RequestMethod.GET)
    public Result queryDefineFunction(@RequestParam(value = "fundFunCatagoryId", required = false) String fundFunCatagoryId,
                                      @RequestParam(value = "fundFunName", required = false) String fundFunName,
                                      @RequestParam(value = "fundState", required = false) String fundState,
                                      @RequestParam(required = false, value = "sortName") String sortName,
                                      @RequestParam(required = false, value = "sortOrder") String sortOrder,
                                      @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                      @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {

        Result result = new Result();
        result.setData(Result.RECODE_SUCCESS);

        logger.info("fundFunCatagoryId: {}, fundFunName: {}, fundState: {}", fundFunCatagoryId, fundFunName, fundState);
        try {
            return defineFunctionService.queryDefineFunction(fundFunCatagoryId, fundFunName, fundState, sortName, sortOrder, pageNum, pageSize);
        } catch (Exception e) {
            String errMsg = "Some error occured when execute queryDefineFunction method --> " + e.getMessage();
            logger.error("Error msg: {}, error stack: {}", errMsg, e.toString());
            result.setRetCode(Result.RECODE_ERROR);
            result.setErrMsg(errMsg);
        }

        return result;
    }

    @RequestMapping(value = "/createCustomizeFunction", method = RequestMethod.POST)
    public Result createCustomizeFunction(@RequestPart("file") MultipartFile file,
                                          @RequestParam("fundFunName") String fundFunName,
                                          @RequestParam("fundState") String fundState,
                                          @RequestParam("fundFunDesc") String fundFunDesc,
                                          @RequestParam("fundModifyRemarks") String fundModifyRemarks,
                                          @RequestParam("fundFunClassifyId") String fundFunClassifyId,
                                          @RequestParam("fundFunClassifyCode") String fundFunClassifyCode) throws Exception {

        Result result = new Result();
        result.setData(Result.RECODE_SUCCESS);

        try {
            return defineFunctionService.createCustomizeFunction(file, fundFunName, fundState, fundFunDesc, fundModifyRemarks, fundFunClassifyId, fundFunClassifyCode);
        } catch (Exception e) {
            String errMsg = "Some error occured when execute createCustomizeFunction method -->" + e.getMessage();
            logger.error("Error msg: {}, error stack: {}", errMsg, e.toString());
            result.setRetCode(Result.RECODE_ERROR);
            result.setErrMsg(errMsg);
        }

        return result;
    }

    /**
     * 根据ID查找自定义函数编译数据
     *
     * @param funcId
     * @return TfmFunctionCompiled
     */
    @RequestMapping(value = "/getCompileFunByCompileId/{funcId}", method = RequestMethod.GET)
    public Result getCompileFunByCompileId(@PathVariable("funcId") String funcId) {

        Result result = new Result();
        result.setData(Result.RECODE_SUCCESS);

        try {
            return defineFunctionService.getCompileFunByCompileId(funcId);
        } catch (Exception e) {
            String errMsg = "Some error occured when execute getCompileFunByCompileId method --> " + e.getMessage();
            logger.error("Error msg: {}, error stack: {}", errMsg, e.toString());
            result.setRetCode(Result.RECODE_ERROR);
            result.setErrMsg(errMsg);
        }

        return result;
    }

    /**
     * 根据ID查找自定义函数定义数据
     *
     * @param funcId
     * @return TfmFunctionCompiled
     */
    @RequestMapping(value = "/getDefineFunByDefineId/{funcId}", method = RequestMethod.GET)
    public Result getDefineFunByDefineId(@PathVariable("funcId") String funcId) {

        Result result = new Result();
        result.setData(Result.RECODE_SUCCESS);

        try {
            return defineFunctionService.getDefineFunByDefineId(funcId);
        } catch (Exception e) {
            String errMsg = "Some error occured when execute getDefineFunByDefineId method --> " + e.getMessage();
            logger.error("Error msg: {}, error stack: {}", errMsg, e.toString());
            result.setRetCode(Result.RECODE_ERROR);
            result.setErrMsg(errMsg);
        }

        return result;
    }

    /**
     * 修改自定义函数
     *
     * @param file
     * @return TfmFunctionCompiled
     */
    @RequestMapping(value = "/updateCustomizeFunction", method = RequestMethod.POST)
    public Result updateCustomizeFunction(@RequestPart("file") MultipartFile file,
                                          @RequestParam("fundId") String fundId,
                                          @RequestParam("fundFunName") String fundFunName,
                                          @RequestParam("fundState") String fundState,
                                          @RequestParam("fundFunDesc") String fundFunDesc,
                                          @RequestParam("fundFunClassifyId") String fundFunClassifyId,
                                          @RequestParam("fundFunClassifyCode") String fundFunClassifyCode) throws Exception {

        Result result = new Result();
        result.setData(Result.RECODE_SUCCESS);

        try {
            return defineFunctionService.updateCustomizeFunction(file, fundId, fundFunName, fundState, fundFunDesc, fundFunClassifyId, fundFunClassifyCode);
        } catch (Exception e) {
            String errMsg = "Some error occured when execute updateCustomizeFunction method --> " + e.getMessage();
            logger.error("Error msg: {}, error stack: {}", errMsg, e.toString());
            result.setRetCode(Result.RECODE_ERROR);
            result.setErrMsg(errMsg);
        }

        return result;
    }

    /**
     * 删除自定义函数
     *
     * @param funcId
     * @return TfmFunctionCompiled
     */
    @RequestMapping(value = "/deleteCustomizeFunction/{funcId}", method = RequestMethod.DELETE)
    public Result deleteCustomizeFunction(@PathVariable("funcId") String funcId) {

        Result result = new Result();
        result.setData(Result.RECODE_SUCCESS);

        try {
            return defineFunctionService.deleteCustomizeFunction(funcId);
        } catch (Exception e) {
            String errMsg = "Some error occured when execute deleteCustomizeFunction method --> " + e.getMessage();
            logger.error("Error msg: {}, error stack: {}", errMsg, e.toString());
            result.setRetCode(Result.RECODE_ERROR);
            result.setErrMsg(errMsg);
        }

        return result;
    }

    /**
     * 根据函数分类ID查找自定义函数
     *
     * @param classifyId
     * @return
     */
    @RequestMapping(value = "/getFunctionDefinitionByClassifyId/{classifyId}", method = RequestMethod.GET)
    public Result getFunctionDefinitionByClassifyId(@PathVariable("classifyId") String classifyId) {

        Result result = new Result();
        result.setData(Result.RECODE_SUCCESS);

        try {
            return defineFunctionService.getFunctionDefinitionByClassifyId(classifyId);
        } catch (Exception e) {
            String errMsg = "Some error occured when execute getFunctionDefinitionByClassifyId method --> " + e.getMessage();
            logger.error("Error msg: {}, error stack: {}", errMsg, e.toString());
            result.setRetCode(Result.RECODE_ERROR);
            result.setErrMsg(errMsg);
        }

        return result;
    }

    @RequestMapping(value = "/getFunctionDescriberById/{funcId}", method = RequestMethod.GET)
    public Result getFunctionDescriberById(@PathVariable("funcId") String funcId) {

        Result result = new Result();
        result.setData(Result.RECODE_SUCCESS);

        try {
            return defineFunctionService.getFunctionDescriberById(funcId);
        } catch (Exception e) {
            String errMsg = "Some error occured when execute getFunctionDescriberById method --> " + e.getMessage();
            logger.error("Error msg: {}, error stack: {}", errMsg, e.toString());
            result.setRetCode(Result.RECODE_ERROR);
            result.setErrMsg(errMsg);
        }

        return result;
    }

}
