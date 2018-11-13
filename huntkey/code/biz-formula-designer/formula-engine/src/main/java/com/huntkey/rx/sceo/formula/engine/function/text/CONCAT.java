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
 * 将多个文本字符串合并成一个文本字符串。示例：
 * CONCAT(A,B,C)，即返回值为ABC
 * 字段或者函数之间，用逗号隔开。如果是字符串，需要用引号包裹起来
 *
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * CONCAT(text1: varchar, [text2: varchar], ...): varchar
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *
 * @author chenfei on 2017/5/15.
 */
public class CONCAT extends TextFunction {


    @Override
    protected void buildParamsDesc(FunctionDescriber describer) {

        StringBuffer buff = new StringBuffer();
        buff.append("将多个文本字符串合并成一个文本字符串。示例：\n")
            .append("CONCAT(A,B,C)，即返回值为ABC\n")
            .append("字段或者函数之间，用逗号隔开。如果是字符串，需要用引号包裹起来\n")
            .append("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n")
            .append("CONCAT(text1: varchar, [text2: varchar], ...): varchar\n")
            .append("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");

        describer.setFunDesc(buff.toString());
        describer.setFunType(FunInOrOutType.VARCHAR);

        int counter = 1;
        ParamType pt = new ParamType(counter++, ParamOrderType.Loop, OptionType.NonOption)
            .addTypes(FunInOrOutType.VARCHAR);

        describer.addParamTypes(pt);
    }

    @Override
    public ExprValue getValue(DataProvider provider) throws FormulaException {

        checkArgsAtLeastCnt(1);

        int count = this.getArgumentCount();
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < count; i++) {
            Object value = this.getArgument(i).getValue(provider).getOriginValue();
            buffer.append(value == null ? "" : value.toString());
        }

        return new ExprValue(buffer.toString());
    }
}
