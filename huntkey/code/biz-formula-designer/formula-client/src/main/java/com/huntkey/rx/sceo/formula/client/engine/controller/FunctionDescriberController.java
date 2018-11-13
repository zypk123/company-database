package com.huntkey.rx.sceo.formula.client.engine.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.sceo.formula.client.engine.feign.FunctionDescriberService;

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
        result.setRetCode(Result.RECODE_SUCCESS);
        try{
            result = functionDescriberService.loadAllFunctions();
        }catch(Exception e){
            String msg = "client FunctionDescriberController method loadAllFunctions is error";
            logger.error(msg,e);
            result.setErrMsg(msg);
            result.setRetCode(Result.RECODE_ERROR);
            throw new RuntimeException(msg,e);
        }
        return result;
    }

    @RequestMapping(value = "/loadCustomizeFunctions", method = RequestMethod.GET)
    public Result loadCustomizeFunctions() {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        try{
            result = functionDescriberService.loadCustomizeFunctions();
        }catch(Exception e){
            String msg = "client FunctionDescriberController loadCustomizeFunctions method  is error";
            logger.error(msg,e);
            result.setErrMsg(msg);
            result.setRetCode(Result.RECODE_ERROR);
            throw new RuntimeException(msg,e);
        }
        return result;
    }


}
