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

public class TrafficSituationDevStatusMonitorScoket extends WebscoketServiceImplA implements IWebscoketReceiver {

	/*
	 * »’÷æ
	 */
	private Log4jFactoryProxy log = Log4jFactoryProxy.getSingleton(TrafficSituationDevStatusMonitorScoket.class);

	private final String routingKey = "its_status_result_for_upgrade";

	private final String monitorKey = "trafficSituationDevStatusMonitor";

	private TrafficSituationDevStatusMonitorScoket() {
		IMqReceiver receive = MQEntrance.getIreceivermap().get(routingKey);
		receive.addReciveObject(this);
	}

	@Override
	public Boolean receive(JSONObject jsonObject) {
		CopyOnWriteArrayList<WebscoketSessionBean> listbean = maplistbean.get(monitorKey);
		listbean.stream().forEach(wsb -> {
			String orgCode = wsb.getOrgCode();
			String orgPrivilegeCode = jsonObject.getString("orgPrivilegeCode");
			if (orgPrivilegeCode.startsWith(orgCode)) {

				List<String> deviceTypes = (List<String>) wsb.getParam().get("devTypes");
				String deviceType = jsonObject.getString("deviceType");
				if (deviceTypes.contains(deviceType)) {
					JSONObject obj = new JSONObject();
					obj.put("wbskey", monitorKey);
					obj.put(monitorKey, jsonObject.toJSONString());
					TextMessage returnMessage = new TextMessage(obj.toJSONString());
					this.sendMessage(wsb, returnMessage);
				}
			}
		});
		return true;
	}

}
