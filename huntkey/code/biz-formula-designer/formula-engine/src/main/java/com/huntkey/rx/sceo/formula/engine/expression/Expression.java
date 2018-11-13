package com.huntkey.rx.sceo.formula.engine.expression;

import com.huntkey.rx.sceo.formula.engine.DataProvider;
import com.huntkey.rx.sceo.formula.engine.ExprValue;
import com.huntkey.rx.sceo.formula.engine.exception.FormulaException;

import java.io.Serializable;

/**
 * 表达式类
 * @author chenfei on 2017/5/15.
 */
public abstract class Expression implements Serializable {

    /**
     * 表达式构造器
     *
     * @param operator
     */
    public Expression(Operator operator) {
        this.operator = operator;
    }

    /**
     * 通过DataProvider来计算表达式值
     *
     * @param provider
     * @return 表达式值
     * @throws FormulaException
     */
    public abstract ExprValue getValue(DataProvider provider) throws FormulaException;

    /**
     * 获取操作符的原始类型
     *
     * @return 操作符原始类型
     */
    public abstract String getOperatorPrototype();

    /**
     * 获取操作符
     *
     * @return operator
     */
    public Operator getOperator() {
        return operator;
    }

    /**
     * 表达式的操作符
     */
    protected Operator operator;

}
