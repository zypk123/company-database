package com.huntkey.rx.sceo.formula.engine.exception;


/**
 * 公式引擎异常
 * @author chenfei on 2017/5/15.
 */
public class FormulaException extends RuntimeException {

    public FormulaException(String message) {
        super(message);
    }

    public FormulaException(String message, Throwable cause) {
        super(message, cause);
    }
}
