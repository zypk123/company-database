package cy.its.service.analysisAlarm.module;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import cy.its.service.analysisAlarm.domain.WeatherAlarm;
import cy.its.service.util.TableColumn;

/**
* @Title: AlarmUtil.java 
* @Package cy.its.service.analysisAlarm.module 
* @Description: 预警信息工具类
* @author lil@cychina.cn
* @date 2015年12月16日 上午9:42:56 
* @version V1.0   
 */
public class AlarmUtil {
	
	//日期格式化
	private static SimpleDateFormat  df  = new  SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	//流量
	public  final  static  String  alarmEventTypeF = "506"; 
	//气象
	public  final  static  String  alarmEventTypeW = "502"; 
	//事件抓拍
	public  final  static  String  alarmEventTypeR = "434"; 
	
	//能见度预警
	public  final  static  String  TYPE_5021 = "1";
	
	//路面温度预警
	public  final  static  String  TYPE_5022 = "2";
	//路面积水预警
	public  final  static  String  TYPE_5023 = "3";
	//风力预警
	public  final  static  String  TYPE_5024 = "4";
	//路面结冰预警
	public  final  static  String  TYPE_5025 = "5";
	
	
	//流量突变预警
	public  final  static  String  TYPE_50601 = "01";
	//断流预警
	public  final  static  String  TYPE_50602 = "02";
	//区间旅行时间超长预警
	public  final  static  String  TYPE_50604 = "04";
	
	/** 
	* @Title: generatorId 
	* @Description: 生成唯一键 
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	public  static String generatorId(){
	   String  id = UUID.randomUUID().toString().replace("-",""); 
	   return  id;
    }
	
	/**
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException  
	* @Title: getSql 
	* @Description:获取SQL执行语句
	* @param @param alarm
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	public static String getSql(WeatherAlarm alarm) throws IllegalArgumentException, IllegalAccessException {
		StringBuffer   sb = new StringBuffer();
		StringBuffer   sb2 = new StringBuffer();
		sb.append(" insert  into T_TRAFFIC_ALARM_EVENT( ");
		sb2.append(" values( ");
		Field[] fs = WeatherAlarm.class.getDeclaredFields(); 
		for(int i = 0 ; i < fs.length; i++){  
			 Field f = fs[i];  
			 if(f.isAnnotationPresent(TableColumn.class)){
				 TableColumn tableColumn = f.getAnnotation(TableColumn.class);
				 sb.append(tableColumn.name()+",");
				 f.setAccessible(true); //设置些属性是可以访问的 
				 Object val = f.get(alarm);//得到此属性的值
				 String type = f.getType().toString();//得到此属性的类型 
				 if (type.endsWith("String")) {  
					 sb2.append("'"+val+"',");
				 }else if(type.endsWith("Date")){
					 sb2.append(" to_date('"+df.format(val)+"','yyyy-mm-dd hh24:mi:ss'),");
				 }else{
					 sb2.append(val+",");
				 }
	         }
		}
		if(sb.length() > 1){
			sb.deleteCharAt(sb.length()-1);
		}
		if(sb2.length() > 1){
			sb2.deleteCharAt(sb2.length()-1);
		}
		sb.append(" ) ");
		sb2.append(") ");
		return sb.toString() + sb2.toString();
	}
	
	public static String getUpdateSql(WeatherAlarm alarm,Date  startTIme) {
		String sql  = " update T_TRAFFIC_ALARM_EVENT set END_ALARM_TIME = to_date('"+df.format(startTIme)+"','yyyy-mm-dd hh24:mi:ss') ";
		sql  += " where  ALARM_EVENT_ID = '"+alarm.getAlarmEventId()+"'";
		return sql;
	}
}
