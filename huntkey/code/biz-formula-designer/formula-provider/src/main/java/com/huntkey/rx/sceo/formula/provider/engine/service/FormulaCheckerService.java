package com.huntkey.rx.sceo.formula.provider.engine.service;


import com.huntkey.rx.commons.utils.rest.Result;

/**
 * @author chenfei on 2017/5/15.
 */
public interface FormulaCheckerService {

    /**
     * checkFormula
     * @param formulaStr
     * @return
     */
    Result checkFormula(String formulaStr);
}
