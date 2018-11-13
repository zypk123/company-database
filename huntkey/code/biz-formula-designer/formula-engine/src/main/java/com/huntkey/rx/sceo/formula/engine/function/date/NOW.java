package com.huntkey.rx.sceo.formula.engine.function.date;

import com.huntkey.rx.sceo.formula.common.constant.FunInOrOutType;
import com.huntkey.rx.sceo.formula.common.function.FunctionDescriber;
import com.huntkey.rx.sceo.formula.engine.DataProvider;
import com.huntkey.rx.sceo.formula.engine.ExprValue;
import com.huntkey.rx.sceo.formula.engine.exception.FormulaException;

import java.util.Date;

/**
 * 返回当前时间
 *
 * ~~~~~~~~~~~
 * NOW(): date
 * ~~~~~~~~~~~
 * 
 * @author chenfei on 2017/5/16.
 */
public class NOW extends DateFunction {

    @Override
    protected void buildParamsDesc(FunctionDescriber describer) {

        StringBuffer buff = new StringBuffer();
        buff.append("返回当前时间\n")
            .append("~~~~~~~~~~~\n")
            .append("NOW(): date\n")
            .append("~~~~~~~~~~~\n");

        describer.setFunDesc(buff.toString());
        describer.setFunType(FunInOrOutType.DATE);
    }

    @Override
    public ExprValue getValue(DataProvider provider) throws FormulaException {

        checkArgsOnlyCnt(0);

        return new ExprValue(formatDateToString(new Date()));
    }

}
