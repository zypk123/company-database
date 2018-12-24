package cy.its.service.region.module;

import java.util.List;

import cy.its.service.region.domain.Region;
import cy.its.service.region.domain.TrafficAlarmValue;
import cy.its.service.util.DBUtil;

/**
* @Title: CacheManager.java 
* @Package cy.its.service.section.module 
* @Description:缓存管理，单列模式
* @author lil@cychina.cn
* @date 2015年11月4日 下午6:46:17 
* @version V1.0   
 */
public class CacheManager {
	
	//区间数据缓存
	private static   List<Region>  Lists=null;
	
	private static   List<TrafficAlarmValue> lista=null;
	/**
	 * 预警类型
	 */
	private static String  alermType="41";
	
	
	private static String  sql = " select REGIONAL_ID regionalId,t3.site_code startSiteCode,t4.site_code endSiteCode,t1.DIRECTION_TYPE directionType,to_char(t1.DISTANCE) distance,ROAD_TYPE roadType,t4.org_privilege_code orgPrivilegeCode "
			+ "from  t_Sys_Region  t1  "
			+ " inner join  T_SYS_ROAD t2  on t2.ROAD_ID  = t1.ROAD_ID   "
			+ " inner join  t_Sys_Site t3  on  t3.site_id =  t1.ENTRY_SITE_ID "
			+ " inner join  t_Sys_Site t4  on  t4.site_id =  t1.EXIT_SITE_ID   "
			+ " where t1.ENABLE_FLAG = '1' ";
	
	private static String  sql2 = " select  t.alarm_value_type alarmValueType,t.road_type roadType,t.alarm_grade alermGrade,t.min min,t.max max  from  t_traffic_alarm_value  t  where t.alarm_value_type='"+alermType+"'";
	
	public  static  List<TrafficAlarmValue> getTrafficAlarmDataList(){
		if(lista == null){
			initTrafficAlarmList();
		}
		return lista;
	}
	
	public  static  List<Region> getDataList(){
		if(Lists == null){
			initRegionList();
		}
		return Lists;
	}
	
	public  static  String getAlermType(){
		return alermType;
	}
	//重新刷新缓存
	public static void initRegionList(){
		synchronized(CacheManager.class){
			Lists = DBUtil.queryData(sql,Region.class);
		}
	}
	
	//重新刷新缓存
	public static void initTrafficAlarmList(){
		synchronized(CacheManager.class){
			lista = DBUtil.queryData(sql2,TrafficAlarmValue.class);
		}
	}
	
}
