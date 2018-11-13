package com.huntkey.rx.sceo.formula.engine.function.math;

import com.huntkey.rx.sceo.formula.common.constant.FunInOrOutType;
import com.huntkey.rx.sceo.formula.common.function.FunctionDescriber;
import com.huntkey.rx.sceo.formula.common.function.OptionType;
import com.huntkey.rx.sceo.formula.common.function.ParamOrderType;
import com.huntkey.rx.sceo.formula.common.function.ParamType;
import com.huntkey.rx.sceo.formula.engine.DataProvider;
import com.huntkey.rx.sceo.formula.engine.ExprValue;
import com.huntkey.rx.sceo.formula.engine.exception.FormulaException;

import java.math.BigDecimal;

/**
 * 将数字向下舍入到最接近的整数
 * <p>
 * ~~~~~~~~~~~~~~~~~~~~~~~~
 * INT(number: number): int
 * ~~~~~~~~~~~~~~~~~~~~~~~~
 * <p>
 * @author chenfei on 2017/5/15.
 */
public class INT extends MathFunction {

    @Override
    protected void buildParamsDesc(FunctionDescriber describer) {

        StringBuffer buff = new StringBuffer();
        buff.append("将数字向下舍入到最接近的整数\n")
                .append("~~~~~~~~~~~~~~~~~~~~~~~~\n")
                .append("INT(number: number): int\n")
                .append("~~~~~~~~~~~~~~~~~~~~~~~~\n");

        describer.setFunDesc(buff.toString());
        describer.setFunType(FunInOrOutType.INT);

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
                BigDecimal b = new BigDecimal(objects[i].toString());
                newObjects[i] = b.toBigInteger().longValue();
            }
            return new ExprValue(newObjects);
        } else {
            BigDecimal b = new BigDecimal(number.getDouble());
            return new ExprValue(b.toBigInteger().longValue());
        }
    }

}
