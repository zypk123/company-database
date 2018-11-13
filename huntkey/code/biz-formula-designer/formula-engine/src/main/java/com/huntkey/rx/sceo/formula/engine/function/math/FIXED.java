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

import java.text.DecimalFormat;

/**
 * 将数字舍入到指定的小数位数，以十进制数格式对该数进行格式设置
 * <p>
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * FIXED(number: number, [decimals: int]): decimal
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * number: 必需。 要进行舍入并转换为文本的数字
 * decimals: 可选。 小数点右边的位数
 * <p>
 * @author chenfei on 2017/5/15.
 */
public class FIXED extends MathFunction {

    @Override
    protected void buildParamsDesc(FunctionDescriber describer) {

        StringBuffer buff = new StringBuffer();
        buff.append("将数字舍入到指定的小数位数，以十进制数格式对该数进行格式设置\n")
                .append("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n")
                .append("FIXED(number: number, [decimals: int]): decimal\n")
                .append("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n")
                .append("number: 必需。 要进行舍入并转换为文本的数字\n")
                .append("decimals: 可选。 小数点右边的位数\n");

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

        int[] category = new int[]{1, 2};
        checkArgsCategory(category);

        ExprValue number = this.getArgument(0).getValue(provider);
        if (ExprValue.DataType.ARR.equals(number.getDataType())) {

            Object[] objects = (Object[]) number.getValue();
            if (this.getArgumentCount() == 2) {
                int decimals = this.getArgument(1).getValue(provider).getInt();
                DecimalFormat format = Arith.getFormat(decimals);
                Object[] newObjects = new Object[objects.length];
                for (int i = 0; i < objects.length; i++) {
                    double result = Arith.div(Double.valueOf(objects[i].toString()), 1d);
                    newObjects[i] = format.format(result);
                }
                return new ExprValue(newObjects);
            } else {
                return new ExprValue(objects);
            }
        } else {
            if (this.getArgumentCount() == 2) {
                int decimals = this.getArgument(1).getValue(provider).getInt();
                DecimalFormat format = Arith.getFormat(decimals);
                double result = Arith.div(number.getDouble(), 1d);
                return new ExprValue(format.format(result));
            } else {
                return number;
            }
        }
    }

//    public static void main(String[] args) {
//        BigDecimal d = new BigDecimal(123456.789d);
//        System.out.println(d.setScale(2, RoundingMode.HALF_UP).toString());
//    }
}
