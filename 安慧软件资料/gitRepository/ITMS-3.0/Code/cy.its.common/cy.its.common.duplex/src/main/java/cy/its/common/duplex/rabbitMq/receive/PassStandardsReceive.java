package cy.its.common.duplex.rabbitMq.receive;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONObject;

import cy.its.common.duplex.rabbitMq.IMqReceiver;
import cy.its.common.duplex.rabbitMq.IWebscoketReceiver;

/**
* @Title: PassStandardsReceive.java 
* @Package com.cy.rabbitMq.receive 
* @Description: 过车监控
* @author lil@cychina.cn
* @date 2015年11月19日 下午3:13:27 
* @version V1.0   
 */
public class PassStandardsReceive implements IMqReceiver{
	
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
		try {
			for(IWebscoketReceiver webscoketReceiver:list){
				JSONObject  jsonObject  = JSONObject.parseObject(message);
				status=webscoketReceiver.receive(jsonObject);
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
		synchronized(this){
			list.add(webscoketReceiver);
		}
		return true;
	}

}
