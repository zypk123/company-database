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
 * 将年月日时分秒转换为日期对象
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * DATE(year: int, month: int, day: int, hour: int, minute: int, second: int): date
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * year: 必需。年份
 * month: 必需。月份
 * day: 必需。天数
 * hour: 必须。小时
 * minute: 必需。分钟
 * second: 必需。秒
 *
 * @author chenfei on 2017/5/16.
 */
public class DATE extends DateFunction {

    @Override
    protected void buildParamsDesc(FunctionDescriber describer) {

        StringBuffer buff = new StringBuffer();
        buff.append("将时间戳转换为日期对象\n")
            .append("将年月日时分秒转换为日期对象\n")
            .append("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n")
            .append("DATE(year: int, month: int, day: int, hour: int, minute: int, second: int): date\n")
            .append("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n")
            .append("year: 必需。年份\n")
            .append("month: 必需。月份\n")
            .append("day: 必需。天数\n")
            .append("hour: 必须。小时\n")
            .append("minute: 必需。分钟\n")
            .append("second: 必需。秒");

        describer.setFunDesc(buff.toString());
        describer.setFunType(FunInOrOutType.DATE);

        int counter = 1;
        ParamType pt = new ParamType(counter++, ParamOrderType.Sequence, OptionType.NonOption)
                .addTypes(FunInOrOutType.INT, FunInOrOutType.INT);

        ParamType pt2 = new ParamType(counter++, ParamOrderType.Sequence, OptionType.NonOption)
                .addTypes(FunInOrOutType.INT);

        ParamType pt3 = new ParamType(counter++, ParamOrderType.Sequence, OptionType.NonOption)
                .addTypes(FunInOrOutType.INT);

        ParamType pt4 = new ParamType(counter++, ParamOrderType.Sequence, OptionType.NonOption)
                .addTypes(FunInOrOutType.INT);

        ParamType pt5 = new ParamType(counter++, ParamOrderType.Sequence, OptionType.NonOption)
                .addTypes(FunInOrOutType.INT);

        ParamType pt6 = new ParamType(counter++, ParamOrderType.Sequence, OptionType.NonOption)
                .addTypes(FunInOrOutType.INT);

        describer.addParamTypes(pt, pt2, pt3, pt4, pt5, pt6);
    }

    @Override
    public ExprValue getValue(DataProvider provider) throws FormulaException {

        int[] category = new int[]{1, 3, 6};
        checkArgsCategory(category);

        if (this.getArgumentCount() == 1) {
            long timestamp = this.getArgument(0).getValue(provider).getLong();
            return new ExprValue(formatDateToString(new Date(timestamp)));
        }

        if (this.getArgumentCount() == 3) {
            int year = this.getArgument(0).getValue(provider).getInt();
            int month = this.getArgument(1).getValue(provider).getInt();
            int day = this.getArgument(2).getValue(provider).getInt();

            Calendar calendar = Calendar.getInstance();
            calendar.set(year, month - 1, day);

            return new ExprValue(formatDateToString(calendar.getTime()));
        }

        if (this.getArgumentCount() == 6) {
            int year = this.getArgument(0).getValue(provider).getInt();
            int month = this.getArgument(1).getValue(provider).getInt();
            int day = this.getArgument(2).getValue(provider).getInt();
            int hour = this.getArgument(3).getValue(provider).getInt();
            int minute = this.getArgument(4).getValue(provider).getInt();
            int second = this.getArgument(5).getValue(provider).getInt();

            Calendar calendar = Calendar.getInstance();
            calendar.set(year, month - 1, day, hour, minute, second);

            return new ExprValue(formatDateToString(calendar.getTime()));
        }

        throw new FormulaException("The arguments was not valid, please check it.");
    }
}
