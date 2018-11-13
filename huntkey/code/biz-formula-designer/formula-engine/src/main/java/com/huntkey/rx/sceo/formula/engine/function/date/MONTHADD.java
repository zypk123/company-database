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
 * 将指定日期加/减指定月数
 *
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * MONTHADD(date: date, deltaMonths: int): date
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *
 * @author chenfei on 2017/5/16.
 */
public class MONTHADD extends DateFunction {

    @Override
    protected void buildParamsDesc(FunctionDescriber describer) {

        StringBuffer buff = new StringBuffer();
        buff.append("将指定日期加/减指定月数\n")
            .append("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n")
            .append("MONTHADD(date: date, deltaMonths: int): date\n")
            .append("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");

        describer.setFunDesc(buff.toString());
        describer.setFunType(FunInOrOutType.DATE);

        int counter = 1;
        ParamType pt = new ParamType(counter++, ParamOrderType.Sequence, OptionType.NonOption)
            .addTypes(FunInOrOutType.DATE);
        ParamType pt2 = new ParamType(counter++, ParamOrderType.Sequence, OptionType.NonOption)
            .addTypes(FunInOrOutType.INT);

        describer.addParamTypes(pt, pt2);
    }

    @Override
    public ExprValue getValue(DataProvider provider) throws FormulaException {


        checkArgsOnlyCnt(2);

        Date date = (Date) this.getArgument(0).getValue(provider).getOriginValue();
        int deltaMonths = this.getArgument(1).getValue(provider).getInt();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, deltaMonths);

        return new ExprValue(formatDateToString(calendar.getTime()));
    }

}
