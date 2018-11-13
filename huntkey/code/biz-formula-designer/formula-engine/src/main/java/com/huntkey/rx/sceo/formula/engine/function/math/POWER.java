package com.huntkey.rx.sceo.formula.engine.function.math;

import com.huntkey.rx.sceo.formula.common.constant.FunInOrOutType;
import com.huntkey.rx.sceo.formula.common.function.FunctionDescriber;
import com.huntkey.rx.sceo.formula.common.function.OptionType;
import com.huntkey.rx.sceo.formula.common.function.ParamOrderType;
import com.huntkey.rx.sceo.formula.common.function.ParamType;
import com.huntkey.rx.sceo.formula.engine.DataProvider;
import com.huntkey.rx.sceo.formula.engine.ExprValue;
import com.huntkey.rx.sceo.formula.engine.exception.FormulaException;

/**
 * 返回数字乘幂的结果
 * <p>
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * POWER(number: number, power: int): decimal
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * <p>
 * @author chenfei on 2017/5/15.
 */
public class POWER extends MathFunction {

    @Override
    protected void buildParamsDesc(FunctionDescriber describer) {

        StringBuffer buff = new StringBuffer();
        buff.append("返回数字乘幂的结果\n")
                .append("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n")
                .append("POWER(number: number, power: int): decimal\n")
                .append("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");

        describer.setFunDesc(buff.toString());
        describer.setFunType(FunInOrOutType.DECIMAL);

        int counter = 1;
        ParamType pt = new ParamType(counter++, ParamOrderType.Sequence, OptionType.NonOption)
                .addTypes(FunInOrOutType.NUMBER);

        ParamType pt2 = new ParamType(counter++, ParamOrderType.Sequence, OptionType.NonOption)
                .addTypes(FunInOrOutType.INT);

        describer.addParamTypes(pt, pt2);
    }

    @Override
    public ExprValue getValue(DataProvider provider) throws FormulaException {

        checkArgsOnlyCnt(2);

        ExprValue number = this.getArgument(0).getValue(provider);
        int power = this.getArgument(1).getValue(provider).getInt();
        if (ExprValue.DataType.ARR.equals(number.getDataType())) {
            Object[] objects = (Object[]) number.getValue();
            Object[] newObjects = new Object[objects.length];
            for (int i = 0; i < objects.length; i++) {
                newObjects[i] = Math.pow(Double.valueOf(objects[i].toString()), power * 1d);
            }

            return new ExprValue(newObjects);
        } else {
            return new ExprValue(Math.pow(number.getDouble(), power * 1d));
        }
    }
}
