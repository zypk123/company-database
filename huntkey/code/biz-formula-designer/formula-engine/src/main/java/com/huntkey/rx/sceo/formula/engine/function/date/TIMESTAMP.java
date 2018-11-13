package com.huntkey.rx.sceo.formula.engine.function.date;

import com.huntkey.rx.sceo.formula.common.constant.FunInOrOutType;
import com.huntkey.rx.sceo.formula.common.function.FunctionDescriber;
import com.huntkey.rx.sceo.formula.common.function.OptionType;
import com.huntkey.rx.sceo.formula.common.function.ParamOrderType;
import com.huntkey.rx.sceo.formula.common.function.ParamType;
import com.huntkey.rx.sceo.formula.engine.DataProvider;
import com.huntkey.rx.sceo.formula.engine.ExprValue;
import com.huntkey.rx.sceo.formula.engine.exception.FormulaException;

import java.util.Date;

/**
 * 将日期对象转换成时间戳
 *
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~
 * TIMESTAMP(date: date): int
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~
 *
 * @author chenfei on 2017/5/16.
 */
public class TIMESTAMP extends DateFunction {

    @Override
    protected void buildParamsDesc(FunctionDescriber describer) {

        StringBuffer buff = new StringBuffer();
        buff.append("将日期对象转换成时间戳\n")
            .append("~~~~~~~~~~~~~~~~~~~~~~~~~~\n")
            .append("TIMESTAMP(date: date): int\n")
            .append("~~~~~~~~~~~~~~~~~~~~~~~~~~\n");

        describer.setFunDesc(buff.toString());
        describer.setFunType(FunInOrOutType.INT);

        int counter = 1;
        ParamType pt = new ParamType(counter++, ParamOrderType.Sequence, OptionType.NonOption)
            .addTypes(FunInOrOutType.DATE);

        describer.addParamTypes(pt);
    }

    @Override
    public ExprValue getValue(DataProvider provider) throws FormulaException {

        checkArgsOnlyCnt(1);

        try {
            Date date = (Date) this.getArgument(0).getValue(provider).getOriginValue();
            return new ExprValue(date.getTime());
        } catch (FormulaException e) {
            throw new FormulaException("The argument must be java.util.Date type.");
        }

    }

}
