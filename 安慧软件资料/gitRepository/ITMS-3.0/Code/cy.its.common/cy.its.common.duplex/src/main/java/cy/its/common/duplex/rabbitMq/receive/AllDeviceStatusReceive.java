/**
 * @Title: AllDeviceStatusReceive.java
 * @Package cy.its.common.duplex.rabbitMq.receive
 * @Description: TODO(这里要填写用途)
 * @author Administrator Administrator@cychina.cn
 * @date 2015年12月22日 下午9:39:53
 * @version V1.0
 * @Revision : $Rev$
 * @Id: $Id$
 *
 * Company: 安徽超远信息技术有限公司
 * Copyright: Copyright (c) 2015 
 */

package cy.its.common.duplex.rabbitMq.receive;

import cy.its.common.duplex.mapData.DeviceStatusMapData;
import cy.its.common.duplex.rabbitMq.IMqReceiver;
import cy.its.common.duplex.rabbitMq.IWebscoketReceiver;

/**
  * @ClassName: AllDeviceStatusReceive
  * @Description: TODO(这里要填写用途)
  * @author Administrator Administrator@cychina.cn
  * @date 2015年12月22日 下午9:39:53
  *
  */

public class AllDeviceStatusReceive implements IMqReceiver {

	/*
	  * <p>Title: receive</p>
	  * <p>Description: </p>
	  * @param routingKey
	  * @param message
	  * @return
	  * @see cy.its.service.common.rabbitmqClient.IReceiver#receive(java.lang.String, java.lang.String)
	  */
	
	
	@Override
	public Boolean receive(String routingKey, String message) {
		DeviceStatusMapData.load(message);
		return true;
	}

	/*
	  * <p>Title: addReciveObject</p>
	  * <p>Description: </p>
	  * @param webscoketReceiver
	  * @return
	  * @see cy.its.common.duplex.rabbitMq.IMqReceiver#addReciveObject(cy.its.common.duplex.rabbitMq.IWebscoketReceiver)
	  */
	
	
	@Override
	public boolean addReciveObject(IWebscoketReceiver webscoketReceiver) {
		// TODO Auto-generated method stub
		return false;
	}

}
