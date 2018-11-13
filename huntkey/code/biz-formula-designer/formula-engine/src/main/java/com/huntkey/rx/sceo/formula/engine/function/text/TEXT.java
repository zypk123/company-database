package com.huntkey.rx.sceo.formula.engine.function.text;

import com.huntkey.rx.sceo.formula.common.constant.FunInOrOutType;
import com.huntkey.rx.sceo.formula.common.function.FunctionDescriber;
import com.huntkey.rx.sceo.formula.common.function.OptionType;
import com.huntkey.rx.sceo.formula.common.function.ParamOrderType;
import com.huntkey.rx.sceo.formula.common.function.ParamType;
import com.huntkey.rx.sceo.formula.engine.DataProvider;
import com.huntkey.rx.sceo.formula.engine.ExprValue;
import com.huntkey.rx.sceo.formula.engine.exception.FormulaException;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 根据指定格式格式化参数
 *
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * TEXT(num: number, textFormat: varchar): varchar
 * TEXT(date: date, textFormat: varchar): varchar
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *
 * @author chenfei on 2017/5/15.
 */
public class TEXT extends TextFunction {

    @Override
    protected void buildParamsDesc(FunctionDescriber describer) {

        StringBuffer buff = new StringBuffer();
        buff.append("将参数转化成指定格式的文本\n")
            .append("例：TEXT(23.1578,000.00)显示为023.16,textFormat含义0为占位补位符,％：百分比\n")
            .append(" TEXT(DATE(2017,10,10,10,35,33),yyyy-MM-dd HH:mm:ss E),显示为2017-10-10 10:35:33 星期二")
            .append("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n")
            .append("TEXT(num: number, textFormat: varchar): varchar\n")
            .append("TEXT(date: date, textFormat: varchar): varchar\n")
            .append("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");

        describer.setFunDesc(buff.toString());
        describer.setFunType(FunInOrOutType.VARCHAR);

        int counter = 1;
        ParamType pt = new ParamType(counter++, ParamOrderType.Sequence, OptionType.NonOption)
                .addTypes(FunInOrOutType.NUMBER, FunInOrOutType.DATE);
        ParamType pt2 = new ParamType(counter++, ParamOrderType.Sequence, OptionType.NonOption)
                .addTypes(FunInOrOutType.VARCHAR);
        describer.addParamTypes(pt, pt2);
    }

    @Override
    public ExprValue getValue(DataProvider provider) throws FormulaException {

        checkArgsOnlyCnt(2);

        Object obj = this.getArgument(0).getValue(provider).getOriginValue();
        String textFormat = this.getArgument(1).getValue(provider).getString();
        if (obj instanceof Date) {
            SimpleDateFormat dateFormat = new SimpleDateFormat(textFormat);
            Date date = (Date) obj;
            return new ExprValue(dateFormat.format(date));
        } else {
            DecimalFormat decimalFormat = new DecimalFormat(textFormat);
            BigDecimal b = new BigDecimal(obj.toString());
            return new ExprValue(decimalFormat.format(b));
        }
    }

}
