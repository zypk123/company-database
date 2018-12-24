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
 * @Title: VehicleMonitoringScoket.java
 * @Package com.cy.task.service
 * @Description: 设备故障报警
 * @author lil@cychina.cn
 * @date 2015年10月18日 下午7:31:04
 * @version V1.0
 */
public class ServerAbnormalStateSocket extends WebscoketServiceImplA implements IWebscoketReceiver {

	private Log4jFactoryProxy log = Log4jFactoryProxy.getSingleton(ServerAbnormalStateSocket.class);

	private final String routingKey = "its_alarm_device";
	/**
	 * 两个KEY可以是同一个
	 */
	private final String monitorKey = "serverAbnormalState";

	private ServerAbnormalStateSocket() {
		IMqReceiver receive = MQEntrance.getIreceivermap().get(routingKey);
		receive.addReciveObject(this);
	}

	/**
	 * @Title: processMessage @Description: 接收信息 @param @param jsonObject
	 *         设定文件 @return void 返回类型 @throws
	 */
	public Boolean receive(JSONObject jsonObject) {

		CopyOnWriteArrayList<WebscoketSessionBean> listbean = maplistbean.get(monitorKey);

		listbean.stream().forEach(wsb -> {
			String orgPrivCode = (String) wsb.getParam().get("orgPrivCode");
			//判断报警类型,只接受服务器状态报警
			if(orgPrivCode != null && !orgPrivCode.isEmpty()
					&& "2".equals(jsonObject.getString("alarmType")) &&  jsonObject.getString("orgPrivilegeCode").startsWith(orgPrivCode)){
				JSONObject  obj   = new JSONObject();
		    	obj.put("wbskey", monitorKey);
		    	obj.put(monitorKey, jsonObject.toJSONString());
		    	TextMessage returnMessage = new TextMessage(obj.toJSONString());
		    	this.sendMessage(wsb, returnMessage);
			}
		});
		return true;
	}

	/**
	 * 判断元素是否在list中
	 * 
	 * @param array
	 *            列表
	 * @param index
	 *            检索元素
	 * @return
	 */
	private boolean inList(List<String> array, String index) {
		return array.stream().filter(item -> 
			item.equals(index)
		).count() > 0;
	}
}
