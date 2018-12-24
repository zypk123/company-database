package ah.its.wrokflow.action;

import java.util.Map;

import ah.its.wrokflow.repository.dao.CommonBaseObj;

/**
* @Title: CommonBaseActionI.java 
* @Package ah.its.wrokflow.action 
* @Description:通用取数类
* @author lil@cychina.cn
* @date 2016年5月28日 下午10:43:22 
* @version V1.0   
 */
public interface CommonBaseActionI {
	/** 
	* @Description: 通用取数方法
	* @param @param commonBaseObj
	* @param @return    设定文件 
	* @return Map    返回类型 
	* @throws 
	*/
	public Map selectAllData(Map<String, Object> map);
}
