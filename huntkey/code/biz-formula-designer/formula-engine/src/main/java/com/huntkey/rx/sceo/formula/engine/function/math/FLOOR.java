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

import java.math.BigDecimal;

/**
 * 返回将参数 number 向下舍入（沿绝对值减小的方向）为最接近的指定基数的倍数
 * <p>
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * FLOOR(number: number, significance: int): decimal
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * <p>
 * @author chenfei on 2017/5/15.
 */
public class FLOOR extends MathFunction {

    @Override
    protected void buildParamsDesc(FunctionDescriber describer) {

        StringBuffer buff = new StringBuffer();
        buff.append("返回将参数 number 向下舍入（沿绝对值减小的方向）为最接近的指定基数的倍数\n")
                .append("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n")
                .append("FLOOR(number: number, significance: int): decimal\n")
                .append("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");

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
        int significance = this.getArgument(1).getValue(provider).getInt();
        if (ExprValue.DataType.ARR.equals(number.getDataType())) {
            Object[] objects = (Object[]) number.getValue();
            Object[] newObjects = new Object[objects.length];
            for (int i = 0; i < objects.length; i++) {

                BigDecimal divisor = new BigDecimal(Double.toString(Arith.div(Double.valueOf(objects[i].toString()), significance)));
                newObjects[i] = getResult(divisor, significance);
            }

            return new ExprValue(newObjects);
        } else {

            BigDecimal divisor = new BigDecimal(Double.toString(Arith.div(number.getDouble(), significance)));
            return new ExprValue(getResult(divisor, significance));
        }
    }

    private Object getResult(BigDecimal divisor, int significance) {
        Object result = 0d;
        if (divisor.compareTo(BigDecimal.ZERO) > 0 && significance > 0) {
            result = Arith.mul(divisor.toBigInteger().doubleValue(), significance);
        } else if (divisor.compareTo(BigDecimal.ZERO) > 0 && significance < 0) {
            result = Arith.mul(divisor.toBigInteger().doubleValue() , significance);
        } else {
            result = "NUM";
        }

        return result;
    }
}
