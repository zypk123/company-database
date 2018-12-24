package cy.its.service.analysisAlarm.module;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;

import cy.its.service.analysisAlarm.domain.GlobalProperty;
import cy.its.service.analysisAlarm.domain.TrafficAlarmValue;
import cy.its.service.analysisAlarm.domain.WeatherAlarm;
import cy.its.service.common.rabbitmqClient.IReceiver;
import cy.its.service.common.rabbitmqClient.MQGateWay;
import cy.its.service.util.DBUtil;
import cy.its.service.util.RedisPoolUtil;
import cy.its.service.util.StringUtils;

/**
* @Title: TravalTimeModule.java 
* @Package cy.its.service.analysisAlarm.module 
* @Description:旅行时间超长分析
* @author lil@cychina.cn
* @date 2015年12月17日 上午10:37:16 
* @version V1.0   
 */
public class TravalTimeModule implements IReceiver {

	private static  Logger log=Logger.getLogger(TravalTimeModule.class);
	
	private String   redisKey  = "alarm:traval";
	
	private  String  routeKey ="its_traval_alarm";
	/*
	 * (non-Javadoc)
	 * @see cy.its.service.common.rabbitmqClient.IReceiver#receive(java.lang.String, java.lang.String)
	 * 接收数据，断流要考虑时间  一般6 - 23 可以调整
	 */
	@Override
	public Boolean receive(String routingKey, String message) {
		//程序启动时间，非启动期间删除缓存信息
		int startTime = GlobalProperty.getStartTime();
		int endTime = GlobalProperty.getStartTime();
	    int hour  = new Date().getHours();
	    //数据计算区间
	    if(hour >= startTime && startTime <= endTime){
			//一个区间一条记录
			JSONObject  jsonObject = JSONObject.parseObject(message);
			//获取预警相关信息
			WeatherAlarm alarm = getRegionAlarm(jsonObject);
			String regionalId = jsonObject.getString("regionalId");
			String jsonStr = RedisPoolUtil.hget(redisKey,regionalId);//获取上次缓存的数据
			if(!StringUtils.isEmpty(jsonStr)){
				WeatherAlarm  lastAlarm = JSONObject.parseObject(jsonStr,WeatherAlarm.class);
				//更新缓存
				if(!lastAlarm.getAlarmGrade().equals(alarm.getAlarmGrade())){
					//先插入数据库
					try {
						//只要状态变化就需要插入新数据
						String  sql= AlarmUtil.getSql(alarm);
						DBUtil.execute(sql);
						//产生预警信息发布到MQ上
						MQGateWay.publish(routeKey,JSONObject.toJSONString(alarm));
						
					} catch (IllegalArgumentException | IllegalAccessException e) {
						log.error("平均旅行时间超长预警入库及发送失败！");
						log.error(e);
					}
				}else{
					//状态跟上一次相同时，则更新记录，如果状态为0 则不处理
					if(!lastAlarm.getAlarmGrade().equals("0")){
						log.info("跟新状态持续时间！");
						DBUtil.execute(AlarmUtil.getUpdateSql(lastAlarm,alarm.getStartAlarmTime()));
					}
				}
			}else{
				if(!alarm.getAlarmGrade().equals("0")){
					MQGateWay.publish(routeKey,JSONObject.toJSONString(alarm));
				}
				RedisPoolUtil.hset(redisKey,regionalId,JSONObject.toJSONString(alarm));//获取上次缓存的数据
			}
	    }
		return true;
	}

	/** 
	* @Title: getRegionAlarm 
	* @Description: 根据穿入的值获取预警信息
	* @param @param jsonObject
	* @param @return    设定文件 
	* @return WeatherAlarm    返回类型 
	* @throws 
	*/
	private WeatherAlarm getRegionAlarm(JSONObject jsonObject) {
		WeatherAlarm  alarm= new WeatherAlarm();
		//计算每公里耗时
		double speed = jsonObject.getDouble("avgSpeed");
		double avgTime  = speed==0 ?0:3600*1.00/speed;
		alarm.setAlarmEventId(AlarmUtil.generatorId());
		alarm.setOrgCode(jsonObject.getString("orgCode"));
		alarm.setRegionalId(jsonObject.getString("regionalId"));
		alarm.setDeviceSysNbr("");
		alarm.setSiteCode("");
		alarm.setOrgPrivilegeCode(jsonObject.getString("orgPrivilegeCode"));
		alarm.setStartAlarmTime(jsonObject.getDate("updateTime"));
		alarm.setEndAlarmTime(jsonObject.getDate("updateTime"));
		alarm.setAlarmEventType(AlarmUtil.alarmEventTypeF);
		alarm.setSubAlarmEvent(AlarmUtil.TYPE_50604);
		alarm.setAlarmGrade("0");//无状态正常
		alarm.setWeatherData(jsonObject.getString("avgTravelTime"));
		//阈值计算，每公里设置最小事时间
		int min = new Date().getMinutes();
		List<TrafficAlarmValue> list = CacheManager.getTrafficAlarmDataList(CacheManager.alermTypetr);
		if(list == null){
			CacheManager.initWarnList(CacheManager.alermTypetr,"");
			list = CacheManager.getTrafficAlarmDataList(CacheManager.alermTypetr);
		}
		for(TrafficAlarmValue tdv:list){
			if(avgTime >tdv.getMin() && avgTime <=tdv.getMax()){
				alarm.setAlarmGrade(tdv.getAlermGrade());
				alarm.setAlarmValueId(tdv.getMin()+","+tdv.getMax());
			}
		}
		alarm.setAlarmDesc("区间旅行时间超长预警等级为："+alarm.getAlarmGrade());
		return alarm;
	}

}
