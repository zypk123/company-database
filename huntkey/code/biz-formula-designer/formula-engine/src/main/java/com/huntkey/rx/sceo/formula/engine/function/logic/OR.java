package com.huntkey.rx.sceo.formula.engine.function.logic;

import com.huntkey.rx.sceo.formula.common.constant.FunInOrOutType;
import com.huntkey.rx.sceo.formula.common.function.FunctionDescriber;
import com.huntkey.rx.sceo.formula.common.function.OptionType;
import com.huntkey.rx.sceo.formula.common.function.ParamOrderType;
import com.huntkey.rx.sceo.formula.common.function.ParamType;
import com.huntkey.rx.sceo.formula.engine.DataProvider;
import com.huntkey.rx.sceo.formula.engine.ExprValue;
import com.huntkey.rx.sceo.formula.engine.exception.FormulaException;

/**
 * 在参数组中，任何一个参数逻辑值为true，即返回true；只有当所有逻辑参数值为false，才返回false
 *
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * OR(logical1: boolean, [logical2: boolean], ...): boolean
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *
 * @author chenfei on 2017/5/15.
 */
public class OR extends LogicFunction {

    @Override
    protected void buildParamsDesc(FunctionDescriber describer) {

        StringBuffer buff = new StringBuffer();
        buff.append("在参数组中，任何一个参数逻辑值为true，即返回true；只有当所有逻辑参数值为false，才返回false\n")
            .append("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n")
            .append("OR(logical1: boolean, [logical2: boolean], ...): boolean\n")
            .append("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");

        describer.setFunDesc(buff.toString());
        describer.setFunType(FunInOrOutType.BOOLEAN);

        int counter = 1;
        ParamType pt = new ParamType(counter++, ParamOrderType.Loop, OptionType.NonOption)
                .addTypes(FunInOrOutType.BOOLEAN);

        describer.addParamTypes(pt);
    }

    @Override
    public ExprValue getValue(DataProvider provider) throws FormulaException {

        checkArgsAtLeastCnt(2);

        int count = this.getArgumentCount();

        for (int i = 0; i < count; i++) {
            ExprValue boolValue = getArgument(i).getValue(provider);

            if (boolValue.getBoolean()) {
                return new ExprValue(true);
            }
        }

        return new ExprValue(false);
    }

}
