package com.huntkey.rx.sceo.formula.engine.function.math;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
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
 * 函数使所有以参数形式给出的数字相加并返回和
 * <p>
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * SUM(number1: number, [number2: number], ...): decimal
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * <p>
 * @author chenfei on 2017/5/15.
 */
public class SUM extends MathFunction {

    @Override
    protected void buildParamsDesc(FunctionDescriber describer) {

        StringBuffer buff = new StringBuffer();
        buff.append("函数使所有以参数形式给出的数字相加并返回和\n")
                .append("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n")
                .append("SUM(number1: number, [number2: number], ...): decimal\n")
                .append("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");

        describer.setFunDesc(buff.toString());
        describer.setFunType(FunInOrOutType.DECIMAL);

        int counter = 1;
        ParamType pt = new ParamType(counter++, ParamOrderType.Loop, OptionType.NonOption)
                .addTypes(FunInOrOutType.NUMBER);

        describer.addParamTypes(pt);
    }

    @Override
    public ExprValue getValue(DataProvider provider) throws FormulaException {

        checkArgsAtLeastCnt(1);

        double sum = 0d;
        int count = this.getArgumentCount();
        for (int i = 0; i < count; i++) {
            ExprValue value = this.getArgument(i).getValue(provider);
            if (value.isArray()) {
                double[] arr = extractArrayDelegate(value.getOriginValue());
                for (double n : arr) {
                    sum = Arith.add(sum, n);
                }
            } else {
                sum = Arith.add(sum, this.getArgument(i).getValue(provider).getDouble());
            }
        }

        return new ExprValue(sum);
    }

//    @Override
//    protected double[] extractArrayDelegate(Object arrObj) {
//        String className = arrObj.getClass().getComponentType().toString();
//
//        if (className.contains("int")) {
//            return ArrayExtractor.extractIntArray((int[]) arrObj);
//        } else if (className.contains("float")) {
//            return ArrayExtractor.extractIntArray((int[]) arrObj);
//        } else if (className.contains("long")) {
//            return ArrayExtractor.extractIntArray((int[]) arrObj);
//        } else if (className.contains("double")) {
//            return (double[]) arrObj;
//        } else if (className.contains("Object")) {
//            JSONArray array = (JSONArray) JSON.toJSON(arrObj);
//            double[] doubleArray = new double[array.size()];
//            for (int i = 0; i < array.size(); i++) {
//                doubleArray[i] = (Double) array.get(i);
//            }
//            return doubleArray;
//        } else {
//            throw new RuntimeException("Can not extract array from : " + arrObj + " which componentType is " + className);
//        }
//    }
}
