package cy.its.service.analysisAlarm.module;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cy.its.service.analysisAlarm.domain.GlobalProperty;
import cy.its.service.analysisAlarm.domain.RoadSensor;
import cy.its.service.analysisAlarm.domain.TrafficAlarmValue;
import cy.its.service.analysisAlarm.domain.VisibilityData;
import cy.its.service.analysisAlarm.domain.WeatherAlarm;
import cy.its.service.common.rabbitmqClient.IReceiver;
import cy.its.service.common.rabbitmqClient.MQGateWay;
import cy.its.service.util.DBUtil;
import cy.its.service.util.RedisPoolUtil;

/**
* @Title: RoadSensorModule.java 
* @Package cy.its.service.weather.module 
* @Description: 路感分析分析预警
* @author lil@cychina.cn
* @date 2015年11月14日 上午10:25:24 
* @version V1.0   
 */
public class RoadSensorModule implements IReceiver{
	/*
	 *日志
	 */
	private static  Logger log=Logger.getLogger(RoadSensorModule.class);
	/* (non-Javadoc)
	 * @see cy.its.service.com.rabbitmqClient.IReceiver#receive(java.lang.String, java.lang.String)
	 * 预警分为三种：路面温度、道路冰雪预警、路面积水
	 */
	@Override
	public Boolean receive(String arg0, String jsonObject) {
		//路面温度，路面状况
		RoadSensor vp = JSONObject.parseObject(jsonObject,RoadSensor.class);
		//路面温度分析
		processRoadSensor(vp,"temperature");
		//道路状况分析
		processRoadSensor(vp,"roadCondtion");
		//水膜厚度分析
		processRoadSensor(vp,"waterThickness");
		
		return true;
	}

	
	private void processRoadSensor(RoadSensor vp,String  key) {
		String     deviceSysNbr  = vp.getDeviceSysNbr();
		//如果连续两个周期出现情况，则做预警
		String  jsonStr  = RedisPoolUtil.hget("alarm:"+key,deviceSysNbr);
		if(jsonStr == null){
			//第一次放入数据
			List<WeatherAlarm>  list  = new ArrayList();
			//计算当前数据的状态  alarmGrade = "0" 正常
			WeatherAlarm  alarm = getRoadSensorStatus(vp,key);
			list.add(alarm);
			RedisPoolUtil.hset("alarm:"+key,vp.getDeviceSysNbr(),JSONArray.toJSONString(list));
		}else{
			//计算当前数据的状态  alarmGrade = "0" 无状态
			WeatherAlarm  alarm = getRoadSensorStatus(vp,key);
			List<WeatherAlarm>  list= JSONArray.parseArray(jsonStr,WeatherAlarm.class);
			list  = list.stream().sorted((a,b)->{
				return (int) (a.getEndAlarmTime().getTime() - b.getEndAlarmTime().getTime());
			}).collect(Collectors.toList());
			//连续两个周期，产生预警，则报警，如果 三次预警等级都不同则，
			WeatherAlarm  lastAlarm = list.get(list.size()-1);
			//如果数据延迟则不做预警，直接舍弃
			Date  recordTime  = alarm.getStartAlarmTime();
			Date  lastRecordTime  = lastAlarm.getEndAlarmTime();
			if(recordTime == null || lastRecordTime == null){
				return;
			}else{
				if(alarm.getStartAlarmTime().getTime() < lastAlarm.getEndAlarmTime().getTime()){
					return;
				}else{
					//大于10分钟 数据出现问题，需要重新调整缓存信息，且不做预警
					if((recordTime.getTime() -lastRecordTime.getTime()) > 1000*60*10){
						list =  new ArrayList();
						list.add(alarm);
						RedisPoolUtil.hset("alarm:"+key, vp.getDeviceSysNbr(),JSONArray.toJSONString(list));
						return;
					}
				}
			}
			//如果正好两个周期时，第一个预警，第二个也预警时，产生报警
			if(list.size() == 1){
				WeatherAlarm  firstAlarm = list.get(0);
				if(firstAlarm != null && alarm != null){
					if(!"0".equals(firstAlarm.getAlarmGrade())||!"0".equals(alarm.getAlarmGrade())){
						//发布预警
						warnData(alarm,key);
					}
				}
			}
			//大于两个周期，才做预警
			if(list.size() > 1){
				//第一次数据
				WeatherAlarm  firstAlarm = list.get(0);
				//不为空开始计算预警
				if(firstAlarm != null && lastAlarm != null){
					//firstAlarm 状态正常，则lastAlarm和 alarm同时超过阈值则预警
					if("0".equals(firstAlarm.getAlarmGrade())){
						if(!"0".equals(lastAlarm.getAlarmGrade())&&!"0".equals(alarm.getAlarmGrade())){
							//发布预警
							warnData(alarm,key);
						}
					}else{
						//第二个、第三个正常 则灭警
						if("0".equals(lastAlarm.getAlarmGrade())&&"0".equals(alarm.getAlarmGrade())){
							//推送信息但不入库
							MQGateWay.publish("its_"+key+"_alarm",JSONObject.toJSONString(alarm));
						}
						//第二个 第三个相同预警，更新第二次预警的结束时间
						if(lastAlarm.getAlarmGrade().equals(alarm.getAlarmGrade())&&!"0".equals(alarm.getAlarmGrade())){
							//更新持续时间
							warnUpdateData(lastAlarm,alarm);
						}else{
							//发布预警
							if(!"0".equals(lastAlarm.getAlarmGrade())&&!"0".equals(alarm.getAlarmGrade())){
								warnData(alarm,key);
							}
						}
					}
				}
			}
			if(list.size()>=2){
				list.remove(0);
			}
			list.add(alarm);
			//存放回缓存中
			RedisPoolUtil.hset("alarm:"+key, vp.getDeviceSysNbr(),JSONArray.toJSONString(list));
		   }
	}
	private void warnData(WeatherAlarm alarm,String key){
		try {
			//只要状态变化就需要插入新数据
			String  sql= AlarmUtil.getSql(alarm);
			//不为0，则插入数据，为零只做灭警
			DBUtil.execute(sql);
			//产生预警信息发布到MQ上
			MQGateWay.publish("its_"+key+"_alarm",JSONObject.toJSONString(alarm));
			
		} catch (IllegalArgumentException | IllegalAccessException e) {
			log.error("大风预警入库及推送失败！");
			log.error(e);
			//入库失败要返回正常，不然会循环发送
		}
	}
	/** 
	* @Title: warnUpdateData 
	* @Description: 更新预警持续时间
	* @param @param alarm  设定文件 
	* @return void 返回类型 
	*/
	private void warnUpdateData(WeatherAlarm lastAlarm,WeatherAlarm alarm){
		log.info("跟新状态持续时间！");
		DBUtil.execute(AlarmUtil.getUpdateSql(lastAlarm,alarm.getStartAlarmTime()));
	}

