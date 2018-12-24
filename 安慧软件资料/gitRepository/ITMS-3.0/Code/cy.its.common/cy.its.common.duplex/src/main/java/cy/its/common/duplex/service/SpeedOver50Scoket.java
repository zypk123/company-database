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

public class SpeedOver50Scoket extends WebscoketServiceImplA implements IWebscoketReceiver{
	
	private Log4jFactoryProxy log = Log4jFactoryProxy.getSingleton(SpeedOver50Scoket.class);

	private final String routingKey = "RegionalSpeed";
	/**
	 * 两个KEY可以是同一个
	 */
	private final String monitorKey = "speedOver50Alarm";

	private SpeedOver50Scoket() {
		IMqReceiver receive = MQEntrance.getIreceivermap().get(routingKey);
		receive.addReciveObject(this);
	}

	@Override
	public Boolean receive(JSONObject jsonObject) {
		CopyOnWriteArrayList<WebscoketSessionBean> listbean = maplistbean.get(monitorKey);
		listbean.stream().forEach(wsb -> {
			String orgPrivCode = (String) wsb.getParam().get("orgPrivCode");
			//判断报警类型,只接受设备故障报警
			if(orgPrivCode != null && !orgPrivCode.isEmpty()
					&&  jsonObject.getString("orgPrivilegeCode").startsWith(orgPrivCode)){
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
