package ah.its.wrokflow.repository;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import ah.its.wrokflow.repository.dao.CommonBaseObj;

/**
* @Title: CommonBaseRepositoryI.java 
* @Package ah.its.wrokflow.repository 
* @Description: 通用获取数据方法
* @author lil@cychina.cn
* @date 2016年5月28日 下午10:29:02 
* @version V1.0   
 */
public interface CommonBaseRepositoryI {
	
	/**
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 * @throws SecurityException 
	 * @throws NoSuchMethodException  
	* @Description:利用反射调取 mapper中的方法，出入参数，并返回所需数据
	* @param @param map
	* @param @param method
	* @param @return    设定文件 
	* @return List<CommonBaseObj>    返回类型 
	* @throws 
	*/
	public List<CommonBaseObj> selectAllData(Map map,String method) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException;
	
}
