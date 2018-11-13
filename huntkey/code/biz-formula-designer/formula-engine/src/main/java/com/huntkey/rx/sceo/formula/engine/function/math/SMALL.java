package com.huntkey.rx.sceo.formula.engine.function.math;

import com.huntkey.rx.sceo.formula.common.constant.FunInOrOutType;
import com.huntkey.rx.sceo.formula.common.function.FunctionDescriber;
import com.huntkey.rx.sceo.formula.common.function.OptionType;
import com.huntkey.rx.sceo.formula.common.function.ParamOrderType;
import com.huntkey.rx.sceo.formula.common.function.ParamType;
import com.huntkey.rx.sceo.formula.engine.DataProvider;
import com.huntkey.rx.sceo.formula.engine.ExprValue;
import com.huntkey.rx.sceo.formula.engine.exception.FormulaException;

import java.util.TreeSet;

/**
 * 返回数据集中第k个最小值
 * <p>
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * SMALL(array: number[], k: int): decimal
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * <p>
 * @author chenfei on 2017/5/15.
 */
public class SMALL extends MathFunction {

    @Override
    protected void buildParamsDesc(FunctionDescriber describer) {

        StringBuffer buff = new StringBuffer();
        buff.append("返回数据集中第k个最小值\n")
                .append("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n")
                .append("SMALL(Number: number, k: int): decimal\n")
                .append("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");

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

        checkArgsAtLeastCnt(1);

        try {
            TreeSet<Double> set = new TreeSet<Double>();
            ExprValue number = this.getArgument(0).getValue(provider);
            int k = this.getArgument(1).getValue(provider).getInt();
            if (ExprValue.DataType.ARR.equals(number.getDataType())) {
                Object[] objects = (Object[]) number.getValue();
                for (int i = 0; i < objects.length; i++) {
                    set.add(Double.valueOf(objects[i].toString()));
                }

                if (k > set.size()) {
                    throw new FormulaException("the k argument is over the range.");
                }

                return new ExprValue(set.toArray(new Double[set.size()])[k - 1]);
            } else {
                throw new FormulaException("SMALL function supports arr type array as the first argument.");
            }
        } catch (Exception e) {
            throw new FormulaException("SMALL function supports double type array as the first argument.");
        }
    }
}
