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
 * 根据指定底数返回数字的对数
 * <p>
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * LOG(number: number, [base: int]): decimal
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * <p>
 * @author chenfei on 2017/5/15.
 */
public class LOG extends MathFunction {

    @Override
    protected void buildParamsDesc(FunctionDescriber describer) {

        StringBuffer buff = new StringBuffer();
        buff.append("根据指定底数返回数字的对数\n")
                .append("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n")
                .append("LOG(number: number, [base: int]): decimal\n")
                .append("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");

        describer.setFunDesc(buff.toString());
        describer.setFunType(FunInOrOutType.DECIMAL);

        int counter = 1;
        ParamType pt = new ParamType(counter++, ParamOrderType.Sequence, OptionType.NonOption)
                .addTypes(FunInOrOutType.NUMBER);

        ParamType pt2 = new ParamType(counter++, ParamOrderType.Sequence, OptionType.Option)
                .addTypes(FunInOrOutType.INT);

        describer.addParamTypes(pt, pt2);
    }

    @Override
    public ExprValue getValue(DataProvider provider) throws FormulaException {

        checkArgsAtMostCnt(2);

        ExprValue number = this.getArgument(0).getValue(provider);
        int base = 10;
        if (ExprValue.DataType.ARR.equals(number.getDataType())) {
            if (this.getArgumentCount() == 2) {
                base = this.getArgument(1).getValue(provider).getInt();
            }

            Object[] objects = (Object[]) number.getValue();
            Object[] newObjects = new Object[objects.length];
            for (int i = 0; i < objects.length; i++) {
                double math = Double.valueOf(objects[i].toString());
                if (math < 0) {
                    throw new FormulaException("There is a negative number in the data,can not use log function " + math);
                }
                newObjects[i] = Logarithm.log(math, base);
            }
            return new ExprValue(newObjects);
        } else {
            if (this.getArgumentCount() == 2) {
                base = this.getArgument(1).getValue(provider).getInt();
            }
            double math = number.getDouble();
            if (math < 0) {
                throw new FormulaException("There is a negative number in the data,can not use log function " + math);
            }

            return new ExprValue(Logarithm.log(math, base));
        }
    }

    /**
     * 根据换底公式
     */
    static class Logarithm {

        public static double log(double value, double base) {
            return Math.log(value) / Math.log(base);
        }
    }
}
