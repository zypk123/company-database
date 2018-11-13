package com.huntkey.rx.purchase.common.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author yaoss
 * @Date 2017/12/11 21:28
 * @@Description
 */
public class NullUtils {
    public static String valueOf(Object o){
        if(null==o){
            return "";
        }
        return o.toString();
    }
    public static String intValueOf(Object o){
        if(null==o){
            return "";
        }
        if(o instanceof  BigDecimal){
            BigDecimal bigDecimal = (BigDecimal) o;
            return ""+bigDecimal.intValue();
        }
        return o.toString();
    }

    public static DecimalFormat format =  new DecimalFormat(",###.#######");
    public static SimpleDateFormat sdf =  new SimpleDateFormat();

    public static String doubleValueOf(Object o){
        if(null==o){
            return "";
        }
        if(o instanceof  BigDecimal){
            BigDecimal bigDecimal = (BigDecimal) o;
            return format.format(Double.parseDouble(bigDecimal.toPlainString()));
        }
        return o.toString();
    }

    public static Date formatDateNoTime(Date date) throws Exception{
        sdf.applyPattern("yyyy-MM-dd");
        String str = sdf.format(date);
        return sdf.parse(str);
    }

    public static Date nullEndDate() throws Exception{
        sdf.applyPattern("yyyy-MM-dd");
        return sdf.parse("9999-12-31");
    }

    public static String convertStartTime(String startTime)throws Exception{
        sdf.applyPattern("yyyy-MM-dd");
        Date date1 =  sdf.parse(startTime);
        sdf.applyPattern("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date1);
    }
    public static String convertEndTime(String endTime)throws Exception{
        sdf.applyPattern("yyyy-MM-dd");
        Date date1 =  sdf.parse(endTime);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date1);
        calendar.set(Calendar.HOUR,23);
        calendar.set(Calendar.MINUTE,59);
        calendar.set(Calendar.SECOND,59);
        sdf.applyPattern("yyyy-MM-dd HH:mm:ss");
        return sdf.format(calendar.getTime());
    }



}
