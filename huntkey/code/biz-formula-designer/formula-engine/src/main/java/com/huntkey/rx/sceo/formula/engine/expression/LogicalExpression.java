package com.huntkey.rx.sceo.formula.engine.expression;

import com.huntkey.rx.sceo.formula.engine.DataProvider;
import com.huntkey.rx.sceo.formula.engine.ExprValue;
import com.huntkey.rx.sceo.formula.engine.exception.FormulaException;

/**
 * 逻辑表达式
 * @author chenfei on 2017/5/15.
 */
public class LogicalExpression extends BinaryExpression {

    /**
     * 逻辑表达式构造器
     *
     * @param operator 逻辑操作符
     * @param left     左表达式
     * @param right    右表达式
     */
    public LogicalExpression(Operator operator, Expression left, Expression right) {
        super(operator, left, right);
    }

    /**
     * 获取操作符的原始类型
     */
    @Override
    public String getOperatorPrototype() {

        switch (operator) {
            case OP_GREATER:
                return ">";
            case OP_GREATER_EQUAL:
                return ">=";
            case OP_EQUAL:
                return "==";
            case OP_NOT_EQUAL:
                return "!=";
            case OP_LESS:
                return "<";
            case OP_LESS_EQUAL:
                return "<=";
            case OP_OR:
                return "||";
            case OP_AND:
                return "&&";
            default:
                return "";
        }
    }

    /**
     * 通过DataProvider来计算表达式值
     *
     * @param provider
     * @return
     */
    @Override
    public ExprValue getValue(DataProvider provider) throws FormulaException {

        // TODO Auto-generated method stub
        switch (operator) {
            case OP_GREATER:
                return new ExprValue(left.getValue(provider).compareTo(right.getValue(provider)) > 0);
            case OP_GREATER_EQUAL:
                return new ExprValue(left.getValue(provider).compareTo(right.getValue(provider)) >= 0);
            case OP_EQUAL:
                return new ExprValue(left.getValue(provider).compareTo(right.getValue(provider)) == 0);
            case OP_NOT_EQUAL:
                return new ExprValue(left.getValue(provider).compareTo(right.getValue(provider)) != 0);
            case OP_LESS:
                return new ExprValue(left.getValue(provider).compareTo(right.getValue(provider)) < 0);
            case OP_LESS_EQUAL:
                return new ExprValue(left.getValue(provider).compareTo(right.getValue(provider)) <= 0);
            case OP_OR:
                return new ExprValue(left.getValue(provider).getBoolean() || right.getValue(provider).getBoolean());
            case OP_AND:
                return new ExprValue(left.getValue(provider).getBoolean() && right.getValue(provider).getBoolean());
            default:
                throw new FormulaException("Unsupport operator:" + operator);
        }
    }

}