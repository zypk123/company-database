package cy.its.common.duplex.service.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import cy.its.common.duplex.domain.WebscoketSessionBean;
import cy.its.common.duplex.service.WebscoketServiceI;
import cy.its.platform.common.utils.Log4jFactoryProxy;

/**
* @Title: WebscoketServiceImplA.java 
* @Package com.cy.webscoket.service.impl 
* @Description: webscoket基础类 
* @author lil@cychina.cn
* @date 2015年11月2日 下午1:42:10 
* @version V1.0   
 */
public abstract class WebscoketServiceImplA implements WebscoketServiceI{
    
	private Log4jFactoryProxy log = Log4jFactoryProxy.getSingleton(WebscoketServiceImplA.class);
	
	public static  Map<String,CopyOnWriteArrayList<WebscoketSessionBean>> maplistbean;
	
	static{
		maplistbean = new HashMap();
	}
	/**
	 * @param wsb
	 * 增加处理链接
	 */
	@Override
	public void addWebscoketSessionBean(WebscoketSessionBean wsb,String key) {
		CopyOnWriteArrayList<WebscoketSessionBean>  list = maplistbean.get(key);
		if(list == null){
			list = new CopyOnWriteArrayList<WebscoketSessionBean>();
			list.add(wsb);
			maplistbean.put(key, list);
		}else{
			boolean flag = false;
			for(WebscoketSessionBean obj:list){
				if(obj.getWs().equals(wsb.getWs())){
					flag = true;
				}
			}
			if(!flag){
				list.add(wsb);
			}
	    }
	}
	
	
	/**
	 * @param ws
	 * @param param
	 * 更新参数
	 */
	@Override
	public void updateWebscoketSessionBean(WebSocketSession ws,Map param,String key) {
		Object  obj = maplistbean.get(key);
		if(obj != null ){
			CopyOnWriteArrayList<WebscoketSessionBean> listbean=(CopyOnWriteArrayList<WebscoketSessionBean>)obj;
			listbean.stream().forEach(p->{if(p.equals(ws)){p.setParam(param);}});
		}
	}

	/**
	 * @param wsb
	 * 删除链接
	 */
	@Override
	public void deleteWebscoketSessionBean(WebSocketSession ws,String key) {
		CopyOnWriteArrayList<WebscoketSessionBean> listbean=null;
		Object  obj = maplistbean.get(key);
		if(obj != null ){
			 listbean=(CopyOnWriteArrayList<WebscoketSessionBean>)obj;
		}else{
			return;//为空时，直接不做处理
		}
		int  index=-1;
		int  i=0;
		for(WebscoketSessionBean  temp:listbean){
			if(temp.equals(ws)){
				index = i;
				break;
			}
			i++;
		}
		if(index >-1){
			listbean.remove(index);
		}
	}
	
	/**
	 * @param ws
	 * @param txMessage
	 * 发送消息
	 */
	@Override
	public void  sendMessage(WebscoketSessionBean wb,TextMessage txMessage) {
		WebSocketSession  ws = wb.getWs();
		try {
			if(ws.isOpen()){
				ws.sendMessage(txMessage);
			}else{
				deleteWebscoketSessionBean(ws,wb.getKey());
			}
		} catch (IOException e) {
			log.error(e);
		}
	}
	
	/*
	 *如果需要子类可重写父类的方法
	 */
	@Override
	public void deleteWebscoketSessionParam(WebSocketSession wss, Map wsbMap) {

	}
	/*
	 * 如果需要子类可重写父类的方法
	 */
	@Override
	public void addWebscoketSessionParam(WebSocketSession wss, Map wsbMap) {
	}
}
