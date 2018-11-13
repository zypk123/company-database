package com.huntkey.rx.sceo.formula.engine.function.math;

import com.huntkey.rx.sceo.formula.common.function.FunctionDescriber;
import com.huntkey.rx.sceo.formula.common.function.OptionType;
import com.huntkey.rx.sceo.formula.common.function.ParamOrderType;
import com.huntkey.rx.sceo.formula.engine.DataProvider;
import com.huntkey.rx.sceo.formula.engine.ExprValue;
import com.huntkey.rx.sceo.formula.engine.exception.FormulaException;
import com.huntkey.rx.sceo.formula.common.constant.FunInOrOutType;
import com.huntkey.rx.sceo.formula.common.function.ParamType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 返回数字的绝对值
 * <p>
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * ABS(number: number): number
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * <p>
 * @author chenfei on 2017/5/15.
 */
public class ABS extends MathFunction {

    @Override
    protected void buildParamsDesc(FunctionDescriber describer) {

        StringBuffer buff = new StringBuffer();
        buff.append("返回数字的绝对值\n")
                .append("~~~~~~~~~~~~~~~~~~~~~~~~~~~\n")
                .append("ABS(number: number): decimal\n")
                .append("~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");

        describer.setFunDesc(buff.toString());
        describer.setFunType(FunInOrOutType.DECIMAL);

        ParamType pt = new ParamType(1, ParamOrderType.Sequence, OptionType.NonOption)
                .addTypes(FunInOrOutType.DECIMAL);

        describer.addParamTypes(pt);
    }

    @Override
    public ExprValue getValue(DataProvider provider) throws FormulaException {

        checkArgsOnlyCnt(1);

        ExprValue number = this.getArgument(0).getValue(provider);

        if (ExprValue.DataType.ARR.equals(number.getDataType())) {

            Object[] objects = (Object[]) number.getValue();
            Object[] newObjects = new Object[objects.length];
            for (int i = 0; i < objects.length; i++) {
                newObjects[i] = Math.abs(Double.valueOf(objects[i].toString()));
            }
            return new ExprValue(newObjects);
        } else {
            return new ExprValue(Math.abs(number.getDouble()));
        }

    }
}
