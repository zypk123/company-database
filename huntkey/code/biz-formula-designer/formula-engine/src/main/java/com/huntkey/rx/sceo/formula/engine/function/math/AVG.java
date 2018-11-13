package com.huntkey.rx.sceo.formula.engine.function.math;

import com.huntkey.rx.sceo.formula.common.function.FunctionDescriber;
import com.huntkey.rx.sceo.formula.common.function.OptionType;
import com.huntkey.rx.sceo.formula.common.function.ParamOrderType;
import com.huntkey.rx.sceo.formula.common.function.ParamType;
import com.huntkey.rx.sceo.formula.engine.DataProvider;
import com.huntkey.rx.sceo.formula.engine.ExprValue;
import com.huntkey.rx.sceo.formula.engine.exception.FormulaException;
import com.huntkey.rx.sceo.formula.common.constant.FunInOrOutType;
import com.huntkey.rx.sceo.formula.engine.tools.Arith;

/**
 * 返回平均数
 * <p>
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * AVG(number1: number, [number2: number], ...): decimal
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * <p>
 * @author chenfei on 2017/5/15.
 */
public class AVG extends MathFunction {

    @Override
    protected void buildParamsDesc(FunctionDescriber describer) {

        StringBuffer buff = new StringBuffer();
        buff.append("返回平均数\n")
                .append("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n")
                .append("AVG(number1: number, [number2: number], ...): decimal\n")
                .append("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");

        describer.setFunDesc(buff.toString());
        describer.setFunType(FunInOrOutType.DECIMAL);

        ParamType pt = new ParamType(1, ParamOrderType.Loop, OptionType.NonOption)
                .addTypes(FunInOrOutType.NUMBER);

        describer.addParamTypes(pt);
    }

    @Override
    public ExprValue getValue(DataProvider provider) throws FormulaException {

        checkArgsAtLeastCnt(1);

        double sum = 0d;
        int count = this.getArgumentCount();
        int arrCount = 0;
        for (int i = 0; i < count; i++) {

            ExprValue value = this.getArgument(i).getValue(provider);
            if (value.isArray()) {
                double[] arr = extractArrayDelegate(value.getOriginValue());
                for (double n : arr) {
                    arrCount++;
                    sum += n;
//                    sum = Arith.add(sum, n);a
                }
                arrCount--;
            } else {
                double d = this.getArgument(i).getValue(provider).getDouble();
                sum += d;
            }

        }

        return new ExprValue(Arith.div(sum, (count + arrCount)));
    }


}

