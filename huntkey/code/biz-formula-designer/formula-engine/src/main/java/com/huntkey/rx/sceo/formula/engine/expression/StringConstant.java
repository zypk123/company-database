package com.huntkey.rx.sceo.formula.engine.expression;

import com.huntkey.rx.sceo.formula.engine.DataProvider;
import com.huntkey.rx.sceo.formula.engine.ExprValue;
import com.huntkey.rx.sceo.formula.engine.exception.FormulaException;

/**
 * String常量表达式
 *
 * @author chenfei on 2017/5/15.
 */
public class StringConstant extends Constant {

    protected String value;

    /**
     * String常量表达式构造器
     *
     * @param value
     */
    public StringConstant(String value) {
        super(Operator.OP_STRING_CONSTANT);
        this.value = value;
    }

    @Override
    public ExprValue getValue(DataProvider provider) throws FormulaException {
        return new ExprValue(value);
    }

    @Override
    public String getOperatorPrototype() {
        return value;
    }

    @Override
    public String toString() {
        return "'" + value + "'";
    }
}
