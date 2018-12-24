/**
 * @Title: TrafficWeatherScoket.java
 * @Package cy.its.common.duplex.service
 * @Description: TODO(这里要填写用途)
 * @author gyf guanyf@cychina.cn
 * @date 2015年12月9日 下午3:09:37
 * @version V1.0
 * @Revision : $Rev$
 * @Id: $Id$
 *
 * Company: 安徽超远信息技术有限公司
 * Copyright: Copyright (c) 2015 
 */

package cy.its.common.duplex.service;

import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.web.socket.TextMessage;

import com.alibaba.fastjson.JSONObject;

import cy.its.common.duplex.domain.WebscoketSessionBean;
import cy.its.common.duplex.rabbitMq.IMqReceiver;
import cy.its.common.duplex.rabbitMq.IWebscoketReceiver;
import cy.its.common.duplex.rabbitMq.MQEntrance;
import cy.its.common.duplex.service.impl.WebscoketServiceImplA;
import cy.its.platform.common.utils.Log4jFactoryProxy;

public class TrafficWeatherScoket extends WebscoketServiceImplA implements IWebscoketReceiver{
	/*
	 * 日志
	 */
	private Log4jFactoryProxy log = Log4jFactoryProxy.getSingleton(TrafficWeatherScoket.class);
	
	private final String routingKey="its_weather";
	/**
	 * 两个KEY可以是同一个
	 */
	private final String monitorKey="trafficWeather";
	
	private TrafficWeatherScoket(){
		IMqReceiver receive = MQEntrance.getIreceivermap().get(routingKey);
		receive.addReciveObject(this);
	}
	
	@Override
	public Boolean receive(JSONObject  jsonObject) {
		
		CopyOnWriteArrayList<WebscoketSessionBean> listbean = maplistbean.get(monitorKey);
		
		listbean.stream().forEach(wsb->{
			String orgCode = wsb.getOrgCode();
		    String  orgAuthorityCode = jsonObject.getString("orgAuthorityCode");
		    //if(orgAuthorityCode.startsWith(orgCode)){
		    	JSONObject  obj   = new JSONObject();
		    	obj.put("wbskey", monitorKey);
		    	obj.put(monitorKey, jsonObject.toJSONString());
		    	TextMessage returnMessage = new TextMessage(obj.toJSONString());
		    	this.sendMessage(wsb, returnMessage);
		    //}
        });
		return true;
	}
}
