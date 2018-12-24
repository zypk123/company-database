package cy.its.common.duplex.service;

import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.web.socket.TextMessage;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cy.its.common.duplex.domain.WebscoketSessionBean;
import cy.its.common.duplex.rabbitMq.IMqReceiver;
import cy.its.common.duplex.rabbitMq.IWebscoketReceiver;
import cy.its.common.duplex.rabbitMq.MQEntrance;
import cy.its.common.duplex.service.impl.WebscoketServiceImplA;
import cy.its.platform.common.utils.Log4jFactoryProxy;

/**
* @Title: SurveillanceComparisonScoket.java 
* @Package com.cy.task.service 
* @Description:布控比对的双通服务
* @author lil@cychina.cn
* @date 2015年10月27日 下午7:35:02 
* @version V1.0   
 */
public class SurveillanceComparisonScoket extends WebscoketServiceImplA implements IWebscoketReceiver{
	
	/*
	 * 日志
	 */
	private Log4jFactoryProxy log = Log4jFactoryProxy.getSingleton(SurveillanceComparisonScoket.class);
	
	
	private final String routingKey="comparison_services";
	/**
	 * 两个KEY可以是同一个
	 */
	private final String monitorKey="surveillanceComparison";

	private SurveillanceComparisonScoket(){
		IMqReceiver receive = MQEntrance.getIreceivermap().get(routingKey);
		receive.addReciveObject(this);
	}

	@Override
	public Boolean receive(JSONObject  jsonObject) {
		//一个预警信息可能包含多个预警信息所以需要统一处理
		JSONArray   array  = jsonObject.getJSONArray("vlist");
		jsonObject.remove("vlist");
		for(int i=0;i<array.size();i++){
			JSONObject  newJsonobj  = array.getJSONObject(i);
			newJsonobj.putAll(jsonObject);
			singleSend(newJsonobj);
		}
		return true;
	}


	private void singleSend(JSONObject newJsonobj) {
		//数据不多可以直接取，不在做缓存
		CopyOnWriteArrayList<WebscoketSessionBean> listbean = maplistbean.get(monitorKey);
		
		listbean.stream().forEach(wsb->{
		    String  trackCharacter = newJsonobj.getString("trackCharacter");
		    if("2".equals(trackCharacter)){//秘控只发给自己
		    	String  userName  = newJsonobj.getString("trackName");
		    	if(userName.equals(wsb.getLoginName())){
			    	JSONObject  obj   = new JSONObject();
			    	obj.put("wbskey",monitorKey);
			    	obj.put(monitorKey, newJsonobj.toJSONString());
			    	TextMessage returnMessage = new TextMessage(obj.toJSONString());
			    	this.sendMessage(wsb, returnMessage);
		    	}
		    }else{
		    	//发给本级及以上的订阅用户
		    	String  orgPrivilegeCode  = newJsonobj.getString("orgPrivilegeCode");
		    	String  orgCode  = wsb.getOrgCode()==null?"-":wsb.getOrgCode();
		    	if(orgPrivilegeCode.startsWith(orgCode)){
			    	JSONObject  obj   = new JSONObject();
			    	obj.put("wbskey",monitorKey);
			    	obj.put(monitorKey, newJsonobj.toJSONString());
			    	TextMessage returnMessage = new TextMessage(obj.toJSONString());
			    	this.sendMessage(wsb, returnMessage);
		    	}
		    }
        });
	}
}
