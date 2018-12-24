package cy.its.common.duplex.rabbitMq.receive;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cy.its.common.duplex.rabbitMq.IMqReceiver;
import cy.its.common.duplex.rabbitMq.IWebscoketReceiver;
import cy.its.platform.common.utils.RedisPoolUtil;

/**
* @Title: TrafficStatus.java 
* @Package com.cy.rabbitMq.receive 
* @Description: 路况
* @author lil@cychina.cn
* @date 2015年11月19日 下午3:19:19
* @version V1.0   
 */
public class TrafficStatusReceive implements IMqReceiver{
	
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
		for(IWebscoketReceiver webscoketReceiver:list){
			status=webscoketReceiver.receive(jsonObject);
		}
		//最新状态数据放入redis
		RedisPoolUtil.hset("tf:region",jsonObject.getString("regionalId"), message);
		//缓存更新完成
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