package com.huntkey.rx.hr.common.util;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * @author yaoss
 */
public class DateDiff {

    public static double yearDateDiff(long startTime, Date endDate) {
        float f =  (getTimeDiff(startTime,endDate)*1.f)/365.f/ 24.f / 60.f / 60.f / 1000.f;
        BigDecimal decimal = new BigDecimal(f);
        return decimal.setScale(1, BigDecimal.ROUND_DOWN).doubleValue();
    }

    private static long getTimeDiff(long startTime, Date endDate){
        if(endDate==null){
            endDate = new Date();
        }
        Calendar calBegin = Calendar.getInstance();
        Calendar calEnd = Calendar.getInstance();
        calBegin.setTimeInMillis(startTime);
        calEnd.setTime(endDate);
        long timeDur = calEnd.getTimeInMillis() - calBegin.getTimeInMillis();
        return timeDur;
    }

    public static double monthsDateDiff(long startTime, Date endDate) {
        float f =  (getTimeDiff(startTime,endDate)*1.f)/30.f/ 24.f / 60.f / 60.f / 1000.f;
        BigDecimal decimal = new BigDecimal(f);
        return decimal.setScale(1, BigDecimal.ROUND_DOWN).doubleValue();
    }

    public static double daysDateDiff(long startTime, Date endDate) {
        float f =  (getTimeDiff(startTime,endDate)*1.f)/ 24.f / 60.f / 60.f / 1000.f;
        BigDecimal decimal = new BigDecimal(f);
        return decimal.setScale(1, BigDecimal.ROUND_DOWN).doubleValue();
    }

    public static boolean canPositive(Date oepaDate) {
        Calendar nowCal= Calendar.getInstance();
        Calendar oepateCal = Calendar.getInstance();
        oepateCal.setTime(oepaDate);
        nowCal.set(nowCal.get(Calendar.YEAR),nowCal.get(Calendar.MONTH),nowCal.get(Calendar.DATE));
        nowCal.set(Calendar.HOUR_OF_DAY,0);
        nowCal.set(Calendar.MINUTE,0);
        nowCal.set(Calendar.SECOND,0);
        nowCal.set(Calendar.MILLISECOND,0);
        oepateCal.set(oepateCal.get(Calendar.YEAR),oepateCal.get(Calendar.MONTH),oepateCal.get(Calendar.DATE));
        oepateCal.set(Calendar.HOUR_OF_DAY,0);
        oepateCal.set(Calendar.MINUTE,0);
        oepateCal.set(Calendar.SECOND,0);
        oepateCal.set(Calendar.MILLISECOND,0);
        float f =( (oepateCal.getTimeInMillis()-nowCal.getTimeInMillis())*1.f)/ 24.f / 60.f / 60.f / 1000.f;
        return f<=0.0;
    }

//    public static void main(String[] args) {
//        Calendar calendar = Calendar.getInstance();
//        calendar.set(2017,1,4);
//        System.out.println(yearDateDiff(calendar.getTimeInMillis(),null));
//        calendar.set(2018,0,20);
//        System.out.println(monthsDateDiff(calendar.getTimeInMillis(),null));
//        calendar.set(2018,0,20);
//        System.out.println(daysDateDiff(calendar.getTimeInMillis(),null));
//        calendar.set(2018,1,20);
//        System.out.println(daysDateDiff(calendar.getTimeInMillis(),null));
//        calendar.set(2018,0,21);
//        System.out.println(daysDateDiff(calendar.getTimeInMillis(),null));
//    }


}


