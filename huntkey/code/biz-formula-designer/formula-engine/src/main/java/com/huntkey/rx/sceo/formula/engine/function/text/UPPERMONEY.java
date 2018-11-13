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
import java.util.HashMap;
import java.util.Map;

/**
 * 返回中文大写金额
 *
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * UPPERMONEY(number: number): varchar
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *
 * @author chenfei on 2017/5/15.
 */
public class UPPERMONEY extends TextFunction {


    @Override
    protected void buildParamsDesc(FunctionDescriber describer) {

        StringBuffer buff = new StringBuffer();
        buff.append("返回中文大写金额\n")
            .append("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n")
            .append("UPPERMONEY(number: number): varchar\n")
            .append("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");

        describer.setFunDesc(buff.toString());
        describer.setFunType(FunInOrOutType.VARCHAR);

        int counter = 1;
        ParamType pt = new ParamType(counter++, ParamOrderType.Sequence, OptionType.NonOption)
            .addTypes(FunInOrOutType.NUMBER);

        describer.addParamTypes(pt);
    }

    @Override
    public ExprValue getValue(DataProvider provider) throws FormulaException {

        checkArgsOnlyCnt(1);

        String money = this.getArgument(0).getValue(provider).getString();

        return new ExprValue(digitUppercase(money));
    }

    /**
     * 处理的最大数字达千万亿位 精确到分
     * @return
     */
    private String digitUppercase(String num) {
        String[] fraction = {"角", "分"};
        String[] digit = {"零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖"};
        /**
         *      仟        佰        拾         ' '
         ' '    $4        $3        $2         $1
         万     $8        $7        $6         $5
         亿     $12       $11       $10        $9
         */
        //把钱数分成段,每四个一段,实际上得到的是一个二维数组
        String[] unit1 = {"", "拾", "佰", "仟"};
        //把钱数分成段,每四个一段,实际上得到的是一个二维数组
        String[] unit2 = {"元", "万", "亿", "万亿"};
        BigDecimal bigDecimal = new BigDecimal(num);
        bigDecimal = bigDecimal.multiply(new BigDecimal(100));
        String strVal = String.valueOf(bigDecimal.toBigInteger());
        //整数部分
        String head = strVal.substring(0, strVal.length() - 2);
        //小数部分
        String end = strVal.substring(strVal.length() - 2);
        String endMoney = "";
        String headMoney = "";
        if ("00".equals(end)) {
            endMoney = "整";
        } else {
            if (!"0".equals(end.substring(0, 1))) {
                endMoney += digit[Integer.valueOf(end.substring(0, 1))] + "角";
            } else if ("0".equals(end.substring(0, 1)) && !"0".equals(end.substring(1, 2))) {
                endMoney += "零";
            }
            if (!"0".equals(end.substring(1, 2))) {
                endMoney += digit[Integer.valueOf(end.substring(1, 2))] + "分";
            }
        }
        char[] chars = head.toCharArray();
        //段位置是否已出现zero
        Map<String, Boolean> map = new HashMap<String, Boolean>();
        //0连续出现标志
        boolean zeroKeepFlag = false;
        int vidxtemp = 0;
        for (int i = 0; i < chars.length; i++) {
            //段内位置  unit1
            int idx = (chars.length - 1 - i) % 4;
            //段位置 unit2
            int vidx = (chars.length - 1 - i) / 4;
            String s = digit[Integer.valueOf(String.valueOf(chars[i]))];
            if (!"零".equals(s)) {
                headMoney += s + unit1[idx] + unit2[vidx];
                zeroKeepFlag = false;
            } else if (i == chars.length - 1 || map.get("zero" + vidx) != null) {
                headMoney += "";
            } else {
                headMoney += s;
                zeroKeepFlag = true;
                //该段位已经出现0；
                map.put("zero" + vidx, true);
            }
            if (vidxtemp != vidx || i == chars.length - 1) {
                headMoney = headMoney.replaceAll(unit2[vidx], "");
                headMoney += unit2[vidx];
            }
            if (zeroKeepFlag && (chars.length - 1 - i) % 4 == 0) {
                headMoney = headMoney.replaceAll("零", "");
            }
        }
        return headMoney + endMoney;
    }
}
