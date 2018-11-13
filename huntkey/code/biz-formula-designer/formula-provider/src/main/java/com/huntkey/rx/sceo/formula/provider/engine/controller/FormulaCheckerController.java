package com.huntkey.rx.sceo.formula.provider.engine.controller;


import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.sceo.formula.provider.engine.service.FormulaCheckerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chenfei on 2017/5/15.
 */
@RestController
@RequestMapping("/formulaChecker")
public class FormulaCheckerController {

    private static Logger log = LoggerFactory.getLogger(FormulaCheckerController.class);

    @Autowired
    private FormulaCheckerService formulaCheckerService;

    @RequestMapping(value = "/checkFormula",method = RequestMethod.POST)
    public Result checkFormula(@RequestParam(required = false, defaultValue = "",value = "formulaStr")String formulaStr){
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        try {
            result = formulaCheckerService.checkFormula(formulaStr);
        } catch (Exception e) {
            log.error("检查公式合法性接口出错",e);
            throw new RuntimeException("检查公式合法性接口出错",e);
        }
        return result;
    }






}