	/** 
	* @Title: getRoadSensorStatus 
	* @Description: 封装预警类 
	* @param @param vp
	* @param @param key
	* @param @return  设定文件 
	* @return WeatherAlarm  返回类型 
	* @throws 
	*/
	private WeatherAlarm getRoadSensorStatus(RoadSensor vp, String key) {
		WeatherAlarm  alarm= new WeatherAlarm();
		alarm.setAlarmEventId(AlarmUtil.generatorId());
		alarm.setDeviceSysNbr(vp.getDeviceSysNbr());
		alarm.setOrgPrivilegeCode(vp.getOrgPrivilegeCode());
		alarm.setOrgCode(vp.getOrgCode());
		alarm.setSiteCode(vp.getSiteCode());
		alarm.setStartAlarmTime(vp.getRecordTime());
		alarm.setEndAlarmTime(vp.getRecordTime());
		alarm.setDeviceId(vp.getDeviceId());
		
		alarm.setAlarmGrade("0");//无状态正常
		if(key.equals("temperature")){
			alarm.setAlarmEventType(AlarmUtil.alarmEventTypeW);
			alarm.setSubAlarmEvent(AlarmUtil.TYPE_5022);
			alarm.setWeatherData(vp.getRoadbedTemperature()+"");
			List<TrafficAlarmValue> list = CacheManager.getTrafficAlarmDataList(CacheManager.alermTyper);
			if(list == null){
				CacheManager.initWarnList(CacheManager.alermTyper,"");
				 CacheManager.getTrafficAlarmDataList(CacheManager.alermTyper);
			}
			for(TrafficAlarmValue tdv:list){
				if(vp.getRoadbedTemperature()>tdv.getMin() && vp.getRoadbedTemperature() <=tdv.getMax()){
					alarm.setAlarmGrade(tdv.getAlermGrade());
					alarm.setAlarmValueId(tdv.getMin()+","+tdv.getMax());
				}
			}
			alarm.setAlarmDesc("温度预警等级为："+alarm.getAlarmGrade());
		}
		if(key.equals("roadCondtion")){
			alarm.setAlarmEventType(AlarmUtil.alarmEventTypeW);
			alarm.setSubAlarmEvent(AlarmUtil.TYPE_5025);
			alarm.setWeatherData(vp.getRoadCondition()+"");
			List<TrafficAlarmValue> list = CacheManager.getTrafficAlarmDataList(CacheManager.alermTypeice);
			if(list == null){
				CacheManager.initWarnList(CacheManager.alermTypeice,"");
				list = CacheManager.getTrafficAlarmDataList(CacheManager.alermTypeice);
			}
			for(TrafficAlarmValue tdv:list){
				double config  = Double.valueOf(vp.getRoadCondition());
				if(config==tdv.getMax()){
					alarm.setAlarmGrade(vp.getRoadCondition());
					alarm.setAlarmValueId(tdv.getMax()+","+tdv.getMax());
				}
			}
			alarm.setAlarmDesc("道路状况预警等级为："+alarm.getAlarmGrade());
		}
		if(key.equals("waterThickness")){
			alarm.setAlarmEventType(AlarmUtil.alarmEventTypeW);
			alarm.setSubAlarmEvent(AlarmUtil.TYPE_5023);
			alarm.setWeatherData(vp.getWaterFileHeigh()+"");
			List<TrafficAlarmValue> list = CacheManager.getTrafficAlarmDataList(CacheManager.alermTypewt);
			if(list == null){
				CacheManager.initWarnList(CacheManager.alermTypeice,"");
				list = CacheManager.getTrafficAlarmDataList(CacheManager.alermTypewt);
			}
			for(TrafficAlarmValue tdv:list){
				if(vp.getWaterFileHeigh()>tdv.getMin() && vp.getWaterFileHeigh() <=tdv.getMax()){
					alarm.setAlarmGrade(tdv.getAlermGrade());
					alarm.setAlarmValueId(tdv.getMin()+","+tdv.getMax());
				}
			}
			alarm.setAlarmDesc("水膜厚度预警等级为："+alarm.getAlarmGrade());
		}
		return alarm;
	}
}
