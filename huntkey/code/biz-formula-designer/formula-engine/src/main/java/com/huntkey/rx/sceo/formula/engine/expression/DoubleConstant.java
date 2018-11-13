package com.huntkey.rx.sceo.formula.engine.expression;

import com.huntkey.rx.sceo.formula.engine.DataProvider;
import com.huntkey.rx.sceo.formula.engine.ExprValue;
import com.huntkey.rx.sceo.formula.engine.exception.FormulaException;

/**
 * Double常量表达式
 * @author chenfei on 2017/5/15.
 */
public class DoubleConstant extends Constant {

    protected double value;

    /**
     * Double常量表达式构造器
     *
     * @param value
     */
    public DoubleConstant(double value) {
        super(Operator.OP_FLOAT_CONSTANT);
        this.value = value;
    }

    @Override
    public ExprValue getValue(DataProvider provider) throws FormulaException {
        return new ExprValue(value);
    }

    @Override
    public String getOperatorPrototype() {
        return String.valueOf(value);
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}