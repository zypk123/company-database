package com.huntkey.rx.sceo.formula.engine.function.text;

import com.huntkey.rx.sceo.formula.common.constant.FunInOrOutType;
import com.huntkey.rx.sceo.formula.common.function.FunctionDescriber;
import com.huntkey.rx.sceo.formula.common.function.OptionType;
import com.huntkey.rx.sceo.formula.common.function.ParamOrderType;
import com.huntkey.rx.sceo.formula.common.function.ParamType;
import com.huntkey.rx.sceo.formula.engine.DataProvider;
import com.huntkey.rx.sceo.formula.engine.ExprValue;
import com.huntkey.rx.sceo.formula.engine.exception.FormulaException;

/**
 * 返回字符串
 *
 * ~~~~~~~~~~~~~~~~~~~~~~~~~
 * STRING(any: any): varchar
 * ~~~~~~~~~~~~~~~~~~~~~~~~~
 *
 * @author chenfei on 2017/5/15.
 */
public class STRING extends TextFunction {

    @Override
    protected void buildParamsDesc(FunctionDescriber describer) {

        StringBuffer buff = new StringBuffer();
        buff.append("返回字符串\n")
            .append("~~~~~~~~~~~~~~~~~~~~~~~~~\n")
            .append("STRING(any: any): varchar\n")
            .append("~~~~~~~~~~~~~~~~~~~~~~~~~\n");

        describer.setFunDesc(buff.toString());
        describer.setFunType(FunInOrOutType.VARCHAR);

        int counter = 1;
        ParamType pt = new ParamType(counter++, ParamOrderType.Sequence, OptionType.NonOption)
            .addTypes(FunInOrOutType.ANY);

        describer.addParamTypes(pt);
    }

    @Override
    public ExprValue getValue(DataProvider provider) throws FormulaException {

        checkArgsOnlyCnt(1);

        Object obj = this.getArgument(0).getValue(provider).getOriginValue();

        return new ExprValue(obj.toString());
    }

}
