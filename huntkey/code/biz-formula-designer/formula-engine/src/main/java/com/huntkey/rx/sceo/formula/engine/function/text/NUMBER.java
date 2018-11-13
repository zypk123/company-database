package com.huntkey.rx.sceo.formula.engine.function.text;

import com.huntkey.rx.sceo.formula.common.constant.FunInOrOutType;
import com.huntkey.rx.sceo.formula.common.function.FunctionDescriber;
import com.huntkey.rx.sceo.formula.common.function.OptionType;
import com.huntkey.rx.sceo.formula.common.function.ParamOrderType;
import com.huntkey.rx.sceo.formula.common.function.ParamType;
import com.huntkey.rx.sceo.formula.engine.DataProvider;
import com.huntkey.rx.sceo.formula.engine.ExprValue;
import com.huntkey.rx.sceo.formula.engine.exception.FormulaException;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 返回数值
 *
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~
 * NUMBER(text: varchar): int
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~
 *
 * @author chenfei on 2017/5/15.
 */
public class NUMBER extends TextFunction {

    @Override
    protected void buildParamsDesc(FunctionDescriber describer) {

        StringBuffer buff = new StringBuffer();
        buff.append("返回数值\n")
            .append("~~~~~~~~~~~~~~~~~~~~~~~~~~\n")
            .append("NUMBER(text: varchar): int\n")
            .append("~~~~~~~~~~~~~~~~~~~~~~~~~~\n");

        describer.setFunDesc(buff.toString());
        describer.setFunType(FunInOrOutType.INT);

        int counter = 1;
        ParamType pt = new ParamType(counter++, ParamOrderType.Sequence, OptionType.NonOption)
            .addTypes(FunInOrOutType.VARCHAR);

        describer.addParamTypes(pt);
    }

    @Override
    public ExprValue getValue(DataProvider provider) throws FormulaException {

        checkArgsOnlyCnt(1);

        String numText = this.getArgument(0).getValue(provider).getString();
        if (numText.contains(".") == true) {
            int numDigits = numText.length() - numText.indexOf(".");
            BigDecimal b = new BigDecimal(numText).setScale(numDigits, RoundingMode.HALF_UP);
            return new ExprValue(b.doubleValue());
        } else {
            return new ExprValue(Integer.parseInt(numText));
        }
    }

}
