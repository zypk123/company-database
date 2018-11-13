package com.huntkey.rx.sceo.formula.engine.function.date;

import com.huntkey.rx.sceo.formula.common.constant.FunInOrOutType;
import com.huntkey.rx.sceo.formula.common.function.FunctionDescriber;
import com.huntkey.rx.sceo.formula.common.function.OptionType;
import com.huntkey.rx.sceo.formula.common.function.ParamOrderType;
import com.huntkey.rx.sceo.formula.common.function.ParamType;
import com.huntkey.rx.sceo.formula.engine.DataProvider;
import com.huntkey.rx.sceo.formula.engine.ExprValue;
import com.huntkey.rx.sceo.formula.engine.exception.FormulaException;

/**
 * 将日期对象转换成时间单位
 *
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * TIME(hour: int, minute: int, second: int): decimal
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *
 * 返回特定时间的十进制数字。时间值为日期值的一部分，并用十进制数表示（例如 12:00 PM 可表示为 0.5，因为此时是一天的一半）
 * hour: 必需。 0（零）到 32767 之间的数字，代表小时。 任何大于 23 的值都会除以 24，余数将作为小时值。 例如，TIME(27,0,0) = TIME(3,0,0) = .125
 * minute: 必需。 0 到 32767 之间的数字，代表分钟。 任何大于 59 的值将转换为小时和分钟。 例如，TIME(0,750,0) = TIME(12,30,0) = .520833
 * second: 必需。 0 到 32767 之间的数字，代表秒。 任何大于 59 的值将转换为小时、分钟和秒。 例如，TIME(0,0,2000) = TIME(0,33,22) = .023148
 * 
 * @author chenfei on 2017/5/16.
 */
public class TIME extends DateFunction {

    @Override
    protected void buildParamsDesc(FunctionDescriber describer) {

        StringBuffer buff = new StringBuffer();
        buff.append("将日期对象转换成时间单位\n")
            .append("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n")
            .append("TIME(hour: int, minute: int, second: int): decimal\n")
            .append("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n")
            .append("\n返回特定时间的十进制数字。时间值为日期值的一部分，并用十进制数表示（例如 12:00 PM 可表示为 0.5，因为此时是一天的一半）\n")
            .append("hour: 必需。 0（零）到 32767 之间的数字，代表小时。 任何大于 23 的值都会除以 24，余数将作为小时值。 例如，TIME(27,0,0) = TIME(3,0,0) = .125\n")
            .append("minute: 必需。 0 到 32767 之间的数字，代表分钟。 任何大于 59 的值将转换为小时和分钟。 例如，TIME(0,750,0) = TIME(12,30,0) = .520833\n")
            .append("second: 必需。 0 到 32767 之间的数字，代表秒。 任何大于 59 的值将转换为小时、分钟和秒。 例如，TIME(0,0,2000) = TIME(0,33,22) = .023148\n");

        describer.setFunDesc(buff.toString());
        describer.setFunType(FunInOrOutType.DECIMAL);

        int counter = 1;
        ParamType pt = new ParamType(counter++, ParamOrderType.Sequence, OptionType.NonOption)
            .addTypes(FunInOrOutType.INT);
        ParamType pt2 = new ParamType(counter++, ParamOrderType.Sequence, OptionType.NonOption)
            .addTypes(FunInOrOutType.INT);
        ParamType pt3 = new ParamType(counter++, ParamOrderType.Sequence, OptionType.NonOption)
            .addTypes(FunInOrOutType.INT);

        describer.addParamTypes(pt, pt2, pt3);
    }

    private double secondsOfDay = 86400d;
    private double secondsOfHour = 3600d;
    private double secendsOfMinute = 60d;

    @Override
    public ExprValue getValue(DataProvider provider) throws FormulaException {

        checkArgsOnlyCnt(3);

        long hour = this.getArgument(0).getValue(provider).getLong();
        long minute = this.getArgument(1).getValue(provider).getLong();
        long second = this.getArgument(2).getValue(provider).getLong();
        if(hour<0||hour>32767){
            return new ExprValue("参数1-hour: 必需。 0到 32767 之间的数字，代表小时。");
        }
        if(minute < 0||minute > 32767){
            return new ExprValue("参数2-minute: 必需。 0 到 32767 之间的数字，代表分钟。");
        }
        if(second < 0||second > 32767){
            return new ExprValue("参数3-second: 必需。 0 到 32767 之间的数字，代表秒。");
        }
        if(second > 59){
            minute = minute +second/60;
            second = second % 60 ;
        }
        if(minute>59){
            hour = hour + minute/60;
            minute = minute % 60;
        }
        if(hour > 23){
            hour = hour % 24;
        }
        double proportion = (secondsOfHour * hour + secendsOfMinute * minute + second) / secondsOfDay;

        return new ExprValue(proportion);
    }

}
