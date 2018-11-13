package com.huntkey.rx.sceo.formula.engine.function.date;

import com.huntkey.rx.sceo.formula.common.constant.FunInOrOutType;
import com.huntkey.rx.sceo.formula.common.function.FunctionDescriber;
import com.huntkey.rx.sceo.formula.common.function.OptionType;
import com.huntkey.rx.sceo.formula.common.function.ParamOrderType;
import com.huntkey.rx.sceo.formula.common.function.ParamType;
import com.huntkey.rx.sceo.formula.engine.DataProvider;
import com.huntkey.rx.sceo.formula.engine.ExprValue;
import com.huntkey.rx.sceo.formula.engine.exception.FormulaException;

import java.sql.Timestamp;
import java.util.Date;

/**
 * 将年月日时分秒转换为日期对象
 * ~~~~~~~~~~~~~~~~~~~~~~~
 * TODATE(date: any): date
 * ~~~~~~~~~~~~~~~~~~~~~~~
 * date: 日期字符串或者日期对象
 * 日期字符串格式要求：yyyy-mm-dd
 *
 * @author chenfei on 2017/8/23.
 */
public class TODATE extends DateFunction {

    @Override
    protected void buildParamsDesc(FunctionDescriber describer) {

        StringBuffer buff = new StringBuffer();
        buff.append("转换为日期对象\n")
            .append("~~~~~~~~~~~~~~~~~~~~~~~\n")
            .append("TODATE(date: any): date\n")
            .append("~~~~~~~~~~~~~~~~~~~~~~~\n")
            .append("date: 日期字符串或者日期对象\n")
            .append("日期字符串格式要求：yyyy-mm-dd\n");

        describer.setFunDesc(buff.toString());
        describer.setFunType(FunInOrOutType.DATE);

        int counter = 1;
        ParamType pt = new ParamType(counter++, ParamOrderType.Sequence, OptionType.NonOption)
                .addTypes(FunInOrOutType.ANY);

        describer.addParamTypes(pt);
    }

    @Override
    public ExprValue getValue(DataProvider provider) throws FormulaException {

        Object value = this.getArgument(0).getValue(provider).getOriginValue();

        if ("Date".equals(value.getClass().getSimpleName())) {
            Date date = (Date) value;
            return new ExprValue(formatDateToString(date));
        } else {
            Timestamp timestamp = Timestamp.valueOf(value.toString() + " 00:00:00");
            Date date = new Date(timestamp.getTime());
            return new ExprValue(formatDateToString(date));
        }
    }
}
