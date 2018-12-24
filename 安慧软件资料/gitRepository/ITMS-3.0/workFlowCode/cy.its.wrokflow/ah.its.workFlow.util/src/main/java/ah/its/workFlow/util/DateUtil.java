package ah.its.workFlow.util;

import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 时间工具类
 */
public class DateUtil {
    private static final String defaultTimePattern  = "yyyy-MM-dd HH:mm:ss";

    public static String getCurrentTime(){
    	return getCurrentTime(defaultTimePattern);
    }
    
    public static String getCurrentTime(String timePattern){
        if(StringUtils.hasText(timePattern)){
            return new SimpleDateFormat(timePattern).format(new Date());
        }else{
            return new SimpleDateFormat(defaultTimePattern).format(new Date());
        }
    }
    
    /**
     * 两个时间之间的时间差，单位毫秒
     */
    public static Long getSubTime(String startTime,String endTime,String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		try {
			Date startDate = sdf.parse(startTime);
			Date endDate = sdf.parse(endTime);
			Long subTimes = endDate.getTime() - startDate.getTime();
			return subTimes;
		} catch (ParseException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
    
    /**
	  * 返回自1970-1-1 00:00:00到当前系统时间的秒数 
	  */
	public static String getTimestamp(){
		Long time = new Date().getTime();
		time = time / 1000;
		return time.toString();
	}
	
	/**
	 * 获取两个日期之间的每一天
	 */
	public static List<Date> SubDay(String s_date,String e_date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Long subTimes ;
		Date startDate ;
		Date endDate ;
		try {
			startDate = sdf.parse(s_date);
			endDate = sdf.parse(e_date);
			subTimes = endDate.getTime() - startDate.getTime();
		} catch (ParseException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		Long step = subTimes / (24 * 60 * 60 * 1000);// 相隔天数
	 	List<Date> dateList = new ArrayList<Date>();
	    dateList.add(endDate);
	    for (int i = 1; i <= step; i++) {
	        dateList.add(new Date(dateList.get(i - 1).getTime()
	                - (24 * 60 * 60 * 1000)));// 比上一天减一
	    }
		return dateList;
	}
	
	/**
	 * 秒转换成hh:mm:ss
	 */
	public static String secToTime(int time) {  
        String timeStr = null;  
        int hour = 0;  
        int minute = 0;  
        int second = 0;  
        if (time <= 0)  
            return "00:00";  
        else {  
            minute = time / 60;  
            if (minute < 60) {  
                second = time % 60;  
                timeStr = unitFormat(minute) + ":" + unitFormat(second);  
            } else {  
                hour = minute / 60;  
                if (hour > 99)  
                    return "99:59:59";  
                minute = minute % 60;  
                second = time - hour * 3600 - minute * 60;  
                timeStr = unitFormat(hour) + ":" + unitFormat(minute) + ":" + unitFormat(second);  
            }  
        }  
        return timeStr;  
    }  
  
    public static String unitFormat(int i) {  
        String retStr = null;  
        if (i >= 0 && i < 10)  
            retStr = "0" + Integer.toString(i);  
        else  
            retStr = "" + i;  
        return retStr;  
    }  
    
    /**
     * 当前日期前后几天的日期
     * 往后输入正数，往前输入负数
     */
    public static String findDay(int d) {  
    	SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd"); //字符串转换
    	Calendar c = Calendar.getInstance(); 
    	c.setTimeInMillis(new Date().getTime());
    	c.add(Calendar.DATE, d);
    	Date date= new Date(c.getTimeInMillis()); //将c转换成Date
    	String retdate = formatDate.format(date);
    	return retdate;
    }
    
    /** 
	* @Description: 把时间LONG 转化为日期
	*/
    public static Object toDate(String time) {
		if(StringUtils.isEmpty(time)){
			return null;
		}
		long  dt =  Long.valueOf(time);
		Date  date  = new  Date();
		date.setTime(dt);
		return date;
	}
	
	/** 
	* @Description: 把字符串日期时间 转化为date日期时间(yyyy-MM-dd HH:mm:ss)
	*/
    public static Object StringtoDate(String time,String pattern) {
		if(StringUtils.isEmpty(time)){
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);//注意格式化的表达式
		Date d = null;
		try {
			d = sdf.parse(time );
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return d;
	}
    
    /**
     * date转换成string
     */
    public static Object DatetoString(Date time,String pattern) {
		if(StringUtils.isEmpty(time)){
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);//注意格式化的表达式
		String d = sdf.format(time );
		return d;
	}
}
