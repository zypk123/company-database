package cy.its.common.duplex.service;

import java.util.Date;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.web.socket.TextMessage;

import com.alibaba.fastjson.JSONObject;

import cy.its.common.duplex.domain.WebscoketSessionBean;
import cy.its.common.duplex.rabbitMq.IMqReceiver;
import cy.its.common.duplex.rabbitMq.IWebscoketReceiver;
import cy.its.common.duplex.rabbitMq.MQEntrance;
import cy.its.common.duplex.service.impl.WebscoketServiceImplA;
import cy.its.platform.common.utils.Log4jFactoryProxy;
import cy.its.platform.common.utils.RedisPoolUtil;

/**
* @Title: TrafficStatusScoket.java 
* @Package com.cy.task.service 
* @Description: 路况状态实时推送
* @author lil@cychina.cn
* @date 2015年10月30日 上午9:47:28 
* @version V1.0   
 */
public class TrafficStatusScoket extends WebscoketServiceImplA implements IWebscoketReceiver{
	/*
	 * 日志
	 */
	private Log4jFactoryProxy log = Log4jFactoryProxy.getSingleton(TrafficStatusScoket.class);
	
	
	private final String routingKey="its_region_speed";
	
	/**
	 * 两个KEY可以是同一个
	 */
	private final String monitorKey="trafficStatus";
	
	
	private TrafficStatusScoket(){
		IMqReceiver receive = MQEntrance.getIreceivermap().get(routingKey);
		receive.addReciveObject(this);
	}
	
	
	@Override
	public Boolean receive(JSONObject  jsonObject) {
		//数据不多可以直接取，不在做缓存
		CopyOnWriteArrayList<WebscoketSessionBean> listbean = maplistbean.get(monitorKey);
		String  id = jsonObject.getString("regionalId");
		String  manualState = RedisPoolUtil.get("ms:"+id);
		if(manualState != null){
			JSONObject manualJSON  = JSONObject.parseObject(manualState);
			Long start=manualJSON.getDate("startTime").getTime();
			Long end=manualJSON.getDate("endTime").getTime();			
			if(new Date().getTime()>=start && new Date().getTime()<=end){
				jsonObject.put("trafficState", manualJSON.get("trafficState"));
			}
			
		}
		listbean.stream().forEach(wsb->{
			String orgCode = wsb.getOrgCode();
		    //String  orgAuthorityCode = jsonObject.getString("orgAuthorityCode");
		    if(true){//orgAuthorityCode.startsWith(orgCode)){
		    	JSONObject  obj   = new JSONObject();
		    	obj.put("wbskey", monitorKey);
		    	obj.put(monitorKey,jsonObject);
		    	TextMessage returnMessage = new TextMessage(obj.toJSONString());
		    	this.sendMessage(wsb, returnMessage);
		    }
        });
		return true;
	}

}
