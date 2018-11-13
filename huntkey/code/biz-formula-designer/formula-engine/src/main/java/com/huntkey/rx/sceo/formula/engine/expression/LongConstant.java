package com.huntkey.rx.sceo.formula.engine.expression;

import com.huntkey.rx.sceo.formula.engine.DataProvider;
import com.huntkey.rx.sceo.formula.engine.ExprValue;
import com.huntkey.rx.sceo.formula.engine.exception.FormulaException;

/**
 * Long常量表达式
 * @author chenfei on 2017/5/15.
 */
public class LongConstant extends Constant {

    protected long value;

    /**
     * Long常量表达式构造器
     *
     * @param value
     */
    public LongConstant(long value) {
        super(Operator.OP_INTEGER_CONSTANT);
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