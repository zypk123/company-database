package com.huntkey.rx.sceo.formula.engine.function.math;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.huntkey.rx.sceo.formula.common.function.FunctionDescriber;
import com.huntkey.rx.sceo.formula.engine.function.Function;

/**
 * 数学函数超类
 * <p>
 * @author chenfei on 2017/5/17.
 */
abstract public class MathFunction extends Function {

    @Override
    public FunctionDescriber buildFunctionDescriber() {

        FunctionDescriber describer = new FunctionDescriber();
        describer.setFunClassify("math");
        describer.setClassifyDesc("数学函数");
        describer.setFunName(this.getClass().getSimpleName());

        buildParamsDesc(describer);

        return describer;
    }

    protected double[] extractArrayDelegate(Object arrObj) {

        return ArrayExtractor.extract(arrObj);
    }

    static class ArrayExtractor {

        public static double[] extract(Object arrObj) {

            String className = arrObj.getClass().getComponentType().toString();

            if (className.contains("int")) {
                return extractIntArray((int[]) arrObj);
            } else if (className.contains("float")) {
                return extractIntArray((int[]) arrObj);
            } else if (className.contains("long")) {
                return extractIntArray((int[]) arrObj);
            } else if (className.contains("double")) {
                return (double[]) arrObj;
            } else if (className.contains("Object")) {
                JSONArray array = (JSONArray) JSON.toJSON(arrObj);
                double[] doubleArray = new double[array.size()];
                for (int i = 0; i < array.size(); i++) {
                    doubleArray[i] = Double.valueOf(array.get(i).toString());
                }
                return doubleArray;
            } else {
                throw new RuntimeException("Can not extract array from : " + arrObj + " which componentType is " + className);
            }
        }


        public static double[] extractIntArray(int[] arr) {
            double[] dArr = new double[arr.length];
            int counter = 0;
            for (int i : arr) {
                dArr[counter++] = i * 1.0d;
            }

            return dArr;
        }

        private static double[] extractFloatArray(float[] arr) {
            double[] dArr = new double[arr.length];
            int counter = 0;
            for (float i : arr) {
                dArr[counter++] = i * 1.0d;
            }

            return dArr;
        }

        private static double[] extractLongArray(long[] arr) {
            double[] dArr = new double[arr.length];
            int counter = 0;
            for (long i : arr) {
                dArr[counter++] = i * 1.0d;
            }

            return dArr;
        }

    }
}
