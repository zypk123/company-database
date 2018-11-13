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
 * 返回特定日期的周数。例如，包含1月1里的周为该年的第1周，其编号为第1周
 *
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * WEEKNUM(date: date, [returnType: int]): int
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *
 * data: 必需。指定日期。代表一周中的日期。应使用DATE函数输入日期，或者将日期作为其他公式或函数的结果输入
 * returnType: 可选。一数字，确定星期从周几开始。默认值为1
 *
 * @author chenfei on 2017/5/16.
 */
public class WEEKNUM extends DateFunction {

    @Override
    protected void buildParamsDesc(FunctionDescriber describer) {

        StringBuffer buff = new StringBuffer();
        buff.append("返回特定日期的周数。例如，包含1月1日里的周为该年的第1周，其编号为第1周\n")
            .append("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n")
            .append("WEEKNUM(date: date, [returnType: int]): int\n")
            .append("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n")
            .append("data: 必需。指定日期。代表一周中的日期。应使用DATE函数输入日期，或者将日期作为其他公式或函数的结果输入\n")
            .append("returnType: 可选。一数字，确定星期从周几开始。默认值为1\n");

        describer.setFunDesc(buff.toString());
        describer.setFunType(FunInOrOutType.INT);

        int counter = 1;
        ParamType pt = new ParamType(counter++, ParamOrderType.Sequence, OptionType.NonOption)
            .addTypes(FunInOrOutType.DATE);

        ParamType pt2 = new ParamType(counter++, ParamOrderType.Sequence, OptionType.Option)
            .addTypes(FunInOrOutType.INT);

        describer.addParamTypes(pt, pt2);
    }

    @Override
    public ExprValue getValue(DataProvider provider) throws FormulaException {

        int[] category = new int[]{1, 2};
        checkArgsCategory(category);

        Date date = (Date) this.getArgument(0).getValue(provider).getOriginValue();
        int returnType = 1;

        if (this.getArgumentCount() == 2) {
            returnType = this.getArgument(1).getValue(provider).getInt();
        }

        if (returnType > 7) {
            throw new FormulaException("please choose the number from [1-7] which represent MONDAY to SUNDAY.");
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.setFirstDayOfWeek(wrap(returnType));

        int week = calendar.get(Calendar.WEEK_OF_YEAR);

        return new ExprValue(week);
    }

    private int wrap(int returnType) {

        switch (returnType) {
            case 1:
                return Calendar.MONDAY;
            case 2:
                return Calendar.TUESDAY;
            case 3:
                return Calendar.WEDNESDAY;
            case 4:
                return Calendar.THURSDAY;
            case 5:
                return Calendar.FRIDAY;
            case 6:
                return Calendar.SATURDAY;
            case 7:
                return Calendar.SUNDAY;
            default:
                throw new FormulaException("Can not supported return type: " + returnType);
        }
    }

}
