package com.huntkey.rx.sceo.formula.engine.function.text;

import com.huntkey.rx.sceo.formula.common.constant.FunInOrOutType;
import com.huntkey.rx.sceo.formula.common.function.FunctionDescriber;
import com.huntkey.rx.sceo.formula.common.function.OptionType;
import com.huntkey.rx.sceo.formula.common.function.ParamOrderType;
import com.huntkey.rx.sceo.formula.common.function.ParamType;
import com.huntkey.rx.sceo.formula.engine.DataProvider;
import com.huntkey.rx.sceo.formula.engine.ExprValue;
import com.huntkey.rx.sceo.formula.engine.exception.FormulaException;
import com.huntkey.rx.sceo.formula.engine.expression.Expression;

/**
 * 比较两个字符串是否完全相同（区分大小写）。完全相同则返回true，否则返回false
 *
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * EXACT(text1: varchar, text2: varchar): boolean
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *
 * @author chenfei on 2017/5/15.
 */
public class EXACT extends TextFunction {

    @Override
    protected void buildParamsDesc(FunctionDescriber describer) {

        StringBuffer buff = new StringBuffer();
        buff.append("比较两个字符串是否完全相同（区分大小写）。完全相同则返回true，否则返回false\n")
            .append("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n")
            .append("EXACT(text1: varchar, text2: varchar): boolean\n")
            .append("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");

        describer.setFunDesc(buff.toString());
        describer.setFunType(FunInOrOutType.BOOLEAN);

        int counter = 1;
        ParamType pt = new ParamType(counter++, ParamOrderType.Loop, OptionType.NonOption)
            .addTypes(FunInOrOutType.VARCHAR);

        describer.addParamTypes(pt);
    }

    @Override
    public ExprValue getValue(DataProvider provider) throws FormulaException {

        checkArgsOnlyCnt(2);

        String text1 = this.getArgument(0).getValue(provider).getString();
        String text2 = this.getArgument(1).getValue(provider).getString();

        if (!isInstanceOfString(text1) || !isInstanceOfString(text2)) {
            throw new FormulaException("The EXACT function supports only string type arguments.");
        }

        if (text1.toString().equals(text2.toString())) {
            return new ExprValue(true);
        }

        return new ExprValue(false);
    }

    @Override
    public void checkArgument(Expression arg) throws FormulaException {

    }
}
