package cy.its.common.duplex.rabbitMq.receive;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONObject;

import cy.its.common.duplex.rabbitMq.IMqReceiver;
import cy.its.common.duplex.rabbitMq.IWebscoketReceiver;

/**
* @Title: ComparisonReceive.java 
* @Package com.cy.rabbitMq.receive 
* @Description:布控比对 
* @author lil@cychina.cn
* @date 2015年11月19日 下午3:19:30 
* @version V1.0   
 */
public class ComparisonReceive implements IMqReceiver{
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