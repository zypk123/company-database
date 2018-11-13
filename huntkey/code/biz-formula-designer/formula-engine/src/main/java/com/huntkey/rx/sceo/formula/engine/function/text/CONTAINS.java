package com.huntkey.rx.sceo.formula.engine.function.text;

import com.huntkey.rx.sceo.formula.common.constant.FunInOrOutType;
import com.huntkey.rx.sceo.formula.common.function.FunctionDescriber;
import com.huntkey.rx.sceo.formula.common.function.OptionType;
import com.huntkey.rx.sceo.formula.common.function.ParamOrderType;
import com.huntkey.rx.sceo.formula.common.function.ParamType;
import com.huntkey.rx.sceo.formula.engine.DataProvider;
import com.huntkey.rx.sceo.formula.engine.ExprValue;
import com.huntkey.rx.sceo.formula.engine.exception.FormulaException;

/**
 * 判断字符串text1是否包含字符串text2，包含返回true，否则返回false
 *
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * CONTAINS(text1: varchar, text2: varchar): boolean
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *
 * @author chenfei on 2017/5/15.
 */
public class CONTAINS extends TextFunction {

    @Override
    protected void buildParamsDesc(FunctionDescriber describer) {

        StringBuffer buff = new StringBuffer();
        buff.append("判断字符串text1是否包含字符串text2，包含返回true，否则返回false\n")
            .append("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n")
            .append("CONTAINS(text1: varchar, text2: varchar): boolean\n")
            .append("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");

        describer.setFunDesc(buff.toString());
        describer.setFunType(FunInOrOutType.BOOLEAN);

        int counter = 1;
        ParamType pt = new ParamType(counter++, ParamOrderType.Sequence, OptionType.NonOption)
            .addTypes(FunInOrOutType.VARCHAR);

        ParamType pt2 = new ParamType(counter++, ParamOrderType.Sequence, OptionType.NonOption)
            .addTypes(FunInOrOutType.VARCHAR);

        describer.addParamTypes(pt, pt2);
    }

    @Override
    public ExprValue getValue(DataProvider provider) throws FormulaException {

        checkArgsOnlyCnt(2);

        String text1 = this.getArgument(0).getValue(provider).getString();
        String text2 = this.getArgument(1).getValue(provider).getString();

        return new ExprValue(text1.contains(text2));
    }

}
