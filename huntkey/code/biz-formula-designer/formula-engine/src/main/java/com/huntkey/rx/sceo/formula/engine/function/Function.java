package com.huntkey.rx.sceo.formula.engine.function;

import com.huntkey.rx.sceo.formula.common.function.FunctionDescriber;
import com.huntkey.rx.sceo.formula.engine.exception.FormulaException;
import com.huntkey.rx.sceo.formula.engine.expression.Expression;
import com.huntkey.rx.sceo.formula.engine.expression.Operator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 函数抽象类
 * @author chenfei on 2017/5/15.
 */
public abstract class Function extends Expression implements Serializable {

    private static final long serialVersionUID = -3492374603888226726L;

    /**
     * 创建参数类型描述符
     * @return
     */
    abstract public FunctionDescriber buildFunctionDescriber();

    /**
     * 构建参数描述
     *
     * @param describer
     */
    abstract protected void buildParamsDesc(FunctionDescriber describer);

    /**
     * 函数构造器
     */
    public Function() {
        super(Operator.OP_EXTEND);
        this.prototype = this.getClass().getSimpleName();
    }

    /**
     * 函数参数
     */
    protected List<Expression> args = Collections.synchronizedList(new ArrayList<Expression>());

    /**
     * 原始类型
     */
    protected String prototype = "Function";

    /**
     * 获取函数列表
     *
     * @return
     */
    public Expression[] arguments() {
        return args.toArray(new Expression[args.size()]);
    }

    /**
     * 获取参数个数
     *
     * @return
     */
    public int getArgumentCount() {
        return args.size();
    }

    /**
     * 根据索引获取参数
     *
     * @param index
     * @return
     */
    public Expression getArgument(int index) {
        return args.get(index);
    }

    @Override
    public String getOperatorPrototype() {
        return prototype;
    }

    @Override
    public String toString() {

        StringBuffer buffer = new StringBuffer();
        buffer.append(getOperatorPrototype());
        buffer.append("(");

        for (int i = 0; i < getArgumentCount(); i++) {
            if (i != 0) {
                buffer.append(",");
            }
            buffer.append(getArgument(i).toString());
        }

        buffer.append(")");
        return buffer.toString();
    }

    /**
     * 添加参数到参数列表中
     *
     * @param arg
     * @return
     */
    public Function addArgument(Expression arg) {
        if (arg == null) {
            throw new FormulaException("argument can not be null.");
        }
        checkArgument(arg);
        args.add(arg);
        return this;
    }

    /**
     * 添加参数之前执行检查
     *
     * @param arg
     * @throw FormulaException
     */
    public void checkArgument(Expression arg) throws FormulaException {

        // nothing to do.
    }

    protected void checkArgsOnlyCnt(int cnt) {

        if (this.getArgumentCount() != cnt) {
            throw new FormulaException(this.getClass().getSimpleName() + " function supports only "
                    + cnt + "argument" + (cnt > 1 ? "s" : "") + ".");
        }
    }

    protected void checkArgsAtLeastCnt(int cnt) {

        if (this.getArgumentCount() <= cnt - 1) {
            throw new FormulaException(this.getClass().getSimpleName() + " function supports at least "
                    + cnt + "argument" + (cnt > 1 ? "s" : "") + ".");
        }
    }

    protected void checkArgsAtMostCnt(int cnt) {

        if (this.getArgumentCount() - 1 >= cnt) {
            throw new FormulaException(this.getClass().getSimpleName() + " function supports at most "
                    + cnt + "argument" + (cnt > 1 ? "s" : "") + ".");
        }
    }

    protected void checkArgsCategory(int[] category) {

        int cnt = this.getArgumentCount();
        for (int i = 0; i < category.length; i++) {
            if (cnt == category[i]) {
                return;
            }
        }

        throw new FormulaException(this.getClass().getSimpleName() + " function can not support "
                + cnt + "argument" + (cnt > 1 ? "s" : "") + ".");

    }

    protected void checkArgsNotNum(Object... args) {

        for (Object obj : args) {
            if (null == obj) {
                throw new FormulaException("The arguments can not be null.");
            }
        }
    }

}
