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
 * 对参数逻辑值求反
 * NOT比较简单，就是把括号里为true的值变成false，括号里为false的值变为true
 *
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * NOT(logical: boolean): boolean
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *
 * @author chenfei on 2017/5/15.
 */
public class NOT extends LogicFunction {

    @Override
    protected void buildParamsDesc(FunctionDescriber describer) {

        StringBuffer buff = new StringBuffer();
        buff.append("对参数逻辑值求反\n")
            .append("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n")
            .append("NOT(logical: boolean): boolean\n")
            .append("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");

        describer.setFunDesc(buff.toString());
        describer.setFunType(FunInOrOutType.BOOLEAN);

        int counter = 1;
        ParamType pt = new ParamType(counter++, ParamOrderType.Sequence, OptionType.NonOption)
            .addTypes(FunInOrOutType.BOOLEAN);

        describer.addParamTypes(pt);
    }

    @Override
    public ExprValue getValue(DataProvider provider) throws FormulaException {

        checkArgsOnlyCnt(1);

        ExprValue boolValue = getArgument(0).getValue(provider);

        if (boolValue.getBoolean()) {
            return new ExprValue(false);
        }

        return new ExprValue(true);
    }

}
