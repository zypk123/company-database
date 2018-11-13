package com.huntkey.rx.sceo.formula.engine.expression;

/**
 * 操作符枚举类
 * @author chenfei on 2017/5/15.
 */
public enum Operator {
    /**
     * +
     */
    OP_ADD,

    /**
     * -
     */
    OP_SUB,

    /**
     * *
     */
    OP_MUL,

    /**
     * /
     */
    OP_DIV,

    /**
     * %
     */
    OP_MOD,

    /**
     * >
     */
    OP_GREATER,

    /**
     * >=
     */
    OP_GREATER_EQUAL,

    /**
     * =
     */
    OP_EQUAL,

    /**
     * !=
     */
    OP_NOT_EQUAL,

    /**
     * <
     */
    OP_LESS,

    /**
     * <=
     */
    OP_LESS_EQUAL,

    /**
     * &&
     */
    OP_AND,

    /**
     * ||
     */
    OP_OR,

    /**
     * !
     */
    OP_NOT,

    /**
     * extend ,like function
     */
    OP_EXTEND,

    /**
     * Double
     */
    OP_FLOAT_CONSTANT,

    /**
     * Long
     */
    OP_INTEGER_CONSTANT,

    /**
     * Boolean
     */
    OP_BOOLEAN_CONSTANT,

    /**
     * String
     */
    OP_STRING_CONSTANT,

    /**
     * variant
     */
    OP_VARIANT,

    /**
     * negative
     */
    OP_NEGATIVE,

    /**
     * positive
     */
    OP_POSITIVE
}