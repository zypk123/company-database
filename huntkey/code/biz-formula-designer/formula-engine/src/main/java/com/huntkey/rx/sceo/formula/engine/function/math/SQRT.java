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
 * 返回正的平方根
 * <p>
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * SQRT(number: number): decimal
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * <p>
 * @author chenfei on 2017/5/15.
 */
public class SQRT extends MathFunction {

    @Override
    protected void buildParamsDesc(FunctionDescriber describer) {

        StringBuffer buff = new StringBuffer();
        buff.append("返回正的平方根\n")
                .append("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n")
                .append("SQRT(number: number): decimal\n")
                .append("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");

        describer.setFunDesc(buff.toString());
        describer.setFunType(FunInOrOutType.DECIMAL);

        int counter = 1;
        ParamType pt = new ParamType(counter++, ParamOrderType.Sequence, OptionType.NonOption)
                .addTypes(FunInOrOutType.NUMBER);

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
                Double math = Double.valueOf(objects[i].toString());
                if (math < 0) {
                    throw new FormulaException("There is a negative number in the data,can not use sqrt function " + math);
                }
                newObjects[i] = Math.sqrt(math);
            }

            return new ExprValue(newObjects);
        } else {

            Double math = number.getDouble();
            if (math < 0) {
                throw new FormulaException("There is a negative number in the data,can not use sqrt function " + math);
            }
            return new ExprValue(Math.sqrt(math));
        }
    }
}
