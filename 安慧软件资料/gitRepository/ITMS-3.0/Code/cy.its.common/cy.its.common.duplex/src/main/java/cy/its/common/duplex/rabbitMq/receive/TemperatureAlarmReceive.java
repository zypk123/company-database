/**
 * @Title: TemperatureAlarmReceive.java
 * @Package cy.its.common.duplex.rabbitMq.receive
 * @Description: TODO(这里要填写用途)
 * @author gyf guanyf@cychina.cn
 * @date 2015年12月17日 下午4:44:02
 * @version V1.0
 * @Revision : $Rev$
 * @Id: $Id$
 *
 * Company: 安徽超远信息技术有限公司
 * Copyright: Copyright (c) 2015 
 */

package cy.its.common.duplex.rabbitMq.receive;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONObject;

import cy.its.common.duplex.rabbitMq.IMqReceiver;
import cy.its.common.duplex.rabbitMq.IWebscoketReceiver;

public class TemperatureAlarmReceive implements IMqReceiver{
	/**
	 * 临时保存注册信息
	 */
    private  List<IWebscoketReceiver> list = new ArrayList<IWebscoketReceiver>();
    /**
     * 向接收类发送信息
     */
    @Override
	public Boolean receive(String routingKey,String message) {
    	boolean status = true;
		for(IWebscoketReceiver webscoketReceiver:list){
			JSONObject  jsonObject  = JSONObject.parseObject(message);
			status=webscoketReceiver.receive(jsonObject);
		}
		return status;
	}

	
	/**
	 * 注册一个接收类
	 */
	@Override
	public boolean addReciveObject(IWebscoketReceiver webscoketReceiver) {
		synchronized(this){
			list.add(webscoketReceiver);
		}
		return true;
	}
}
