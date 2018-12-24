package cy.its.common.duplex.rabbitMq;

import com.alibaba.fastjson.JSONObject;

/**
* @Title: IWebscoketReceiver.java 
* @Package com.cy.rabbitMq 
* @Description:MQ调用接口类，传输数据
* @author lil@cychina.cn
* @date 2015年11月19日 下午2:52:50 
* @version V1.0   
 */
public interface IWebscoketReceiver {
	
	/** 
	* @Title: receive 
	* @Description:定义传输接口
	* @param @param jsonStr
	* @param @return    设定文件 
	* @return Boolean    返回类型 
	* @throws 
	*/
	public Boolean receive(JSONObject  jsonObject);
	
}
