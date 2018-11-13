package com.huntkey.rx.hr.common.util;

import com.huntkey.rx.commons.utils.datetime.DateUtil;
import com.huntkey.rx.commons.utils.string.StringUtil;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author zhangyu
 * @create 2017-11-23 10:02
 **/
public class DateUtils {

    /**
     * DATE_YYYY_MM_DD_HH_MM_SS:日期格式 yyyy-MM-dd HH:mm:ss
     */
    public static String DATE_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    /**
     * DATE_YYYY_MM_DD:日期格式 yyyy-MM-dd
     */
    public static String DATE_YYYY_MM_DD = "yyyy-MM-dd";
    /**
     * DATE_YYYY_MM_DD:日期格式 yyyyMMddHHmmssfff
     */
    public static String DATE_YYYYMMDDHHMMSSFFF = "yyyyMMddHHmmssfff";

    /**
     * DATE_YYYY_MM_DD:日期格式 HHmmssfff
     */
    public static String DATE_HHMMSSFFF = "HHmmssfff";

    /**
     * 格式化Date时间
     *
     * @param time       Date类型时间
     * @param timeFromat String类型格式
     * @return 格式化后的字符串
     */
    public static String parseDateToStr(Date time, String timeFromat) {
        DateFormat dateFormat = new SimpleDateFormat(timeFromat);
        return dateFormat.format(time);
    }

    /**
     * 格式化Timestamp时间
     *
     * @param timestamp  Timestamp类型时间
     * @param timeFromat
     * @return 格式化后的字符串
     */
    public static String parseTimestampToStr(Timestamp timestamp, String timeFromat) {
        SimpleDateFormat df = new SimpleDateFormat(timeFromat);
        return df.format(timestamp);
    }

    /**
     * 格式化Date时间
     *
     * @param time         Date类型时间
     * @param timeFromat   String类型格式
     * @param defaultValue 默认值为当前时间Date
     * @return 格式化后的字符串
     */
    public static String parseDateToStr(Date time, String timeFromat, final Date defaultValue) {
        try {
            DateFormat dateFormat = new SimpleDateFormat(timeFromat);
            return dateFormat.format(time);
        } catch (Exception e) {
            if (defaultValue != null) {
                return parseDateToStr(defaultValue, timeFromat);
            } else {
                return parseDateToStr(new Date(), timeFromat);
            }

        }
    }

    /**
     * 格式化Date时间
     *
     * @param time         Date类型时间
     * @param timeFromat   String类型格式
     * @param defaultValue 默认时间值String类型
     * @return 格式化后的字符串
     */
    public static String parseDateToStr(Date time, String timeFromat, final String defaultValue) {
        try {
            DateFormat dateFormat = new SimpleDateFormat(timeFromat);
            return dateFormat.format(time);
        } catch (Exception e) {
            return defaultValue;
        }
    }

    /**
     * 格式化String时间
     *
     * @param time       String类型时间
     * @param timeFromat String类型格式
     * @return 格式化后的Date日期
     */
    public static Date parseStrToDate(String time, String timeFromat) {
        if (time == null || time.equals("")) {
            return null;
        }

        Date date = null;
        try {
            DateFormat dateFormat = new SimpleDateFormat(timeFromat);
            date = dateFormat.parse(time);
        } catch (Exception e) {

        }
        return date;
    }

    /**
     * 格式化String时间
     *
     * @param strTime      String类型时间
     * @param timeFromat   String类型格式
     * @param defaultValue 异常时返回的默认值
     * @return
     */
    public static Date parseStrToDate(String strTime, String timeFromat,
                                      Date defaultValue) {
        try {
            DateFormat dateFormat = new SimpleDateFormat(timeFromat);
            return dateFormat.parse(strTime);
        } catch (Exception e) {
            return defaultValue;
        }
    }

