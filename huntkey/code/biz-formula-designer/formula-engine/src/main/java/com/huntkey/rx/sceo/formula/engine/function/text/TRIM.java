package com.huntkey.rx.sceo.formula.engine.function.text;

import com.huntkey.rx.sceo.formula.common.constant.FunInOrOutType;
import com.huntkey.rx.sceo.formula.common.function.FunctionDescriber;
import com.huntkey.rx.sceo.formula.common.function.OptionType;
import com.huntkey.rx.sceo.formula.common.function.ParamOrderType;
import com.huntkey.rx.sceo.formula.common.function.ParamType;
import com.huntkey.rx.sceo.formula.common.util.StringUtils;
import com.huntkey.rx.sceo.formula.engine.DataProvider;
import com.huntkey.rx.sceo.formula.engine.ExprValue;
import com.huntkey.rx.sceo.formula.engine.exception.FormulaException;

/**
 * 删除字符串首尾的空格，但是会保留字符串内部作为词与词之间分隔的空格
 *
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * TRIM(text: varchar): varchar
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *
 * @author chenfei on 2017/5/15.
 */
public class TRIM extends TextFunction {

    @Override
    protected void buildParamsDesc(FunctionDescriber describer) {

        StringBuffer buff = new StringBuffer();
        buff.append("删除字符串首尾的空格，但是会保留字符串内部作为词与词之间分隔的空格\n")
            .append("~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n")
            .append("TRIM(text: varchar): varchar\n")
            .append("~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");

        describer.setFunDesc(buff.toString());
        describer.setFunType(FunInOrOutType.VARCHAR);

        int counter = 1;
        ParamType pt = new ParamType(counter++, ParamOrderType.Sequence, OptionType.NonOption)
                .addTypes(FunInOrOutType.VARCHAR);

        describer.addParamTypes(pt);
    }

    @Override
    public ExprValue getValue(DataProvider provider) throws FormulaException {

        checkArgsOnlyCnt(1);

        String text = this.getArgument(0).getValue(provider).getString();

        return new ExprValue(StringUtils.trim(text));
    }
}
