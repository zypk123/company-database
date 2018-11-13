package com.huntkey.rx.sceo.formula.engine.function.logic;

import com.huntkey.rx.sceo.formula.common.function.FunctionDescriber;
import com.huntkey.rx.sceo.formula.engine.function.Function;

/**
 * 逻辑函数超类
 *
 * @author chenfei on 2017/5/17.
 */
abstract public class LogicFunction extends Function {

    @Override
    public FunctionDescriber buildFunctionDescriber() {

        FunctionDescriber describer = new FunctionDescriber();
        describer.setFunClassify("logic");
        describer.setClassifyDesc("逻辑函数");
        describer.setFunName(this.getClass().getSimpleName());

        buildParamsDesc(describer);

        return describer;
    }

    /**
     * 函数分类
     */
    protected String funClassify = "logic";

    /**
     * 函数分类描述
     */
    protected String classifyDesc = "逻辑函数";
}
