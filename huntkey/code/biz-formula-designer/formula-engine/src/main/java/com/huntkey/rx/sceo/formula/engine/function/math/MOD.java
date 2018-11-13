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
 * 返回两数相除的余数。 结果的符号与被除数相同
 * <p>
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * MOD(number: number, divisor: int): decimal
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * <p>
 *
 * @author chenfei on 2017/5/15.
 */
public class MOD extends MathFunction {

    @Override
    protected void buildParamsDesc(FunctionDescriber describer) {

        StringBuffer buff = new StringBuffer();
        buff.append("返回两数相除的余数。 结果的符号与除数相同\n")
                .append("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n")
                .append("MOD(number: number, divisor: int): decimal\n")
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
        int divisor = this.getArgument(1).getValue(provider).getInt();
        if (ExprValue.DataType.ARR.equals(number.getDataType())) {
            Object[] objects = (Object[]) number.getValue();
            Object[] newObjects = new Object[objects.length];
            for (int i = 0; i < objects.length; i++) {
                newObjects[i] = Modarithm.mod(Double.valueOf(objects[i].toString()), divisor);
            }

            return new ExprValue(newObjects);
        } else {
            return new ExprValue(Modarithm.mod(number.getDouble(), divisor));
        }
    }

    static class Modarithm {
        public static double mod(double number, int divisor) {
            BigDecimal b1 = new BigDecimal(Double.toString(Math.floor(number / divisor) * divisor));
            b1 = new BigDecimal(Double.toString(number)).subtract(b1);
            return b1.doubleValue();
        }
    }
}
