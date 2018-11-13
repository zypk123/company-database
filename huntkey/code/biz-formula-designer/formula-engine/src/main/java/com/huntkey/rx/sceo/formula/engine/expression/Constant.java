package com.huntkey.rx.sceo.formula.engine.expression;

/**
 * 常量表达式
 * @author chenfei on 2017/5/15.
 */
public abstract class Constant extends Expression {

    public Constant(Operator operator) {
        super(operator);
    }

}