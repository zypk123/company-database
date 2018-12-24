package cy.its.common.duplex.service;

import java.util.Map;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import cy.its.common.duplex.domain.WebscoketSessionBean;

/**
 * @author lilei
 * 服务接口类，定义几个方法
 */
public interface WebscoketServiceI {
	/**
	 * @param wsb
	 * 增加
	 */
	public void  addWebscoketSessionBean(WebscoketSessionBean wsb,String key);
	
	/**
	 * @param wsb
	 * 修改
	 */
	public void  updateWebscoketSessionBean(WebSocketSession ws,Map param,String key);
	
	
	/**
	 * @param wsb
	 * 删除
	 */
	public void  deleteWebscoketSessionBean(WebSocketSession ws,String key);
	
	/**
	 * @param ws
	 * @param txMessage
	 * @return
	 * 调用接口发送消息
	 */
	public void  sendMessage(WebscoketSessionBean wb,TextMessage txMessage);
	
	/** 
	* @Title: deleteWebscoketSessionParam 
	* @Description: 删除参数 
	* @param @param wss
	* @param @param wsbMap    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	public void  deleteWebscoketSessionParam(WebSocketSession wss, Map wsbMap);

	/** 
	* @Title: addWebscoketSessionParam 
	* @Description:增加参数
	* @param @param wss
	* @param @param wsbMap    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	public void addWebscoketSessionParam(WebSocketSession wss, Map wsbMap);
	
}
