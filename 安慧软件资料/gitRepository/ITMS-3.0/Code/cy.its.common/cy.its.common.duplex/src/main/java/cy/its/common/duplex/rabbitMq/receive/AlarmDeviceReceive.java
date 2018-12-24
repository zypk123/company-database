package cy.its.common.duplex.rabbitMq.receive;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONObject;

import cy.its.common.duplex.rabbitMq.IMqReceiver;
import cy.its.common.duplex.rabbitMq.IWebscoketReceiver;

/**
 * 设备状态报警
 * @author dell
 *
 */
public class AlarmDeviceReceive implements IMqReceiver{
	/**
	 * 临时保存注册信息
	 */
	private List<IWebscoketReceiver> list = new ArrayList<IWebscoketReceiver>();

	/**
	 * 向接收类发送信息
	 */
	@Override
	public Boolean receive(String routingKey, String message) {
		boolean status = true;
		try {
			for (IWebscoketReceiver webscoketReceiver : list) {
				JSONObject jsonObject = JSONObject.parseObject(message);
				status = webscoketReceiver.receive(jsonObject);
			}
			return status;
		} catch (Exception e) {
			return true;
		}
	}

	/**
	 * 注册一个接收类
	 */
	@Override
	public boolean addReciveObject(IWebscoketReceiver webscoketReceiver) {
		synchronized (this) {
			list.add(webscoketReceiver);
		}
		return true;
	}

}
