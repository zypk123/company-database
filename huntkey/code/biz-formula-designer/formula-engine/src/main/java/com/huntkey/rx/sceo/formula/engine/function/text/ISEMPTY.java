package com.huntkey.rx.sceo.formula.engine.function.text;

import com.huntkey.rx.sceo.formula.common.constant.FunInOrOutType;
import com.huntkey.rx.sceo.formula.common.function.FunctionDescriber;
import com.huntkey.rx.sceo.formula.common.function.OptionType;
import com.huntkey.rx.sceo.formula.common.function.ParamOrderType;
import com.huntkey.rx.sceo.formula.common.function.ParamType;
import com.huntkey.rx.sceo.formula.engine.DataProvider;
import com.huntkey.rx.sceo.formula.engine.ExprValue;
import com.huntkey.rx.sceo.formula.engine.exception.FormulaException;

/**
 * 判断值是否为空字符串、空对象或者空数组
 *
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * ISEMPTY(text: any): boolean
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *
 * @author chenfei on 2017/5/15.
 */
public class ISEMPTY extends TextFunction {

    @Override
    protected void buildParamsDesc(FunctionDescriber describer) {

        StringBuffer buff = new StringBuffer();
        buff.append("判断值是否为空字符串、空对象或者空数组\n")
            .append("~~~~~~~~~~~~~~~~~~~~~~~~~~~\n")
            .append("ISEMPTY(text: any): boolean\n")
            .append("~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");

        describer.setFunDesc(buff.toString());
        describer.setFunType(FunInOrOutType.BOOLEAN);

        int counter = 1;
        ParamType pt = new ParamType(counter++, ParamOrderType.Sequence, OptionType.NonOption)
            .addTypes(FunInOrOutType.VARCHAR);

        describer.addParamTypes(pt);
    }

    @Override
    public ExprValue getValue(DataProvider provider) throws FormulaException {

        checkArgsOnlyCnt(1);

        Object obj = this.getArgument(0).getValue(provider);

        if (null == obj) {
            return new ExprValue(true);
        }

        obj = this.getArgument(0).getValue(provider).getOriginValue();

        // 判断空数组
        if (obj.getClass().isArray()) {
            return new ExprValue(checkArrayNull(obj));
        }

        // 判断空串
        if (obj.toString().length() == 0) {
            return new ExprValue(true);
        }

        return new ExprValue(false);
    }

    private boolean checkArrayNull(Object object) {
        String className = object.getClass().getName();

        if (className.startsWith("[I")) {
            return false;
        } else if (className.startsWith("[I")) {
            return false;
        } else if (className.startsWith("[B")) {
            return false;
        } else if (className.startsWith("[S")) {
            return false;
        } else if (className.startsWith("[J")) {
            return false;
        } else if (className.startsWith("[C")) {
            return false;
        } else if (className.startsWith("[F")) {
            return false;
        } else if (className.startsWith("[D")) {
            return false;
        } else {
            return checkObjectArrayNull((Object[]) object);
        }

    }

    /**
     * 对象数组的判空有所不同
     * 需要检查每一个引用，如果都为null，则为为空
     *
     * @param arr
     * @return
     */
    private boolean checkObjectArrayNull(Object[] arr) {
        if (null == arr) {
            return true;
        } else {
            for (Object o : arr) {
                if (null != o) {
                    return false;
                }
            }
            return true;
        }
    }

}
