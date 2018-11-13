package com.huntkey.rx.sceo.formula.client.engine.controller;


import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.sceo.formula.client.engine.feign.FormulaCheckerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
/**
 *@desc 公式检查类
 *@date 2017/6/22 0022 下午 3:37 lulx
 * @author lulx
 **/
@RestController
@RequestMapping("/formulaChecker")
public class FormulaCheckerController {

    private static Logger log = LoggerFactory.getLogger(FormulaCheckerController.class);

    @Autowired
    private FormulaCheckerService formulaCheckerService;
    /**
     *@desc 检查公式合法性接口
     *@pars [formulaStr] 待校验公式
     *@date 2017/6/22 0022 下午 3:38 lulx
     *@return com.huntkey.rx.commons.utils.rest.Result
     **/
    @RequestMapping(value = "/checkFormula", method = RequestMethod.POST)
    public Result checkFormula(@RequestParam(required = false, defaultValue = "",value = "formulaStr")String formulaStr){
        Result result = null;
        try {
            result = formulaCheckerService.checkFormula(formulaStr);
        } catch (Exception e) {
            log.error("检查公式合法性接口出错",e);
            throw new RuntimeException("检查公式合法性接口出错",e);
        }
        return result;
    }
}
