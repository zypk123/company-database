package cy.its.service.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 时间处理工具类
 *
 */
public class TimeUtil {
	/** 
	* @Title: getPeriodkey 
	* @Description:根据传入的日期算出当前处于那个周期
	* @param @param format
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	public static long getPeriodkey(Date currentTime) {
		int  value  = currentTime.getMinutes();
		int  s = value/10;
		value = value%10;
		if(value>=5){
			currentTime.setMinutes(s*10+5);
		}else{
			currentTime.setMinutes(s*10);
		}
		currentTime.setSeconds(0);
		//现在的周期落后5分钟，因此需要加5分钟
		currentTime.setTime(currentTime.getTime());
		return 1000*(currentTime.getTime()/1000);
	}
	

	/**
	 * @throws ParseException  
	* @Title: getPeriodkey 
	* @Description:根据传入的日期算出当前处于那个周期
	* @param @param format
	* @param @return 设定文件 
	* @return String 返回类型 
	* @throws 
	*/
	public static  Date getPeriodkey1(Date currentTime) throws ParseException {
		int  value  = currentTime.getMinutes();
		int  s = value/10;
		value = value%10;
		if(value>=5){
			currentTime.setMinutes(s*10+5);
		}else{
			currentTime.setMinutes(s*10);
		}
		currentTime.setSeconds(0);
		return currentTime;
	}

	/**
	 * @param startTime
	 * 根据传入值，取其向后5分钟数据
	 * @return
	 */
	public static Date reduceFiveMinute(Date startTime) {
		long time  = startTime.getTime();
		Date endDate  = new Date();
		endDate.setTime(time - 5*60*1000);
		return endDate;
	}
	
	/**
	 * @param 获取两个小时的KEY值
	 * 作为redis KEY超过两个小时自动过期
	 * @return
	 */
	public static String twoHoursKey(Date startTime) {
		Date date  = new Date();
		date.setTime(startTime.getTime());
		int hours  = date.getHours();
		if(hours%2 != 0){
			hours =hours  -1;
		}
		date.setHours(hours);
		SimpleDateFormat  sf  = new SimpleDateFormat("yyyyMMddHH");
		return sf.format(date);
	}
	
	/**
	 * @param 获取两个小时的KEY值
	 * 作为redis KEY超过两个小时自动过期
	 * @return
	 */
	public static String twoHoursKey(Date time,int min) {
		Date startTime  = new Date();
		startTime.setTime(time.getTime());
		//减三分钟
		int minutes = startTime.getMinutes();
		minutes= minutes -min;
		startTime.setMinutes(minutes);
		
		int hours  = startTime.getHours();
		if(hours%2 != 0){
			hours =hours  -1;
		}
		startTime.setHours(hours);
		SimpleDateFormat  sf  = new SimpleDateFormat("yyyyMMddHH");
		return sf.format(startTime);
	}
	
	public static void main(String[] args) throws ParseException{
		
		SimpleDateFormat  sf  = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for(int i=0;i<40;i++){
			Date  date = new Date();
			date.setHours(i);
			System.out.println(twoHoursKey(date));
		}
	}
}
