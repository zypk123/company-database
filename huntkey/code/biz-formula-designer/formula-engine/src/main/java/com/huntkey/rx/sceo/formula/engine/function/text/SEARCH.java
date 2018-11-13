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
 * 计算文本findText中被查询文本withinText首次出现的位置，startNum为开始查询的位置
 *
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * SEARCH(findText: varchar, withinText: varchar, [startNum: int]): int
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *
 * @author chenfei on 2017/5/15.
 */
public class SEARCH extends TextFunction {

    @Override
    protected void buildParamsDesc(FunctionDescriber describer) {

        StringBuffer buff = new StringBuffer();
        buff.append("计算文本findText中被查询文本withinText首次出现的位置，startNum为开始查询的位置，查询结果-1表示未包含\n")
            .append("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n")
            .append("SEARCH(findText: varchar, withinText: varchar, [startNum: int]): int\n")
            .append("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");

        describer.setFunDesc(buff.toString());
        describer.setFunType(FunInOrOutType.INT);

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

        int[] category = new int[]{2, 3};
        checkArgsCategory(category);

        String text = this.getArgument(0).getValue(provider).getString();
        String withInText = this.getArgument(1).getValue(provider).getString();

        int startNum = 0;

        if (this.getArgumentCount() == 3) {
            startNum = this.getArgument(2).getValue(provider).getInt();
            checkArgPositive("startNum", startNum);
        }
        int index = text.indexOf(withInText, startNum - 1);

        return new ExprValue(index == -1 ? index : index + 1);
    }
}
