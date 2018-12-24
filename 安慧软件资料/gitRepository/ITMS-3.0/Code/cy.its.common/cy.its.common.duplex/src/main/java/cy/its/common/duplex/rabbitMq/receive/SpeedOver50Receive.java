package cy.its.common.duplex.rabbitMq.receive;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONObject;

import cy.its.common.duplex.rabbitMq.IMqReceiver;
import cy.its.common.duplex.rabbitMq.IWebscoketReceiver;

public class SpeedOver50Receive implements IMqReceiver {

	/**
	 * 临时保存注册信息
	 */
	private List<IWebscoketReceiver> list = new ArrayList<IWebscoketReceiver>();

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

	@Override
	public boolean addReciveObject(IWebscoketReceiver webscoketReceiver) {
		synchronized (this) {
			list.add(webscoketReceiver);
		}
		return true;
	}

}
