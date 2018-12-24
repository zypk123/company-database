/**
 * @Title: DeviceMonitorScoket.java
 * @Package com.cy.task.service
 * @Description: 设备状态监控的 webscoket 服务类
 * @author chenzhiying chenzy@cychina.cn
 * @date 2015年11月18日 下午8:41:28
 * @version V1.0
 * @Revision : $Rev$
 * @Id: $Id$
 *
 * Company: 安徽超远信息技术有限公司
 * Copyright: Copyright (c) 2015 
 */

package cy.its.common.duplex.service;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.web.socket.TextMessage;

import com.alibaba.fastjson.JSONObject;

import cy.its.common.duplex.domain.WebscoketSessionBean;
import cy.its.common.duplex.rabbitMq.IMqReceiver;
import cy.its.common.duplex.rabbitMq.IWebscoketReceiver;
import cy.its.common.duplex.rabbitMq.MQEntrance;
import cy.its.common.duplex.service.impl.WebscoketServiceImplA;
import cy.its.platform.common.utils.Log4jFactoryProxy;

/**
  * @ClassName: DeviceMonitorScoket
  * @Description: 视频设备状态监控的 webscoket 服务类
  * @author jinhaibo jinhb@cychina.cn
  * @date 2015年11月18日 下午8:41:28
  *
  */

public class RealVideoMonitorSocket  extends WebscoketServiceImplA implements IWebscoketReceiver{
	
	/*
	 * 日志
	 */
	private Log4jFactoryProxy log = Log4jFactoryProxy.getSingleton(DeviceMonitorScoket.class);
	
	
	private final String routingKey="its_status_result_for_upgrade";
	
	/**
	 * 两个KEY可以是同一个
	 */
	private final String monitorKey="realVideoMonitor";
	
	
	private RealVideoMonitorSocket(){
		IMqReceiver receive = MQEntrance.getIreceivermap().get(routingKey);
		receive.addReciveObject(this);
	}
	/*
	  * <p>Title: receive</p>
	  * <p>Description: </p>
	  * @param arg0
	  * @param arg1
	  * @return
	  * @see cy.its.service.common.rabbitmqClient.IReceiver#receive(java.lang.String, java.lang.String)
	  */	
	
	@Override
	public Boolean receive(JSONObject  jsonObject) {
		//数据不多可以直接取，不在做缓存
		CopyOnWriteArrayList<WebscoketSessionBean> listbean = maplistbean.get(monitorKey);
		
		listbean.stream().forEach(wsb->{
			List<String> temp = (List<String>) wsb.getParam().get("devSysNbrs");
			if (temp.contains(jsonObject.get("deviceSysNbr").toString())) {
		    	JSONObject  obj   = new JSONObject();
		    	obj.put("wbskey", monitorKey);
		    	obj.put(monitorKey, jsonObject.toJSONString());
		    	TextMessage returnMessage = new TextMessage(obj.toJSONString());
		    	this.sendMessage(wsb, returnMessage);
		    }
        });
		return true;
	}

}
