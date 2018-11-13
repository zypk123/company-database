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
 * 统计参数个数
 * <p>
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * COUNT(value1: any, [value2: any], ...): int
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * <p>
 * @author chenfei on 2017/5/15.
 */
public class COUNT extends MathFunction {

    @Override
    protected void buildParamsDesc(FunctionDescriber describer) {

        StringBuffer buff = new StringBuffer();
        buff.append("统计参数个数\n")
                .append("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n")
                .append("COUNT(value1: any, [value2: any], ...): int\n")
                .append("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");

        describer.setFunDesc(buff.toString());
        describer.setFunType(FunInOrOutType.INT);

        int counter = 1;
        ParamType pt = new ParamType(counter++, ParamOrderType.Loop, OptionType.NonOption)
                .addTypes(FunInOrOutType.ANY);

        describer.addParamTypes(pt);
    }

    @Override
    public ExprValue getValue(DataProvider provider) throws FormulaException {

        checkArgsAtLeastCnt(1);
        int counter = 0;

        for (int i = 0; i < this.getArgumentCount(); i++) {
            Object obj = this.getArgument(i).getValue(provider);
            ExprValue value = (ExprValue) obj;
            if (value.isArray()) {
                counter += extractArrayLength(value);
            } else {
                counter++;
            }
        }

        return new ExprValue(counter);
    }

    /**
     * extract length of a array which has any type.
     *
     * @param arrObj
     * @return
     */
    private long extractArrayLength(ExprValue arrObj) {
        String className = arrObj.getDataType().toString();
        if ("Long".equals(className)) {
            return ((long[]) arrObj.getValue()).length;
        } else if ("Double".equals(className)) {
            return ((double[]) arrObj.getValue()).length;
        } else if ("Boolean".equals(className)) {
            return ((boolean[]) arrObj.getValue()).length;
        } else {
            return ((Object[]) arrObj.getValue()).length;
        }
    }

}
