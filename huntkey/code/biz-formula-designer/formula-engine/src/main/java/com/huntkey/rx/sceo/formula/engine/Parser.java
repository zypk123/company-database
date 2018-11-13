package com.huntkey.rx.sceo.formula.engine;

import com.huntkey.rx.sceo.formula.engine.exception.FormulaException;
import com.huntkey.rx.sceo.formula.engine.expression.*;
import com.huntkey.rx.sceo.formula.engine.function.Function;
import com.huntkey.rx.sceo.formula.engine.function.FunctionHandler;

/**
 * 公式解析器
 *
 * Formula-engine 是纯Java实现的公式解析器，具备下列特性：
 * - 支持加,减,乘,除,模等算术操作符
 * - 支持大于,小于,等于等逻辑操作符
 * - 支持com.huntkey.formula.engine.function中所有内置函数
 * - 通过 FunctionHandler 机制支持自定义函数
 * - 通过 DataProvider 机制支持自定义变量
 * @author chenfei on 2017/5/15.
 */
public class Parser {

    /**
     * 当前处理的公式文本
     */
    protected String text;

    /**
     * 当前解析的位置
     */
    protected int current;

    /**
     * 种别码
     */
    private static final char ADD_OR_POSITIVE        = '+';
    private static final char SUBTRACT_OR_NEGATIVE   = '-';
    private static final char MULTIPLY               = '*';
    private static final char DIVIDE                 = '/';
    private static final char MOD                    = '%';
    private static final char EQUAL                  = '=';
    private static final char LESS                   = '<';
    private static final char GREATER                = '>';
    private static final char BIT_AND                = '&';
    private static final char BIT_OR                 = '|';
    private static final char LEFT_BRACKET           = '(';
    private static final char RIGHT_BRACKET          = ')';
    private static final char PARAMETER_SEPERATOR    = ',';
    private static final char NOT                    = '!';
    private static final char STRING                 = '\'';
    private static final char IDENTIFIER             = 257;
    private static final char FINISHED               = 259;
    private static final char EQUAL_LESS             = 260;
    private static final char EQUAL_GREATE           = 261;
    private static final char NOT_EQUAL              = 262;
    private static final char AND                    = 263;
    private static final char OR                     = 264;
    private static final char FLOAT                  = 265;
    private static final char INTEGER                = 266;

    /**
     * 当前解析Token的类型
     */
    protected char type;

    /**
     * 当前解析的ID
     */
    protected String identifier;

    /**
     * 当前是否在字符串中
     */
    protected boolean inString;

    /**
     * 函数对象辅助器
     */
    protected FunctionHandler funcHelper = null;

    /**
     * 设置函数处理器
     *
     * @param handler
     */
    public void setFunctionHandler(FunctionHandler handler) {
        funcHelper = handler;
    }

    /**
     * 判断字符是否为空格
     *
     * @param ch
     * @return
     */
    public boolean isSpace(char ch) {
        return Character.isSpaceChar(ch);
    }

    /**
     * 判断字符是否为字母
     *
     * @param ch
     * @return
     */
    public boolean isAlpha(char ch) {
        return ch >= 'a' && ch <= 'z' || ch >= 'A' && ch <= 'Z';
    }

    /**
     * 判断字符是否为数字
     *
     * @param ch
     * @return
     */
    public boolean isDigital(char ch) {
        return ch >= '0' && ch <= '9';
    }

    /**
     * 判断字符是否为标识符字符
     *
     * @param ch
     * @return
     */
    public boolean isIdentifierChar(char ch) {
        return isAlpha(ch) || isDigital(ch) || ch == '_' || ch == '.';
    }

    /**
     * 判断字符是否为标识符的首字符
     *
     * @param ch
     * @return
     */
    public boolean isIdentifierFirstChar(char ch) {
        return isAlpha(ch) || ch == '_' || ch == '.';
    }

    /**
     * 判断字符是否为小数点
     *
     * @param ch
     * @return
     */
    public boolean isDecimalChar(char ch) {
        return ch == '.';
    }

    /**
     * 公式解析器构造器
     */
    public Parser() {

    }

    /**
     * 公式解析器构造器
     * 
     * @param helper
     */
    public Parser(FunctionHandler helper) {
        funcHelper = helper;
    }

    /**
     * 将公式文本解析为Expression表达式
     *
     * @param text 公式文本
     * @return Expression实例
     * @throws FormulaException
     */
    public Expression parse(String text) throws FormulaException {
        this.text = text;
        current = 0;
        inString = false;
        identifier = "";
        lookAhead();
        //return expressionRLogicalOr(expressionRLogicalAnd(expressionRCompare(expressionR(higherExpression()))));
        Expression step = higherExpression();
        step = expressionR(step);
        step = expressionRCompare(step);
        step = expressionRLogicalAnd(step);
        step = expressionRLogicalOr(step);
        return step;
    }

