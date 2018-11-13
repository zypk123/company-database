package com.huntkey.rx.sceo.formula.provider.engine.service.impl;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.sceo.formula.engine.tools.ValidateTool;
import com.huntkey.rx.sceo.formula.provider.engine.service.FormulaCheckerService;
import org.springframework.stereotype.Service;

/**
 * @author lulx on 2017/6/15 0015.
 */
@Service
public class FormulaCheckerServiceImpl implements FormulaCheckerService {
    @Override
    public Result checkFormula(String formulaStr) {
        Result result = ValidateTool.validateByallRegex(formulaStr);
        return result;
    }
}
