/**
 * @Title: ServerMgrMonitorScoket.java
 * @Package cy.its.common.duplex.service
 * @Description: TODO(这里要填写用途)
 * @author Administrator Administrator@cychina.cn
 * @date 2015年12月28日 下午7:39:04
 * @version V1.0
 * @Revision : $Rev$
 * @Id: $Id$
 *
 * Company: 安徽超远信息技术有限公司
 * Copyright: Copyright (c) 2015 
 */

package cy.its.common.duplex.service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import com.alibaba.fastjson.JSONObject;

import cy.its.common.duplex.domain.WebscoketSessionBean;
import cy.its.common.duplex.rabbitMq.IMqReceiver;
import cy.its.common.duplex.rabbitMq.IWebscoketReceiver;
import cy.its.common.duplex.rabbitMq.MQEntrance;
import cy.its.common.duplex.service.impl.WebscoketServiceImplA;
import cy.its.platform.common.utils.Log4jFactoryProxy;

/**
  * @ClassName: ServerMgrMonitorScoket
  * @Description: 服务器状态监控websocket实现类
  * @author chenzhiying
  * @date 2015年12月28日 下午7:39:04
  * 
  */

public class ServerMgrMonitorScoket extends WebscoketServiceImplA implements IWebscoketReceiver  {

	/*
	 * 日志
	 */
	private Log4jFactoryProxy log = Log4jFactoryProxy.getSingleton(DeviceMonitorScoket.class);

	private final String routingKey = "its_server_status_result";

	
	private final String monitorKey = "serverMgrMonitor";

	private ServerMgrMonitorScoket() {
		IMqReceiver receive = MQEntrance.getIreceivermap().get(routingKey);
		receive.addReciveObject(this);
	}
	/*
	  * <p>Title: receive</p>
	  * <p>Description: </p>
	  * @param jsonObject
	  * @return
	  * @see cy.its.common.duplex.rabbitMq.IWebscoketReceiver#receive(com.alibaba.fastjson.JSONObject)
	  */
	
	@Override
	public Boolean receive(JSONObject jsonObject) {
		CopyOnWriteArrayList<WebscoketSessionBean> listbean = maplistbean.get(monitorKey);

		listbean.stream().parallel().forEach(wsb -> {
			List<String> temp = (List<String>) wsb.getParam().get("serverIds");
			if (temp.contains(jsonObject.get("serverId").toString())) {
				JSONObject obj = new JSONObject();
				obj.put("wbskey", monitorKey);
				obj.put(monitorKey, jsonObject.toString());
				TextMessage returnMessage = new TextMessage(obj.toJSONString());
				this.sendMessage(wsb, returnMessage);
			}
		});
		return true;
	}
	
	@Override
	public void addWebscoketSessionParam(WebSocketSession wss, Map wsbMap) {
		
		CopyOnWriteArrayList<WebscoketSessionBean> listbean = maplistbean.get(monitorKey);
		
		listbean.stream().filter(wbs->wbs.equals(wss)).forEach(bean->{
			 	Map map = bean.getParam();
			 	//把之前存储的设备列表清除，用新传递过来的设备参数列表替换，保存之
			 	List<String> temp =(List<String>) wsbMap.get("serverIds");
			 	map.remove("serverIds");
			 	map.put("serverIds", temp);
			}	
		);
	}

}