    /**
     * 根据生日直接返回年龄
     *
     * @param birthday
     * @return
     */
    public static int getAgeByBirth(String birthday) {

        int age = 0;
        try {

            SimpleDateFormat format = new SimpleDateFormat(DATE_YYYY_MM_DD_HH_MM_SS);

            Calendar now = Calendar.getInstance();
            // 当前时间
            now.setTime(new Date());

            Calendar birth = Calendar.getInstance();
            birth.setTime(format.parse(birthday));

            // 如果传入的时间，在当前时间的后面，返回0岁
            if (birth.after(now)) {
                age = 0;
            } else {
                age = now.get(Calendar.YEAR) - birth.get(Calendar.YEAR);
                if (now.get(Calendar.DAY_OF_YEAR) > birth.get(Calendar.DAY_OF_YEAR)) {
                    age += 1;
                }
            }
            return age;
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * 计算工龄
     *
     * @param nowTime
     * @param workTime
     * @return
     */
    public static String workAge(Date nowTime, String workTime) throws ParseException {

        int year = 0;
        int month = 0;
        String work = "";
        //当前时间的年月日
        Calendar cal = Calendar.getInstance();
        cal.setTime(nowTime);
        int nowYear = cal.get(Calendar.YEAR);
        int nowMonth = cal.get(Calendar.MONTH);
        int nowDay = cal.get(Calendar.DAY_OF_MONTH);

        if (!StringUtil.isNullOrEmpty(workTime)) {
            //开始工作时间的年月日
            SimpleDateFormat format = new SimpleDateFormat(DATE_YYYY_MM_DD_HH_MM_SS);
            cal.setTime(format.parse(workTime));
            int workYear = cal.get(Calendar.YEAR);
            int workMonth = cal.get(Calendar.MONTH);
            int workDay = cal.get(Calendar.DAY_OF_MONTH);

            // 得到工龄
            year = nowYear - workYear; // 得到年差
            // 若目前月数少于开始工作时间的月数，年差-1
            if (nowMonth < workMonth) {
                year = year - 1;
            } else if (nowMonth == workMonth) {
                //当月数相等时，判断日数，若当月的日数小于开始工作时间的日数，年差-1
                if (nowDay < workDay) {
                    year = year - 1;
                }
            }

            //得到月份
            month = nowMonth - workMonth;
            if (nowMonth < workMonth) {
                month = (nowMonth + 12) - workMonth;
            } else if (nowMonth == workMonth) {
                if (nowDay < workDay) {
                    month = (nowMonth + 11) - workMonth;
                }
            }

            work = String.valueOf(year).concat(".").concat(String.valueOf(month));
            return work;
        } else {
            return null;
        }
    }

    /**
     * 计算两个时间差
     *
     * @param nowTime  date类型
     * @param workTime String类型
     * @return String
     */
    public static String workYear(Date nowTime, String workTime) throws ParseException {

        int year = 0;
        int month = 0;
        int day = 0;
        String work = "";
        //当前时间的年月日
        Calendar cal = Calendar.getInstance();
        cal.setTime(nowTime);
        int nowYear = cal.get(Calendar.YEAR);
        int nowMonth = cal.get(Calendar.MONTH);
        int nowDay = cal.get(Calendar.DAY_OF_MONTH);

        //开始工作时间的年月日
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        cal.setTime(format.parse(workTime));
        int workYear = cal.get(Calendar.YEAR);
        int workMonth = cal.get(Calendar.MONTH);
        int workDay = cal.get(Calendar.DAY_OF_MONTH);

        //得到工龄

        year = nowYear - workYear; //得到年差
        //若目前月数少于开始工作时间的月数，年差-1
        if (nowMonth < workMonth) {
            year = year - 1;
        } else if (nowMonth == workMonth) {
            //当月数相等时，判断日数，若当月的日数小于开始工作时间的日数，年差-1
            if (nowDay < workDay) {
                year = year - 1;
            }
        }

        //得到月份
        month = nowMonth - workMonth;
        if (nowMonth < workMonth) {
            month = (nowMonth + 12) - workMonth;
        } else if (nowMonth == workMonth) {
            if (nowDay < workDay) {
                month = (nowMonth + 11) - workMonth;
            }
        }

        double values= Double.valueOf(month)/12;
        BigDecimal bg = new BigDecimal(values);

        //解决精度丢失
        double f1 = bg.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
        String f2 = String.valueOf(f1);

        String[] splitPostId = f2.split("\\.");
        String id = splitPostId[splitPostId.length - 1];

        work = String.valueOf(year).concat(".").concat(String.valueOf(id));
        return work;
    }


    /**
     * 计算在职月，返回小数点后一位
     *
     * @param nowTime
     * @param beginTime
     * @return
     * @throws ParseException
     */
    public static String getInwork(Date nowTime, String beginTime) throws ParseException {

        String time = workYear(nowTime, beginTime);

        DecimalFormat df = new DecimalFormat();
        // 精确到小数点后一位
        df.setMinimumFractionDigits(1);

        return df.format(Double.parseDouble(time) * 12);
    }

    /**
     * 计算两个时间差  返回以月为单位的结果 保留一位
     *
     * @param nowTime  date类型
     * @param workTime String类型
     * @return String
     */
    public static String workMonth(Date nowTime, String workTime) throws ParseException {

        int year = 0;
        int month = 0;
        int totallMonth = 0;
        String work = "";
        //当前时间的年月日
        Calendar cal = Calendar.getInstance();
        cal.setTime(nowTime);
        int nowYear = cal.get(Calendar.YEAR);
        int nowMonth = cal.get(Calendar.MONTH);
        int nowDay = cal.get(Calendar.DAY_OF_MONTH);

        //开始工作时间的年月日
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        cal.setTime(format.parse(workTime));
        int workYear = cal.get(Calendar.YEAR);
        int workMonth = cal.get(Calendar.MONTH);
        int workDay = cal.get(Calendar.DAY_OF_MONTH);

        //得到工龄

        year = nowYear - workYear; //得到年差
        //若目前月数少于开始工作时间的月数，年差-1
        if (nowMonth < workMonth) {
            year = year - 1;
        } else if (nowMonth == workMonth) {
            //当月数相等时，判断日数，若当月的日数小于开始工作时间的日数，年差-1
            if (nowDay < workDay) {
                year = year - 1;
            }
        }

        //得到月份
        month = nowMonth - workMonth;
        if (nowMonth < workMonth) {
            month = (nowMonth + 12) - workMonth;
        } else if (nowMonth == workMonth) {
            if (nowDay < workDay) {
                month = (nowMonth + 11) - workMonth;
            }
        }

        totallMonth = year * 12 + month;
        work = String.valueOf(totallMonth);
        return work;
    }

    /**
     * 计算两个时间差
     *
     * @param nowTime  String类型
     * @param workTime String类型
     * @return String
     */
    public static String workYear1(String nowTime, String workTime) throws ParseException {

        int year = 0;
        int month = 0;
        long template = 0;
        int day = 0;
        String work = "";
        //当前时间的年月日
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        cal.setTime(format1.parse(nowTime));
        int nowYear = cal.get(Calendar.YEAR);
        int nowMonth = cal.get(Calendar.MONTH);
        int nowDay = cal.get(Calendar.DAY_OF_MONTH);

        //开始工作时间的年月日
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        cal.setTime(format.parse(workTime));
        int workYear = cal.get(Calendar.YEAR);
        int workMonth = cal.get(Calendar.MONTH);
        int workDay = cal.get(Calendar.DAY_OF_MONTH);

        //得到工龄
        year = nowYear - workYear; //得到年差
        //若目前月数少于开始工作时间的月数，年差-1
        if (nowMonth < workMonth) {
            year = year - 1;
        } else if (nowMonth == workMonth) {
            //当月数相等时，判断日数，若当月的日数小于开始工作时间的日数，年差-1
            if (nowDay < workDay) {
                year = year - 1;
            }
        }

        //得到月份
        month = nowMonth - workMonth;
        if (nowMonth < workMonth) {
            month = (nowMonth + 12) - workMonth;
        } else if (nowMonth == workMonth) {
            if (nowDay < workDay) {
                month = (nowMonth + 11) - workMonth;
            }
        }

        double values= Double.valueOf(month)/12;
        BigDecimal bg = new BigDecimal(values);
        double f1 = bg.setScale(1).doubleValue();
        String f2 = String.valueOf(f1);

        String[] splitPostId = f2.split("\\.");
        String id = splitPostId[splitPostId.length - 1];

        work = String.valueOf(year).concat(".").concat(String.valueOf(id));
        return work;
    }


}
