package com.huntkey.rx.sceo.formula.engine.function.object;

import com.huntkey.rx.sceo.formula.common.constant.FunInOrOutType;
import com.huntkey.rx.sceo.formula.common.function.FunctionDescriber;
import com.huntkey.rx.sceo.formula.common.function.OptionType;
import com.huntkey.rx.sceo.formula.common.function.ParamOrderType;
import com.huntkey.rx.sceo.formula.common.function.ParamType;
import com.huntkey.rx.sceo.formula.engine.DataProvider;
import com.huntkey.rx.sceo.formula.engine.ExprValue;
import com.huntkey.rx.sceo.formula.engine.exception.FormulaException;

/**
 * 将对象或是对象链接进行类型转换
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * OBJECT(object: any): object
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *
 * @author chenfei on 2017/5/15.
 */
public class OBJECT extends ObjectFunction {

    @Override
    protected void buildParamsDesc(FunctionDescriber describer) {

        StringBuffer buff = new StringBuffer();
        buff.append("将对象或是对象链接进行类型转换\n")
            .append("~~~~~~~~~~~~~~~~~~~~~~~~~~~\n")
            .append("OBJECT(object: any): object\n")
            .append("~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");

        describer.setFunDesc(buff.toString());
        describer.setFunType(FunInOrOutType.OBJECT);

        int counter = 1;
        ParamType pt = new ParamType(counter++, ParamOrderType.Sequence, OptionType.NonOption)
            .addTypes(FunInOrOutType.ANY);

        describer.addParamTypes(pt);
    }

    @Override
    public ExprValue getValue(DataProvider provider) throws FormulaException {

        checkArgsAtLeastCnt(1);
        Object value = this.getArgument(0).getValue(provider).getOriginValue();
        return new ExprValue(value);
    }
}
