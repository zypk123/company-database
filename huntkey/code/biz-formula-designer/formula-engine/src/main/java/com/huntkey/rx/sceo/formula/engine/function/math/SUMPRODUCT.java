package com.huntkey.rx.sceo.formula.engine.function.math;

import com.huntkey.rx.sceo.formula.common.constant.FunInOrOutType;
import com.huntkey.rx.sceo.formula.common.function.FunctionDescriber;
import com.huntkey.rx.sceo.formula.common.function.OptionType;
import com.huntkey.rx.sceo.formula.common.function.ParamOrderType;
import com.huntkey.rx.sceo.formula.common.function.ParamType;
import com.huntkey.rx.sceo.formula.engine.DataProvider;
import com.huntkey.rx.sceo.formula.engine.ExprValue;
import com.huntkey.rx.sceo.formula.engine.exception.FormulaException;
import com.huntkey.rx.sceo.formula.engine.tools.Arith;

/**
 * 在给定的数组中，将数组间对应的元素相乘，并返回乘积之和
 * <p>
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * SUMPRODUCT(array1: number[], [array2]: number[]): decimal
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * <p>
 * @author chenfei on 2017/5/15.
 */
public class SUMPRODUCT extends MathFunction {

    @Override
    protected void buildParamsDesc(FunctionDescriber describer) {

        StringBuffer buff = new StringBuffer();
        buff.append("在给定的数组中，将数组间对应的元素相乘，并返回乘积之和\n")
                .append("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n")
                .append("SUMPRODUCT(Number: number, Number: number): decimal\n")
                .append("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");

        describer.setFunDesc(buff.toString());
        describer.setFunType(FunInOrOutType.DECIMAL);

        int counter = 1;
        ParamType pt = new ParamType(counter++, ParamOrderType.Sequence, OptionType.NonOption)
                .addTypes(FunInOrOutType.NUMBER);

        ParamType pt2 = new ParamType(counter++, ParamOrderType.Sequence, OptionType.Option)
                .addTypes(FunInOrOutType.NUMBER);

        describer.addParamTypes(pt, pt2);
    }

    @Override
    public ExprValue getValue(DataProvider provider) throws FormulaException {

        int[] category = new int[]{1, 2};
        checkArgsCategory(category);
        ExprValue number1 = this.getArgument(0).getValue(provider);
        if (ExprValue.DataType.ARR.equals(number1.getDataType())) {
            Object[] array1 = (Object[]) number1.getOriginValue();
            if (this.getArgumentCount() == 2) {
                ExprValue number2 = this.getArgument(1).getValue(provider);
                Object[] array2 = (Object[]) number2.getOriginValue();

                if (array1.length != array2.length) {
                    throw new FormulaException("the two array type arguments must have the same length.");
                }

                for (int i = 0; i < array1.length; i++) {
                    array1[i] = Double.valueOf(array1[i].toString()) * Double.valueOf(array2[i].toString());
                }
            }

            double sum = 0d;

            for (int i = 0; i < array1.length; i++) {
                sum = Arith.add(sum, Double.valueOf(array1[i].toString()));
            }

            return new ExprValue(sum);
        } else {
            throw new FormulaException("SUMPRODUCT function supports arr type array as the first argument.");
        }


    }
}
