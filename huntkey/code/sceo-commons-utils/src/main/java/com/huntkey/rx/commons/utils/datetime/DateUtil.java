package com.huntkey.rx.commons.utils.datetime;

import com.huntkey.rx.commons.utils.string.StringUtil;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    private final static ThreadLocal<SimpleDateFormat> local = new ThreadLocal<SimpleDateFormat>();
    private final static String formatStr = "yyyy-MM-dd";

    /**
     * 将日期转换成字符串  yyyy-MM-dd HH:mm:ss
     *
     * @param date
     * @return String
     */
    public static String formatDate(Date date) {
        return getDataFormat().format(date);
    }

    /**
     * 将字符串转换成日期类型    yyyy-MM-dd HH:mm:ss
     * 字符串为空返回null。
     * 转换出异常则返回当前时间
     *
     * @param dataStr
     * @return Date
     */
    public static Date parseDate(String dataStr) {
        if (!StringUtil.isNullOrEmpty(dataStr)) {
            try {
                return getDataFormat().parse(dataStr);
            } catch (Exception e) {
                return new Date();
            }
        }
        return null;
    }

    private static SimpleDateFormat getDataFormat() {
        SimpleDateFormat sdf = local.get();
        if (sdf == null) {
            sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            local.set(sdf);
        }
        return sdf;
    }


    public static Date addDateByHour(Date date, int hour) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR, hour);
        return calendar.getTime();
    }


    /**
     * 将字符串转换成日期类型
     * 字符串为空或转换出异常则返回null。
     *
     * @param dataStr 日期字符串
     * @param format  日期格式
     * @return Date
     */
    public static Date parseFormatDate(String dataStr, String format) {
        if (!StringUtil.isNullOrEmpty(dataStr)) {
            try {
                return new SimpleDateFormat(format).parse(dataStr);
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }

    /**
     * 将字符串转换成日期类型(格式：yyyy-MM-dd)
     * 字符串为空或转换出异常则返回null。
     *
     * @param dataStr 日期字符串
     * @return Date
     */
    public static Date parseFormatDate(String dataStr) {
        return parseFormatDate(dataStr, formatStr);
    }


    /**
     * 将日期类型转换成字符串
     * 日期为空或转换出异常则返回null。
     *
     * @param date   日期字符串
     * @param format 日期格式
     * @return Date
     */
    public static String parseFormatDate(Date date, String format) {
        if (date != null) {
            try {
                return new SimpleDateFormat(format).format(date);
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }

    /**
     * 将日期类型转换成字符串(格式：yyyy-MM-dd)
     * 日期为空或转换出异常则返回null。
     *
     * @param date
     * @return String
     */
    public static String parseFormatDate(Date date) {
        return parseFormatDate(date, formatStr);
    }


    /**
     * 把long 转换成 日期 再转换成String类型
     */
    public static String transferDate(Long time, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Date date = new Date(time);
        return sdf.format(date);
    }
}