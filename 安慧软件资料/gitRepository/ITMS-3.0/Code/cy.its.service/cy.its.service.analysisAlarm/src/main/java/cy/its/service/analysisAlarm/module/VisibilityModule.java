package cy.its.service.analysisAlarm.module;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cy.its.service.analysisAlarm.domain.TrafficAlarmValue;
import cy.its.service.analysisAlarm.domain.VisibilityData;
import cy.its.service.analysisAlarm.domain.WeatherAlarm;
import cy.its.service.common.rabbitmqClient.IReceiver;
import cy.its.service.common.rabbitmqClient.MQGateWay;
import cy.its.service.util.DBUtil;
import cy.its.service.util.RedisPoolUtil;


/**
* @Title: VisibilityModule.java 
* @Package cy.its.service.analysisAlarm.module 
* @Description: 能见度预警,连续两个周期
* @author lil@cychina.cn
* @date 2015年12月16日 下午4:15:21 
* @version V1.0   
 */
public class VisibilityModule implements IReceiver{
	/*
	 *日志
	 */
	private static  Logger log=Logger.getLogger(VisibilityModule.class);
	
	/* (non-Javadoc)
	 * @see cy.its.service.com.rabbitmqClient.IReceiver#receive(java.lang.String, java.lang.String)
	 * 接收能见度数据
	 */
	@Override
	public Boolean receive(String arg0, String jsonObject) {
		//能见度
		VisibilityData vp = JSONObject.parseObject(jsonObject,VisibilityData.class);
	    //缓存中获取数据，数据结构为MAP key  field list
		//每个能见度仪保留6条数据
		//相邻的两个周期做预警判定
		//如果连续两个周期出现情况，则做预警
		String  deviceSysNbr  = vp.getDeviceSysNbr();
		String  jsonStr  = RedisPoolUtil.hget("alarm:visibility",deviceSysNbr);
		if(jsonStr == null){
			//第一次放入数据
			List<WeatherAlarm>  list  = new ArrayList();
			//计算当前数据的状态  alarmGrade = "0" 正常
			WeatherAlarm  alarm = getVisibilityStatus(vp);
			list.add(alarm);
			RedisPoolUtil.hset("alarm:visibility",vp.getDeviceSysNbr(),JSONArray.toJSONString(list));
		}else{
			//计算当前数据的状态  alarmGrade = "0" 无状态
			WeatherAlarm  alarm = getVisibilityStatus(vp);
			List<WeatherAlarm>  list= JSONArray.parseArray(jsonStr,WeatherAlarm.class);
			list  = list.stream().sorted((a,b)->{
				return (int) (a.getEndAlarmTime().getTime() - b.getEndAlarmTime().getTime());
			}).collect(Collectors.toList());
			//如果前一周期数据预警 这月周期数据也预警 则产生预警信息
			WeatherAlarm  lastAlarm = list.get(list.size()-1);
			//如果数据延迟则不做预警，直接舍弃
			Date  recordTime  = alarm.getStartAlarmTime();
			Date  lastRecordTime  = lastAlarm.getEndAlarmTime();
			if(recordTime == null || lastRecordTime == null){
				return true;
			}else{
				if(alarm.getStartAlarmTime().getTime() < lastAlarm.getEndAlarmTime().getTime()){
					return true;
				}else{
					//大于10分钟 数据出现问题，需要重新调整缓存信息，且不做预警
					if((recordTime.getTime() -lastRecordTime.getTime()) > 1000*60*10){
						list =  new ArrayList();
						list.add(alarm);
						RedisPoolUtil.hset("alarm:visibility", vp.getDeviceSysNbr(),JSONArray.toJSONString(list));
						return true;
					}
				}
			}
			//如果正好两个周期时，第一个预警，第二个也预警时，产生报警
			if(list.size() == 1){
				WeatherAlarm  firstAlarm = list.get(0);
				if(firstAlarm != null && alarm != null){
					if(!"0".equals(firstAlarm.getAlarmGrade())||!"0".equals(alarm.getAlarmGrade())){
						//发布预警
						warnData(alarm);
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
							warnData(alarm);
						}
					}else{
						//第二个、第三个正常 则灭警
						if("0".equals(lastAlarm.getAlarmGrade())&&"0".equals(alarm.getAlarmGrade())){
							//推送信息但不入库
							MQGateWay.publish("its_visibility_alarm",JSONObject.toJSONString(alarm));
						}
						//第二个 第三个相同预警，更新第二次预警的结束时间
						if(lastAlarm.getAlarmGrade().equals(alarm.getAlarmGrade())&&!"0".equals(alarm.getAlarmGrade())){
							//更新持续时间
							warnUpdateData(lastAlarm,alarm);
						}else{
							//发布预警
							if(!"0".equals(lastAlarm.getAlarmGrade())&&!"0".equals(alarm.getAlarmGrade())){
								warnData(alarm);
							}
						}
					}
				}
			}
			if(list.size() >= 2){
				list.remove(0);
			}
			list.add(alarm);
			//存放回缓存中
			RedisPoolUtil.hset("alarm:visibility",vp.getDeviceSysNbr(),JSONArray.toJSONString(list));
		}
		return true;
	}
	
	private void warnData(WeatherAlarm alarm){
		try {
			//只要状态变化就需要插入新数据
			String  sql= AlarmUtil.getSql(alarm);
			//不为0，则插入数据，为零只做灭警
			DBUtil.execute(sql);
			//产生预警信息发布到MQ上
			MQGateWay.publish("its_visibility_alarm",JSONObject.toJSONString(alarm));
			
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
	* @Title: getVisibilityStatus 
	* @Description:获取该能见度的阈值信息
	* @param @param vp 设定文件 
	* @return WeatherAlarm 返回类型 
	* @throws 
	*/
	private WeatherAlarm getVisibilityStatus(VisibilityData vp) {
		WeatherAlarm  alarm= new WeatherAlarm();
		alarm.setAlarmEventId(AlarmUtil.generatorId());
		alarm.setDeviceSysNbr(vp.getDeviceSysNbr());
		alarm.setDeviceId(vp.getDeviceId());
		alarm.setOrgPrivilegeCode(vp.getOrgPrivilegeCode());
		alarm.setOrgCode(vp.getOrgCode());
		alarm.setSiteCode(vp.getSiteCode());
		alarm.setStartAlarmTime(vp.getRecordTime());
		alarm.setEndAlarmTime(vp.getRecordTime());
		alarm.setAlarmEventType(AlarmUtil.alarmEventTypeW);
		alarm.setSubAlarmEvent(AlarmUtil.TYPE_5021);
		alarm.setAlarmGrade("0");//无状态正常
		alarm.setWeatherData(vp.getOneMinuteVisibility()+"");
		List<TrafficAlarmValue> list = CacheManager.getTrafficAlarmDataList(CacheManager.alermTypev);
		if(list==null){
			CacheManager.initWarnList(CacheManager.alermTypev,"");
			CacheManager.getTrafficAlarmDataList(CacheManager.alermTypev);
		}
		for(TrafficAlarmValue tdv:list){
			if(vp.getOneMinuteVisibility()>tdv.getMin() && vp.getOneMinuteVisibility() <=tdv.getMax()){
				alarm.setAlarmGrade(tdv.getAlermGrade());
				alarm.setAlarmValueId(tdv.getMin()+","+tdv.getMax());
			}
		}
		alarm.setAlarmDesc("能见度预警等级为："+alarm.getAlarmGrade());
		return alarm;
	}
	
}
