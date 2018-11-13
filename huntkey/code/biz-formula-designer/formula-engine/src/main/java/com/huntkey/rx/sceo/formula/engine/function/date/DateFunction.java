package com.huntkey.rx.sceo.formula.engine.function.date;

import com.huntkey.rx.sceo.formula.common.function.FunctionDescriber;
import com.huntkey.rx.sceo.formula.engine.function.Function;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期类函数超类
 *
 * @author chenfei on 2017/5/17.
 */
abstract public class DateFunction extends Function {
    private final static String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    @Override
    public FunctionDescriber buildFunctionDescriber() {

        FunctionDescriber describer = new FunctionDescriber();
        describer.setFunClassify("date");
        describer.setClassifyDesc("日期函数");
        describer.setFunName(this.getClass().getSimpleName());

        buildParamsDesc(describer);

        return describer;
    }

    public String formatDateToString(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        return sdf.format(date);
    }
}
