package com.huntkey.rx.sceo.formula.engine.function.date;

import com.huntkey.rx.sceo.formula.common.constant.FunInOrOutType;
import com.huntkey.rx.sceo.formula.common.function.FunctionDescriber;
import com.huntkey.rx.sceo.formula.common.function.OptionType;
import com.huntkey.rx.sceo.formula.common.function.ParamOrderType;
import com.huntkey.rx.sceo.formula.common.function.ParamType;
import com.huntkey.rx.sceo.formula.engine.DataProvider;
import com.huntkey.rx.sceo.formula.engine.ExprValue;
import com.huntkey.rx.sceo.formula.engine.exception.FormulaException;

import java.util.Calendar;
import java.util.Date;

/**
 * 返回某日期的分钟数
 *
 * ~~~~~~~~~~~~~~~~~~~~~~~
 * MINUTE(date: date): int
 * ~~~~~~~~~~~~~~~~~~~~~~~
 * 
 * @author chenfei on 2017/5/16.
 */
public class MINUTE extends DateFunction {

    @Override
    protected void buildParamsDesc(FunctionDescriber describer) {

        StringBuffer buff = new StringBuffer();
        buff.append("返回某日期的分钟数\n")
            .append("~~~~~~~~~~~~~~~~~~~~~~~\n")
            .append("MINUTE(date: date): int\n")
            .append("~~~~~~~~~~~~~~~~~~~~~~~\n");

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

        Date date = (Date) this.getArgument(0).getValue(provider).getOriginValue();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        int minute = calendar.get(Calendar.MINUTE);

        return new ExprValue(minute);
    }

}
