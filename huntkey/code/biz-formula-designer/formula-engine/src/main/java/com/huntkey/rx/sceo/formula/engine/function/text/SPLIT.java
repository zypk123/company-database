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
 * 将文本按指定字符串分割成数组，并取值
 * <p>
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * SPLIT(text: varchar, textSeparator: varchar, index: int): varchar
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * <p>
 * @author chenfei on 2017/5/15.
 */
public class SPLIT extends TextFunction {

    @Override
    protected void buildParamsDesc(FunctionDescriber describer) {

        StringBuffer buff = new StringBuffer();
        buff.append("将文本按指定字符串分割成数组，并取值。分割符支持 ^ , * , | , $ \n")
                .append("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n")
                .append("SPLIT(text: varchar, textSeparator: varchar, index: int): varchar\n")
                .append("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");

        describer.setFunDesc(buff.toString());
        describer.setFunType(FunInOrOutType.VARCHAR);

        int counter = 1;
        ParamType pt = new ParamType(counter++, ParamOrderType.Sequence, OptionType.NonOption)
                .addTypes(FunInOrOutType.VARCHAR);
        ParamType pt2 = new ParamType(counter++, ParamOrderType.Sequence, OptionType.NonOption)
                .addTypes(FunInOrOutType.VARCHAR);
        ParamType pt3 = new ParamType(counter++, ParamOrderType.Sequence, OptionType.Option)
                .addTypes(FunInOrOutType.INT);

        describer.addParamTypes(pt, pt2, pt3);
    }

    @Override
    public ExprValue getValue(DataProvider provider) throws FormulaException {

        checkArgsOnlyCnt(3);

        String text = this.getArgument(0).getValue(provider).getString();
        String textSeparator = this.getArgument(1).getValue(provider).getString();
        if ("^".equals(textSeparator)) {
            textSeparator = "\\^";
        }
        if ("*".equals(textSeparator)) {
            textSeparator = "\\*";
        }
        if ("|".equals(textSeparator)) {
            textSeparator = "\\|";
        }
        if ("$".equals(textSeparator)) {
            textSeparator = "\\$";
        }
        int index = this.getArgument(2).getValue(provider).getInt();
        checkArgPositive("index", index);
        String[] arr = text.split(textSeparator);

        checkArgsLarge("index", index, "arr.length", arr.length);

        return new ExprValue(arr[index - 1]);
    }
}
