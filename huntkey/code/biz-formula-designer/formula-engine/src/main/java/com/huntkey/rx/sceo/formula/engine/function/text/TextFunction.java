package com.huntkey.rx.sceo.formula.engine.function.text;

import com.huntkey.rx.sceo.formula.common.function.FunctionDescriber;
import com.huntkey.rx.sceo.formula.engine.exception.FormulaException;
import com.huntkey.rx.sceo.formula.engine.function.Function;

/**
 * 文本类函数超类
 *
 * @author chenfei on 2017/5/17.
 */
abstract public class TextFunction extends Function {

    @Override
    public FunctionDescriber buildFunctionDescriber() {

        FunctionDescriber describer = new FunctionDescriber();
        describer.setFunClassify("text");
        describer.setClassifyDesc("文本函数");
        describer.setFunName(this.getClass().getSimpleName());

        buildParamsDesc(describer);

        return describer;
    }

    /**
     *
     * 包装空对象为空串
     *
     * @param object
     * @return
     */
    protected Object wrapNull(Object object) {
        if (null == object) {
            return "";
        }

        return object;
    }

    /**
     * 判断是否为String类型的对象
     *
     * @param object
     * @return
     */
    protected boolean isInstanceOfString(Object object) {
        if (object instanceof String) {
            return true;
        }

        return false;
    }

    /**
     * 检查参数为正数
     *
     * @param argName
     * @param argValue
     */
    protected void checkArgPositive(String argName, int argValue) {
        if (argValue < 0) {
            throw new FormulaException("The " + this.getClass().getSimpleName() + " function " + argName + " must be positive.");
        }
    }

    /**
     * 判断两个函数之间的大于关系
     *
     * @param argName1
     * @param argValue1
     * @param argName2
     * @param argValue2
     */
    protected void checkArgsLarge(String argName1, int argValue1, String argName2, int argValue2) {

        if (argValue1 > argValue2) {
            throw new FormulaException("The " + this.getClass().getSimpleName() + " function " + argName1 + ": " + argValue1 + " is larger than " + argName2 + ": " + argValue2 + ".");
        }
    }
}
