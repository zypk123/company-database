package com.huntkey.rx.sceo.formula.engine.expression;

import com.huntkey.rx.sceo.formula.engine.DataProvider;
import com.huntkey.rx.sceo.formula.engine.ExprValue;
import com.huntkey.rx.sceo.formula.engine.exception.FormulaException;

/**
 * 算数表达式
 * @author chenfei on 2017/8/12
 */
public class ArithmeticExpression extends BinaryExpression {

    /**
     * 算数表达式构造器
     *
     * @param operator 操作符
     * @param left     左表达式
     * @param right    右表达式
     */
    public ArithmeticExpression(Operator operator, Expression left, Expression right) {
        super(operator, left, right);
    }

    @Override
    public ExprValue getValue(DataProvider provider) throws FormulaException {

        ExprValue leftValue = left.getValue(provider);
        ExprValue rightValue = right.getValue(provider);
        if (ExprValue.DataType.ARR.equals(leftValue.getDataType()) && ExprValue.DataType.ARR.equals(rightValue.getDataType())) {

            ExprValue value = new ExprValue();
            Object[] leftArr = (Object[]) leftValue.getValue();
            Object[] rightArr = (Object[]) rightValue.getValue();
            Object[] newArr = new Object[leftArr.length];
            for (int i = 0; i < leftArr.length; i++) {
                if (Operator.OP_ADD.equals(operator)) {
                    value = new ExprValue(leftArr[i]).add(new ExprValue(rightArr[i]));
                } else if (Operator.OP_SUB.equals(operator)) {
                    value = new ExprValue(leftArr[i]).sub(new ExprValue(rightArr[i]));
                } else if (Operator.OP_MUL.equals(operator)) {
                    value = new ExprValue(leftArr[i]).mul(new ExprValue(rightArr[i]));
                } else if (Operator.OP_DIV.equals(operator)) {
                    value = new ExprValue(leftArr[i]).div(new ExprValue(rightArr[i]));
                } else if (Operator.OP_MOD.equals(operator)) {
                    value = new ExprValue(leftArr[i]).mod(new ExprValue(rightArr[i]));
                }
                newArr[i] = Double.valueOf(value.toString());
            }
            return new ExprValue(newArr);
        } else {
            switch (operator) {
                case OP_ADD:
                    return leftValue.add(rightValue);
                case OP_SUB:
                    return leftValue.sub(rightValue);
                case OP_MUL:
                    return leftValue.mul(rightValue);
                case OP_DIV:
                    return leftValue.div(rightValue);
                case OP_MOD:
                    return leftValue.mod(rightValue);
                default:
                    throw new FormulaException("Unsupport operator:" + operator);
            }
        }

    }

    @Override
    public String getOperatorPrototype() {

        switch (operator) {
            case OP_ADD:
                return "+";
            case OP_SUB:
                return "-";
            case OP_MUL:
                return "*";
            case OP_DIV:
                return "/";
            case OP_MOD:
                return "%";
            default:
                throw new FormulaException("Unsupport operator:" + operator);
        }

    }
}

