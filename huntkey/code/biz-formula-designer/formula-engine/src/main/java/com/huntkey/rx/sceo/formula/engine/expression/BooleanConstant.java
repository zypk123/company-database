package com.huntkey.rx.sceo.formula.engine.expression;

import com.huntkey.rx.sceo.formula.engine.DataProvider;
import com.huntkey.rx.sceo.formula.engine.ExprValue;
import com.huntkey.rx.sceo.formula.engine.exception.FormulaException;

/**
 * Boolean常量表达式
 * @author lulx on 2017/7/17 0017 上午 9:14
 */
public class BooleanConstant extends Constant {

    protected boolean value;

    /**
     * Boolean常量表达式构造器
     *
     * @param value
     */
    public BooleanConstant(boolean value) {
        super(Operator.OP_BOOLEAN_CONSTANT);
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