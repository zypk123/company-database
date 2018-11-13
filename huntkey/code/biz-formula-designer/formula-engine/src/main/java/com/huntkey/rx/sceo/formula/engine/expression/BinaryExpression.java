package com.huntkey.rx.sceo.formula.engine.expression;

import com.huntkey.rx.sceo.formula.engine.exception.FormulaException;

/**
 * 二元表达式
 * @author lulx on 2017/6/15
 */
public abstract class BinaryExpression extends Expression {

    /**
     * 二元表达式之左表达式
     */
    protected Expression left = null;

    /**
     * 二元表达式之右表达式
     */
    protected Expression right = null;

    /**
     * 二元表达式构造函数
     *
     * @param operator 操作符
     * @param left     左表达式
     * @param right    右表达式
     */
    public BinaryExpression(Operator operator, Expression left, Expression right) {
        super(operator);
        this.left = left;
        this.right = right;
    }

    /**
     * 获取左表达式
     *
     * @return
     */
    public Expression getLeft() {
        return left;
    }

    /**
     * 获取右表达式
     *
     * @return
     */
    public Expression getRight() {
        return right;
    }

    @Override
    public String toString() {
        return "(" + left.toString() + getOperatorPrototype() + right.toString() + ")";
    }

    /**
     * 创建子表达式
     *
     * @param operator 操作符
     * @param left     左表达式
     * @param right    右表达式
     * @return
     * @throws FormulaException
     */
    public static Expression createChild(Operator operator, Expression left, Expression right) throws FormulaException {

        switch (operator) {
            case OP_ADD:
            case OP_SUB:
            case OP_MUL:
            case OP_DIV:
            case OP_MOD:
                return new ArithmeticExpression(operator, left, right);
            case OP_GREATER:
            case OP_GREATER_EQUAL:
            case OP_EQUAL:
            case OP_NOT_EQUAL:
            case OP_LESS:
            case OP_LESS_EQUAL:
            case OP_OR:
            case OP_AND:
            case OP_NOT:
                return new LogicalExpression(operator, left, right);
            default:
                throw new FormulaException("Unsupport operator:" + operator);
        }
    }

}