    /**
     * 高阶表达式解析函数
     *
     * @return
     */
    private Expression higherExpression() {
        return higherExpressionR(signExpression());
    }

    private Expression higherExpressionR(Expression left) {

        Expression result = left;
        if (type == MULTIPLY) {
            match(MULTIPLY);
            Expression higher = signExpression();
            result = higherExpressionR(BinaryExpression.createChild(Operator.OP_MUL, left, higher));
        } else if (type == DIVIDE) {
            match(DIVIDE);
            Expression higher = signExpression();
            result = higherExpressionR(BinaryExpression.createChild(Operator.OP_DIV, left, higher));
        }
        return result;
    }

    /**
     * 符号表达式解析函数
     *
     * @return
     */
    private Expression signExpression() {

        Expression result;
        if (type == ADD_OR_POSITIVE) {
            match(ADD_OR_POSITIVE);
            result = signExpression();
        } else if (type == SUBTRACT_OR_NEGATIVE) {
            match(SUBTRACT_OR_NEGATIVE);
            result = new UnaryExpression(Operator.OP_NEGATIVE, signExpression());
        } else {
            result = factor();
        }

        return result;
    }

    /**
     * 表达式的核心因子函数
     *
     * @return
     */
    private Expression factor() {
        Expression result;
        if (type == FLOAT) {
            match(FLOAT);
            result = new DoubleConstant(Double.valueOf(identifier));
        } else if (type == STRING) {
            match(STRING);
            result = new StringConstant(identifier);
        } else if (type == INTEGER) {
            match(INTEGER);
            result = new LongConstant(Long.valueOf(identifier));
        } else if (type == LEFT_BRACKET) {
            match(LEFT_BRACKET);
            result = expressionRLogicalOr(expressionRLogicalAnd(expressionRCompare(expressionR(higherExpression()))));
            match(RIGHT_BRACKET);
        } else {
            result = langStructure();
        }
        return result;
    }

    /**
     * 语言结构的解析函数
     *
     * @return
     */

    private Expression langStructure() {
        String id = identifier;
        match(IDENTIFIER);
        return langTail(id);
    }

    /**
     * 语言结构尾部的解析函数
     *
     * @param id
     * @return
     */
    private Expression langTail(String id) {
        if ("true".equals(id)) {
            return new BooleanConstant(true);
        }
        if ("false".equals(id)) {
            return new BooleanConstant(false);
        }

        if (type == LEFT_BRACKET) {
            Function result = null;
            if (funcHelper != null) {
                result = (Function) funcHelper.customize(id);
            }
            if (result != null) {
                match(LEFT_BRACKET);

                if (type != RIGHT_BRACKET) {
                    result.addArgument(expressionRLogicalOr(expressionRLogicalAnd(expressionRCompare(expressionR(higherExpression())))));
                }

                for (; ; ) {
                    if (type == RIGHT_BRACKET) {
                        match(RIGHT_BRACKET);
                        break;
                    }
                    match(PARAMETER_SEPERATOR);
                    result.addArgument(expressionRLogicalOr(expressionRLogicalAnd(expressionRCompare(expressionR(higherExpression())))));
                }
                return result;
            }

            return expressionRLogicalOr(expressionRLogicalAnd(expressionRCompare(expressionR(higherExpression()))));
        }

        return new Variant(id);
    }

    /**
     * 表达式右边是逻辑或的解析函数
     *
     * @param left
     * @return
     */
    private Expression expressionRLogicalOr(Expression left) {
        Expression result = left;

        if (type == OR) {
            match(OR);
            Expression higher = expressionRLogicalAnd(expressionRCompare(expressionR(higherExpression())));
            result = expressionRLogicalOr(BinaryExpression.createChild(Operator.OP_OR, left, higher));
        }

        return result;
    }

    /**
     * 表达式右边是逻辑与的解析函数
     *
     * @param left
     * @return
     */
    private Expression expressionRLogicalAnd(Expression left) {
        Expression result = left;

        if (type == AND) {
            match(AND);
            Expression higher = expressionRCompare(expressionR(higherExpression()));
            result = expressionRLogicalAnd(BinaryExpression.createChild(Operator.OP_AND, left, higher));
        }
        return result;
    }

