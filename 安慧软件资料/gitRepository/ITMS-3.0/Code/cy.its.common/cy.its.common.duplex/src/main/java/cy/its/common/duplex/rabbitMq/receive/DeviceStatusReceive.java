package cy.its.common.duplex.rabbitMq.receive;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONObject;

import cy.its.common.duplex.mapData.DeviceStatusMapData;
import cy.its.common.duplex.rabbitMq.IMqReceiver;
import cy.its.common.duplex.rabbitMq.IWebscoketReceiver;

/**
* @Title: DeviceStatus.java 
* @Package com.cy.rabbitMq.receive 
* @Description: 设备状态
* @author lil@cychina.cn
* @date 2015年11月19日 下午3:28:12 
* @version V1.0   
 */
public class DeviceStatusReceive implements IMqReceiver{
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
    	DeviceStatusMapData.updateDevStatus(jsonObject);
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
