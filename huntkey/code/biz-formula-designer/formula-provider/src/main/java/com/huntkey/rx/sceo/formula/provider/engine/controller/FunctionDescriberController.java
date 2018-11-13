package com.huntkey.rx.sceo.formula.provider.engine.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.sceo.formula.common.function.FunctionDescriber;
import com.huntkey.rx.sceo.formula.provider.engine.service.FunctionDescriberService;

/**
 * @author chenfei on 2017/5/15.
 */
@RestController
@RequestMapping("/functionDescriber")
public class FunctionDescriberController {
    
    private Logger logger = LoggerFactory.getLogger(FunctionDescriberController.class);
    
    @Autowired
    private FunctionDescriberService functionDescriberService;

    @RequestMapping(value = "/loadAllFunctions", method = RequestMethod.GET)
    public  Result loadAllFunctions() {

        Result result = new Result();
        try {
            result.setData(functionDescriberService.loadAllFunctions());
            result.setRetCode(Result.RECODE_SUCCESS);
        } catch (Exception e) {
            String err = "加载函数描述错误！";
            logger.error(err, e);
            throw new RuntimeException(err,e);
        }
        return result;
    }

    @RequestMapping(value = "/loadCustomizeFunctions", method = RequestMethod.GET)
    public Result loadCustomizeFunctions() {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        try{
            List<FunctionDescriber> describers = functionDescriberService.loadCustomizeFunctions();
            result.setData(describers);
        }catch(Exception e){
            String msg = "remote FunctionDescriberController method loadCustomizeFunctions is error";
            logger.error(msg,e);
            result.setErrMsg(msg);
            result.setRetCode(Result.RECODE_ERROR);
            throw new RuntimeException(msg,e);
        }
        return result;
    }


}
