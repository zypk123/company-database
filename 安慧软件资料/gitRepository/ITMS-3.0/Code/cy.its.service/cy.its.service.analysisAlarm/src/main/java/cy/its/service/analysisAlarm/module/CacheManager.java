package cy.its.service.analysisAlarm.module;

import java.util.List;

import javax.print.DocFlavor.STRING;



import cy.its.service.analysisAlarm.domain.TrafficAlarmValue;
import cy.its.service.util.DBUtil;
import cy.its.service.util.StringUtils;

/**
* @Title: CacheManager.java 
* @Package cy.its.service.section.module 
* @Description:缓存管理，放置5个指标的阈值信息 能见度、路感、气象、
* @author lil@cychina.cn
* @date 2015年11月4日 下午6:46:17 
* @version V1.0   
* 
 */
public class CacheManager {
	
	//能见度
	private static   List<TrafficAlarmValue> listv=null;
	
	//路面温度
	private static   List<TrafficAlarmValue> listr=null;
	
	//大风
	private static   List<TrafficAlarmValue> listwd=null;
	
	//水膜厚度
	private static   List<TrafficAlarmValue> listwt=null;
	
	//冰雪public
	private static   List<TrafficAlarmValue> listice=null;
	
	
	//冰雪public
	private static   List<TrafficAlarmValue> listtr=null;
	
	
	/*
	 * 道路类型
	 */
	private static  String    roadType = "0";
	/**
	 * 平均旅行时间超长预警
	 */
	public final static String  alermTypetr="04";
	
	/**
	 * 预警类型 能见度
	 */
	public final static String  alermTypev="31";
	/**
	 * 预警类型 路面温度
	 */
	public final static String  alermTyper="32";
	/**
	 * 预警类型  水膜厚度
	 */
	public final static String  alermTypewt="33";
	
	/**
	 * 预警类型  雨雪，预警 34
	 */
	public final static String  alermTypeice="34";
	/**
	 * 预警类型  风速
	 */
	public final static String  alermTypewd="35";
	
	
	
	/**
	 * @param alermType
	 * @return
	 * 程序启动不做初始化，如果为空先初始化数据
	 */
	public  static  List<TrafficAlarmValue> getTrafficAlarmDataList(String alermType){
	    if(alermType.equals(alermTypev)){
	    	if(listv == null){
	    		initWarnList(alermType,"");
	    	}
	    	return listv;
	    }else if(alermType.equals(alermTypewt)){
	    	if(listwt == null){
	    		initWarnList(alermType,"");
	    	}
	    	return listwt;
		}else if(alermType.equals(alermTyper)){
			if(listr == null){
	    		initWarnList(alermType,"");
	    	}
	    	return listr;
		}else if(alermType.equals(alermTypeice)){
			if(listice == null){
	    		initWarnList(alermType,"");
	    	}
	    	return listice;
		}else if(alermType.equals(alermTypewd)){
			if(listwd == null){
	    		initWarnList(alermType,"");
	    	}
	    	return listwd;
		}else if(alermType.equals(alermTypetr)){
			if(listtr == null){
	    		initWarnList(alermType,"");
	    	}
			return listtr;
		}else{
			return null;
		}
	}
	//重新刷新缓存
	public static void initWarnList(String alermType,String rt){
		synchronized(CacheManager.class){
			if(StringUtils.isEmpty(rt)){
				rt = roadType;
			}
			if(alermType.equals(alermTypev)){
				listv = DBUtil.queryData(getSql(alermType,rt),TrafficAlarmValue.class);
		    }else if(alermType.equals(alermTypewt)){
		    	listwt = DBUtil.queryData(getSql(alermType,rt),TrafficAlarmValue.class);
			}else if(alermType.equals(alermTyper)){
		    	listr = DBUtil.queryData(getSql(alermType,rt),TrafficAlarmValue.class);
			}else if(alermType.equals(alermTypeice)){
		    	listice = DBUtil.queryData(getSql(alermType,rt),TrafficAlarmValue.class);
			}else if(alermType.equals(alermTypewd)){
		    	listwd = DBUtil.queryData(getSql(alermType,rt),TrafficAlarmValue.class);
			}else if(alermType.equals(alermTypetr)){
				listtr = DBUtil.queryData(getSql(alermType,rt),TrafficAlarmValue.class);
			}
		}
	}
	
	/** 
	* @Title: getSql 
	* @Description: 预警配置SQL获取
	* @param @param alermType
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	public static String getSql(String  alermType,String roadType){
		
		return  " select  t.alarm_value_type alarmValueType,t.road_type roadType,t.alarm_grade alermGrade,t.min min,t.max max  from  t_traffic_alarm_value  t  where t.road_type='"+roadType+"' and  t.alarm_value_type='"+alermType+"'";
	}
	
}
