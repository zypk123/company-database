package cy.its.common.duplex.rabbitMq;

import cy.its.service.common.rabbitmqClient.IReceiver;

/**
* @Title: IMqReceiver.java 
* @Package com.cy.rabbitMq 
* @Description:集成 IReceiver 接口，需要定义注册接口
* @author lil@cychina.cn
* @date 2015年11月19日 下午2:48:49 
* @version V1.0   
 */
public interface IMqReceiver extends IReceiver{
    
	/** 
	* @Title: addReciveObject 
	* @Description:定义注册接口，基于接口实现 
	* @param @return  设定文件 
	* @return boolean 返回类型 
	* @throws 
	*/
	public  boolean  addReciveObject(IWebscoketReceiver webscoketReceiver);
	
}
