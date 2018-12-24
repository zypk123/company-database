package cy.its.service.analysisAlarm.module;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;

import cy.its.service.analysisAlarm.domain.TrafficSectionFlow;
import cy.its.service.analysisAlarm.domain.WeatherAlarm;
import cy.its.service.common.rabbitmqClient.IReceiver;
import cy.its.service.common.rabbitmqClient.MQGateWay;
import cy.its.service.util.DBUtil;
import cy.its.service.util.RedisPoolUtil;
import cy.its.service.util.StringUtils;

/**
* @Title: SectionFlowModule.java 
* @Package cy.its.service.analysisAlarm.module 
* @Description: 断流预警信息
* @author lil@cychina.cn
* @date 2015年12月22日 下午2:13:37 
* @version V1.0   
 */
public class SectionFlowModule  implements IReceiver {
	/*
	 *日志
	 */
	private static  Logger log=Logger.getLogger(SectionFlowModule.class);
	
	/* (non-Javadoc)
	 * @see cy.its.service.common.rabbitmqClient.IReceiver#receive(java.lang.String, java.lang.String)
	 * 断流预警很简单，只要判定流量为0 就行
	 */
	@Override
	public Boolean receive(String routingKey, String message) {
		// TODO Auto-generated method stub
		TrafficSectionFlow tf = JSONObject.parseObject(message,TrafficSectionFlow.class);
//		String tempstr  = "52f7eef07523489dbfaffd81fe430267,9aff670a0a374f6abebc7014e46ccc08,71c61c3c272a4daaa0fa080538e8d75f";
		String  jsonStr  = RedisPoolUtil.hget("zeroFlow", tf.getSectionId());
		WeatherAlarm  alarm = getTrafficSectionAlarm(tf);
//		if(tempstr.indexOf(tf.getSectionId()) >-1){
//			MQGateWay.publish("its_section_alarm",JSONObject.toJSONString(alarm));
//			return true;
//		}
		WeatherAlarm  lastAlarm  = null;
		if(!StringUtils.isEmpty(jsonStr)){
			lastAlarm  = JSONObject.parseObject(jsonStr, WeatherAlarm.class);
		}
		//数量为0 则为断流
		if(tf.getTotalNum() == 0){
			String sql;
			try {
				if(lastAlarm != null && lastAlarm.getWeatherData().equals("0")){
					sql = AlarmUtil.getUpdateSql(lastAlarm, alarm.getStartAlarmTime());
				}else{
					sql = AlarmUtil.getSql(alarm);
				}
				//执行插入记录
				DBUtil.execute(sql);
				//产生预警信息发布到MQ上
				MQGateWay.publish("its_section_alarm",JSONObject.toJSONString(alarm));
				//保存数据到redis 
				RedisPoolUtil.hset("zeroFlow",alarm.getSectionId(),JSONObject.toJSONString(alarm));
				
			} catch (IllegalArgumentException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				log.error("断流预警入库失败！"+tf.getSectionId()+":"+tf.getUpdateTime());
				log.error(e);
			}
		}else{
			if(lastAlarm != null && lastAlarm.getWeatherData().equals("0")){
				//发布消息消除预警
				MQGateWay.publish("its_section_alarm",JSONObject.toJSONString(alarm));
				//保存当前状态
				RedisPoolUtil.hset("zeroFlow",alarm.getSectionId(),JSONObject.toJSONString(alarm));
			}
		}
		return true;
	}
	
	/** 
	* @Title: getVisibilityStatus 
	* @Description:获取风速阈值
	* @param @param vp 设定文件 
	* @return WeatherAlarm 返回类型 
	* @throws 
	*/
	private WeatherAlarm getTrafficSectionAlarm(TrafficSectionFlow tf) {
		WeatherAlarm  alarm= new WeatherAlarm();
		alarm.setAlarmEventId(AlarmUtil.generatorId());
		alarm.setSectionId(tf.getSectionId());
		alarm.setOrgPrivilegeCode(tf.getOrgPrivilegeCode());
		alarm.setStartAlarmTime(tf.getUpdateTime());
		alarm.setEndAlarmTime(tf.getUpdateTime());
		alarm.setAlarmEventType(AlarmUtil.alarmEventTypeF);
		alarm.setSubAlarmEvent(AlarmUtil.TYPE_50602);	
		alarm.setWeatherData("0");
		alarm.setAlarmGrade("1");//无状态正常
		alarm.setAlarmValueId("0");
		alarm.setAlarmDesc("断流预警等级为："+alarm.getAlarmGrade());
		return alarm;
	}

}
