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
 * @Description: 过车监控的 webscoket 服务类
 * @author lil@cychina.cn
 * @date 2015年10月18日 下午7:31:04
 * @version V1.0
 */
public class DeviceVehicleMonitoringScoket extends WebscoketServiceImplA implements IWebscoketReceiver {

	private Log4jFactoryProxy log = Log4jFactoryProxy.getSingleton(DeviceVehicleMonitoringScoket.class);

	private final String routingKey = "its_pass_vehicle";
	/**
	 * 两个KEY可以是同一个
	 */
	private final String monitorKey = "deviceVehicleMonitor";

	private DeviceVehicleMonitoringScoket() {
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
			// 设备系统编码
			String deviceSysNbr = (String) wsb.getParam().get("deviceSysNbr");
			// 如果该点位是监控范围内的点位，且方向正确
			if (jsonObject.get("deviceSysNbr").equals(deviceSysNbr)) {
				JSONObject obj = new JSONObject();
				obj.put("wbskey", monitorKey);
				obj.put(monitorKey, jsonObject);
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
		return array.stream().filter(item -> item.equals(index)).count() > 0;
	}
}
