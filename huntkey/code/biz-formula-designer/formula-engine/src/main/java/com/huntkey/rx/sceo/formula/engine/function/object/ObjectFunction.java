package com.huntkey.rx.sceo.formula.engine.function.object;

import com.huntkey.rx.sceo.formula.common.function.FunctionDescriber;
import com.huntkey.rx.sceo.formula.engine.function.Function;

/**
 * 对象函数超类
 *
 * @author chenfei on 2017/8/24.
 */
abstract public class ObjectFunction extends Function {

    @Override
    public FunctionDescriber buildFunctionDescriber() {

        FunctionDescriber describer = new FunctionDescriber();
        describer.setFunClassify("object");
        describer.setClassifyDesc("对象函数");
        describer.setFunName(this.getClass().getSimpleName());

        buildParamsDesc(describer);

        return describer;
    }

}
