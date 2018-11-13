package com.huntkey.rx.commons.utils.money;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class MoneyUtil {
    private final static RoundingMode ROUNDING_MODE = RoundingMode.HALF_UP;

    public static enum SCALE {
        YUAN("元", 100), TEN_YUAN("十元", 1000);
        private String desc;
        private long value;

        private SCALE(String desc, int value) {
            this.desc = desc;
            this.value = value;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public long getValue() {
            return value;
        }

        public void setValue(long value) {
            this.value = value;
        }
    }

    public static String cent2Yuan(BigDecimal amount) {
        return amount.divide(new BigDecimal(100), 2, ROUNDING_MODE).toPlainString();
    }

    public static String cent2Yuan(BigDecimal amount, int scale, RoundingMode roundingMode) {
        return amount.divide(new BigDecimal(100), scale, roundingMode).toPlainString();
    }

    /**
     * 分转为元，只保留整数部分
     *
     * @param amount
     * @return
     */
    public static String cent2YuanFloor(String amount) {
        return cent2Yuan(new BigDecimal(amount), 0, RoundingMode.FLOOR);
    }

    public static String cent2YuanCeiling(String amount) {
        return cent2Yuan(new BigDecimal(amount), 0, RoundingMode.CEILING);
    }

    public static String cent2Yuan(String amount) {
        return cent2Yuan(new BigDecimal(amount));
    }

    public static String cent2Yuan(long amount) {
        return cent2Yuan(new BigDecimal(amount));
    }

    public static String cent2Yuan(double amount) {
        return cent2Yuan(new BigDecimal(amount));
    }

    public static String cent2YuanFloor(double amount) {
        return cent2Yuan(new BigDecimal(amount), 0, RoundingMode.FLOOR);
    }

    /**
     * 分转为元，只保留整数部分,小数部分全舍
     *
     * @param amount
     * @return
     */
    public static String cent2YuanFloor(long amount) {
        return cent2YuanFloor(String.valueOf(amount));
    }

    public static String cent2YuanCeiling(long amount) {
        return cent2YuanCeiling(String.valueOf(amount));
    }

    public static long yuan2Cent(String amount) {
        return yuan2Cent(new BigDecimal(amount));
    }

    public static long yuan2Cent(String amount, Long nullDefault) {
        if (StringUtils.isNotBlank(amount)) {
            return yuan2Cent(amount);
        } else {
            return nullDefault;
        }
    }

    public static String yuan2Cent(double amount) {
        return String.valueOf(yuan2Cent(new BigDecimal(amount)));
    }

    public static long yuan2Cent(BigDecimal amount) {
        if (amount == null) {
            return 0L;
        }
        return amount.multiply(new BigDecimal(100), new MathContext(16, ROUNDING_MODE))
                .setScale(0, BigDecimal.ROUND_HALF_UP).longValue();
    }

    /**
     * 将以分为单位的金额入到指定的刻度
     *
     * @param centAmt
     * @param scale
     * @return 转换之后的以分为单位的金额
     */
    public static long roundUpCent(long centAmt, MoneyUtil.SCALE scale) {
        if (scale == null)
            return centAmt;
        BigDecimal origAmount = new BigDecimal(centAmt);
        BigDecimal scaleVal = new BigDecimal(scale.getValue());
        return origAmount.divide(scaleVal, 0, RoundingMode.UP).multiply(scaleVal).longValue();
    }

    /**
     * 将以分为单位的金额舍到指定的刻度
     *
     * @param centAmt
     * @param scale
     * @return 转换之后的以分为单位的金额
     */
    public static long roundDownCent(long centAmt, MoneyUtil.SCALE scale) {
        if (scale == null)
            return centAmt;
        BigDecimal origAmount = new BigDecimal(centAmt);
        BigDecimal scaleVal = new BigDecimal(scale.getValue());
        return origAmount.divide(scaleVal, 0, RoundingMode.DOWN).multiply(scaleVal).longValue();
    }

    /**
     * 将以任意币种元为单位的金额换算成人民币,小数部分保留到分，向上进位
     */
    public static double convertRateUp(double usdAmt, double rate) {
        BigDecimal usdAmount = new BigDecimal(usdAmt);
        BigDecimal rateAmount = new BigDecimal(rate);
        BigDecimal centAmt = usdAmount.multiply(rateAmount)
                .multiply(new BigDecimal(100), new MathContext(16, ROUNDING_MODE)).setScale(0, BigDecimal.ROUND_UP);
        return Double.parseDouble(cent2Yuan(centAmt, 2, RoundingMode.UP));
    }

    /**
     * 将以任意币种元为单位的金额换算成人民币,小数部分保留到分，向下舍位
     */
    public static double convertRateDown(double usdAmt, double rate) {
        BigDecimal usdAmount = new BigDecimal(usdAmt);
        BigDecimal rateAmount = new BigDecimal(rate);
        BigDecimal centAmt = usdAmount.multiply(rateAmount)
                .multiply(new BigDecimal(100), new MathContext(16, ROUNDING_MODE)).setScale(0, BigDecimal.ROUND_DOWN);
        return Double.parseDouble(cent2Yuan(centAmt, 2, RoundingMode.DOWN));
    }

    /**
     * 将以任意币种元为单位的金额换算成人民币,小数部分保留到分，四舍五入进舍位
     */
    public static double convertRateHalfUp(double usdAmt, double rate) {
        BigDecimal usdAmount = new BigDecimal(usdAmt);
        BigDecimal rateAmount = new BigDecimal(rate);
        BigDecimal centAmt = usdAmount.multiply(rateAmount)
                .multiply(new BigDecimal(100), new MathContext(16, ROUNDING_MODE))
                .setScale(0, BigDecimal.ROUND_HALF_DOWN);
        return Double.parseDouble(cent2Yuan(centAmt, 2, RoundingMode.HALF_UP));
    }

    /**
     * 计算金额分相加，小数点以后部分舍去
     * <p>
     * 6.1 + 6.2 = 12.3 --> 12
     * <p>
     * 6.2 + 6.5 = 12.7 --> 12
     *
     * @param baseAmount
     * @param plusAmount
     * @return
     */
    public static Long centPlusDown(String baseAmount, String plusAmount) {
        BigDecimal baseDec = new BigDecimal(baseAmount);
        BigDecimal plusDec = new BigDecimal(plusAmount);
        return baseDec.add(plusDec).longValue();
    }

    /**
     * 计算金额分相加，四舍五入到分
     * <p>
     * 6.1 + 6.5 = 12.6 --> 13
     *
     * @param baseAmount
     * @param plusAmount
     * @return
     */
    public static Long centPlusUp(String baseAmount, String plusAmount) {
        BigDecimal baseDec = new BigDecimal(baseAmount);
        BigDecimal plusDec = new BigDecimal(plusAmount);
        return baseDec.add(plusDec).setScale(0, BigDecimal.ROUND_HALF_UP).longValue();
    }

    /**
     * 浮点数的减法
     *
     * @param baseRate
     * @param minusRate
     * @return
     */
    public static double minusDouble(double baseRate, double minusRate) {
        BigDecimal baseDec = new BigDecimal(String.valueOf(baseRate));
        BigDecimal minusDec = new BigDecimal(String.valueOf(minusRate));
        return baseDec.subtract(minusDec).doubleValue();
    }

    /**
     * 根据底价和返点计算佣金
     *
     * @param amount
     * @param rate
     * @return
     */
    public static String multiply(double amount, double rate) {
        BigDecimal amountDec = new BigDecimal(String.valueOf(amount));
        BigDecimal rateDec = new BigDecimal(String.valueOf(rate));
        return amountDec.multiply(rateDec).toString();
    }

    /**
     * 根据两个返点计算佣金
     *
     * @param amount
     * @param rateA
     * @param rateB
     * @return
     */
    public static String multiply(double amount, double rateA, double rateB) {
        BigDecimal amountDec = new BigDecimal(String.valueOf(amount));
        BigDecimal rateADec = new BigDecimal(String.valueOf(rateA));
        BigDecimal rateBDec = new BigDecimal(String.valueOf(rateB));
        return amountDec.multiply(rateADec).multiply(rateBDec).toString();
    }

    /**
     * 去掉金额中的千隔符
     */
    public static String parseMoney(String money) {
        return money.replaceAll("[^\\d\\.-]", "");
    }

    /**
     * 给金额加上千隔符
     */
    public static String formatMoney(String money) {
        String s = parseMoney(money);
        String l = StringUtils.reverse(s.split("\\.")[0]);
        String r = s.split("\\.").length > 1 ? s.split("\\.")[1] : "00";
        StringBuilder t = new StringBuilder();
        for (int i = 0; i < l.length(); i++) {
            t.append(l.charAt(i));
            if ((i + 1) % 3 == 0 && (i + 1) != l.length() && l.charAt(i + 1) != '-') {
                t.append(",");
            }
        }
        return t.reverse() + "." + r;
    }

    public static String formatMoney(Long money) {
        return formatMoney(cent2Yuan(money));
    }
}
