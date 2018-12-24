/**
 * @Title: ServerStatusSingleReceiver.java
 * @Package cy.its.common.duplex.rabbitMq.receive
 * @Description: TODO(这里要填写用途)
 * @author Administrator Administrator@cychina.cn
 * @date 2015年12月28日 下午4:04:43
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

import cy.its.common.duplex.mapData.ServerStatusMapData;
import cy.its.common.duplex.rabbitMq.IMqReceiver;
import cy.its.common.duplex.rabbitMq.IWebscoketReceiver;

/**
  * @ClassName: ServerStatusSingleReceiver
  * @Description: 单个服务器状态接收类
  * @author Administrator Administrator@cychina.cn
  * @date 2015年12月28日 下午4:04:43
  *
  */

public class ServerStatusSingleReceiver implements IMqReceiver{

	
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
    	JSONObject  jsonObject  = JSONObject.parseObject(message);
    	//更新缓存
    	ServerStatusMapData.updateServerStatus(jsonObject);
		for(IWebscoketReceiver webscoketReceiver:list){	
		    try{
		    	status=webscoketReceiver.receive(jsonObject);
		    }catch(Exception e){
		    	continue;
		    }
			
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
