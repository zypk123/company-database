package com.huntkey.rx.sceo.formula.engine.expression;

import com.huntkey.rx.sceo.formula.engine.DataProvider;
import com.huntkey.rx.sceo.formula.engine.ExprValue;
import com.huntkey.rx.sceo.formula.engine.exception.FormulaException;

/**
 * 变量表达式
 * @author chenfei on 2017/5/15.
 */
public class Variant extends Expression {

    /**
     * 变量名
     */
    protected String varName;

    /**
     * 变量上下文
     */
    protected Object varContext = null;

    public Variant(String varName) {
        super(Operator.OP_VARIANT);
        this.varName = varName;
    }

    @Override
    public ExprValue getValue(DataProvider provider) throws FormulaException {

        if (provider == null) {
            throw new FormulaException("Data provider is null,can not get value of " + varName);
        }

        Object value = provider.getValue(varName, null);

        return value != null ? new ExprValue(value) : null;
    }

    @Override
    public String getOperatorPrototype() {
        return varName;
    }

    @Override
    public String toString() {
        return varName;
    }
}