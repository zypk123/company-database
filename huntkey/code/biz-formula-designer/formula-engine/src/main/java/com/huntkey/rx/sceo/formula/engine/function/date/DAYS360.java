//package com.huntkey.rx.sceo.formula.engine.function.date;
//
//import com.huntkey.rx.sceo.formula.common.function.FunctionDescriber;
//import com.huntkey.rx.sceo.formula.engine.DataProvider;
//import com.huntkey.rx.sceo.formula.engine.ExprValue;
//import com.huntkey.rx.sceo.formula.engine.exception.FormulaException;
//
///**
// * 按照一年 360 天的算法（每个月以 30 天计，一年共计 12 个月），DAYS360 函数返回两个日期间相差的天数，这在一些会计计算中将会用到。 如果财会系统是基于一年 12 个月，每月 30 天，可使用此函数帮助计算支付款项
// * endDate: 必需。结束日期
// * startDate: 必需。起始日期
// * method: 可选。用于指定在计算中是采用美国方法还是欧洲方法。不填或false表示是美国方法，true表示是欧洲方法。
// *
// * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
// * DAYS360(enddate: date, startdate: date): date
// * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
// *
// * Created by chenfei on 2017/5/16.
// */
//public class DAYS360 extends DateFunction {
//
//    @Override
//    protected void buildParamsDesc(FunctionDescriber describer) {
//
//        // TODO
//    }
//
//    @Override
//    public ExprValue getValue(DataProvider provider) throws FormulaException {
//
//        // TODO
//
//        return null;
//    }
//
//}
