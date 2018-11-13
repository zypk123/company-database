package com.huntkey.rx.sceo.formula.engine.expression;

import com.huntkey.rx.sceo.formula.engine.DataProvider;
import com.huntkey.rx.sceo.formula.engine.ExprValue;
import com.huntkey.rx.sceo.formula.engine.exception.FormulaException;

/**
 * 一元表达式
 * @author chenfei on 2017/5/15.
 */
public class UnaryExpression extends Expression {

    /**
     * 子表达式
     */
    protected Expression expr = null;

    /**
     * 一元表达式构造器
     *
     * @param operator 操作符
     * @param expr     子表达式
     */
    public UnaryExpression(Operator operator, Expression expr) {
        super(operator);
        this.expr = expr;
    }

    /**
     * 通过DataProvider来计算表达式值
     *
     * @param provider
     * @return
     */
    @Override
    public ExprValue getValue(DataProvider provider) throws FormulaException {
        switch (operator) {
            case OP_NEGATIVE: {
                ExprValue value = expr.getValue(provider);
                switch (value.getDataType()) {
                    case Long:
                        return new ExprValue(-value.getLong());
                    case Double:
                        return new ExprValue(-value.getDouble());
                    default:
                        throw new FormulaException("Can not get a negative value of " + value.getDataType());
                }
            }
            case OP_POSITIVE:
                return expr.getValue(provider);
            case OP_NOT: {
                ExprValue value = expr.getValue(provider);
                switch (value.getDataType()) {
                    case Boolean:
                        return new ExprValue(!value.getBoolean());
                    default:
                        throw new FormulaException("Can not get a negative value of " + value.getDataType());
                }
            }
            default:
                throw new FormulaException("Unsupported operator:" + operator.toString());
        }
    }

    @Override
    public String toString() {
        switch (operator) {
            case OP_NEGATIVE:
                return "-(" + expr.toString() + ")";
            case OP_POSITIVE:
                return "+(" + expr.toString() + ")";
            case OP_NOT:
                return "!(" + expr.toString() + ")";
            default:
                return "";
        }
    }

    @Override
    public String getOperatorPrototype() {
        switch (operator) {
            case OP_NEGATIVE:
                return "-";
            case OP_POSITIVE:
                return "+";
            case OP_NOT:
                return "!";
            default:
                return "";
        }
    }
}

