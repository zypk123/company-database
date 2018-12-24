package cy.its.service.analysisAlarm.module;

import java.util.Date;

import oracle.net.aso.a;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;

import cy.its.service.analysisAlarm.domain.GlobalProperty;
import cy.its.service.analysisAlarm.domain.WeatherAlarm;
import cy.its.service.common.rabbitmqClient.IReceiver;
import cy.its.service.common.rabbitmqClient.MQGateWay;
import cy.its.service.util.DBUtil;

/**
* @Title: TrafficEventModule.java 
* @Package cy.its.service.analysisAlarm.module 
* @Description:设备抓拍的事件，如果事件，延迟超过阈值则不做处理
* @author lil@cychina.cn
* @date 2015年12月16日 下午2:43:53 
* @version V1.0   
 */
public class TrafficEventModule implements IReceiver {
	/*
	 *日志
	 */
	private static  Logger log=Logger.getLogger(TrafficEventModule.class);
	
	@Override
	public Boolean receive(String routingKey, String message) {
		
		WeatherAlarm alarm = JSONObject.parseObject(message,WeatherAlarm.class);
		alarm.setAlarmEventId(AlarmUtil.generatorId());
		alarm.setAlarmGrade("-");
		alarm.setEndAlarmTime(alarm.getStartAlarmTime());
		try {
			Date startTime  = alarm.getStartAlarmTime();
			if(startTime != null ){
				DBUtil.execute(AlarmUtil.getSql(alarm));
				//小于半小时的数据，需要推送到界面
				if((new Date().getTime() -startTime.getTime()) < GlobalProperty.getDelayTime()*1000*60){
					//发送MQ消息
					MQGateWay.publish("its_traffic_event_alarm",JSONObject.toJSONString(alarm));
				}
			}
		} catch (IllegalArgumentException | IllegalAccessException e) {
			log.error("获取SQL语句出现问题！");
			log.error(e);
		}
		return true;
	}

}