    /**
     * 表达式右边是比较的解析函数
     *
     * @param left
     * @return
     */
    private Expression expressionRCompare(Expression left) {
        Expression result = left;

        if (type == EQUAL_LESS) {
            match(EQUAL_LESS);
            Expression higher = expressionR(higherExpression());
            result = expressionRCompare(BinaryExpression.createChild(Operator.OP_LESS_EQUAL, left, higher));
        } else if (type == EQUAL_GREATE) {
            match(EQUAL_GREATE);
            Expression higher = expressionR(higherExpression());
            result = expressionRCompare(BinaryExpression.createChild(Operator.OP_GREATER_EQUAL, left, higher));
        } else if (type == NOT_EQUAL) {
            match(NOT_EQUAL);
            Expression higher = expressionR(higherExpression());
            result = expressionRCompare(BinaryExpression.createChild(Operator.OP_NOT_EQUAL, left, higher));
        } else if (type == EQUAL) {
            match(EQUAL);
            Expression higher = expressionR(higherExpression());
            result = expressionRCompare(BinaryExpression.createChild(Operator.OP_EQUAL, left, higher));
        } else if (type == LESS) {
            match(LESS);
            Expression higher = expressionR(higherExpression());
            result = expressionRCompare(BinaryExpression.createChild(Operator.OP_LESS, left, higher));
        } else if (type == GREATER) {
            match(GREATER);
            Expression higher = expressionR(higherExpression());
            result = expressionRCompare(BinaryExpression.createChild(Operator.OP_GREATER, left, higher));
        }
        return result;
    }

    /**
     * 表达式右边解析函数
     *
     * @param left
     * @return
     */
    private Expression expressionR(Expression left) {
        Expression result = left;

        if (type == ADD_OR_POSITIVE) {
            match(ADD_OR_POSITIVE);
            Expression higher = higherExpression();
            result = expressionR(BinaryExpression.createChild(Operator.OP_ADD, left, higher));
        } else if (type == SUBTRACT_OR_NEGATIVE) {
            match(SUBTRACT_OR_NEGATIVE);
            Expression higher = higherExpression();
            result = expressionR(BinaryExpression.createChild(Operator.OP_SUB, left, higher));
        }
        return result;
    }

    /**
     * 根据当前位置，获取公式中的字符
     *
     * @param current
     * @return
     */
    private char get(int current) {

        if (current >= text.length()) {
            return 0;
        }
        return text.charAt(current);
    }

    /**
     * 逐字符检查表达式的每一个字符
     * 
     * 根据字符的特性设置如下变量：
     * inString
     * current
     * identifier
     * type
     */
    private void lookAhead() {
        for (; ; ) {
            if (inString) {
                if (get(current) != STRING) {
                    identifier += get(current);
                    ++current;
                    continue;
                }
            }

            if (isSpace(get(current))) {
                ++current;
                continue;
            }
            
            if (get(current) == STRING) {
                type = STRING;
                ++current;
                if (!inString) {
                    identifier = "";
                    inString = true;
                    continue;
                } else {
                    inString = false;
                    break;
                }
            }

            if (get(current) == LESS && get(current + 1) == EQUAL) {
                type = EQUAL_LESS;
                current += 2;
            } else {
                if (get(current) == GREATER && get(current + 1) == EQUAL) {
                    type = EQUAL_GREATE;
                    current += 2;
                } else {
                    if (get(current) == EQUAL && get(current + 1) == EQUAL) {
                        type = EQUAL;
                        current += 2;
                    } else {
                        if (get(current) == NOT && get(current + 1) == EQUAL) {
                            type = NOT_EQUAL;
                            current += 2;
                        } else {
                            if (get(current) == BIT_AND && get(current + 1) == BIT_AND) {
                                type = AND;
                                current += 2;
                            } else {
                                if (get(current) == BIT_OR && get(current + 1) == BIT_OR) {
                                    type = OR;
                                    current += 2;
                                } else {
                                    char ch = get(current);
                                    if (ch == ADD_OR_POSITIVE
                                            || ch == SUBTRACT_OR_NEGATIVE
                                            || ch == MULTIPLY
                                            || ch == DIVIDE
                                            || ch == MOD
                                            || ch == LEFT_BRACKET
                                            || ch == RIGHT_BRACKET
                                            || ch == PARAMETER_SEPERATOR
                                            || ch == EQUAL
                                            || ch == LESS
                                            || ch == GREATER) {
                                        type = ch;
                                        ++current;
                                    } else {
                                        if (isIdentifierFirstChar(get(current))) {
                                            type = IDENTIFIER;
                                            identifier = "";
                                            identifier += get(current);
                                            ++current;
                                            while (isIdentifierChar(get(current))) {
                                                identifier += get(current);
                                                ++current;
                                            }
                                        } else {
                                            if (get(current) == 0) {
                                                type = FINISHED;
                                            } else {
                                                identifier = "";
                                                identifier += get(current);
                                                ++current;
                                                boolean isFloat = false;
                                                while ((isDecimalChar(get(current)) && !isFloat) || isDigital(get(current))) {
                                                    identifier += get(current);
                                                    if (isDecimalChar(get(current))) {
                                                        isFloat = true;
                                                    }
                                                    ++current;
                                                }
                                                type = isFloat ? FLOAT : INTEGER;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            break;
        }
    }

    /**
     * 匹配字符，配型匹配成功，回调lookAhead
     *
     * @param type
     */
    private void match(char type) {
        if (this.type == type) {
            lookAhead();
        }else {
            throw new FormulaException("mismatched token, index:" + current);
        }
    }
}
