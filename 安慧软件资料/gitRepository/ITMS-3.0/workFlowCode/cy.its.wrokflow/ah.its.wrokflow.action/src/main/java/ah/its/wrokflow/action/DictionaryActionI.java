package ah.its.wrokflow.action;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
* @Title: DictionaryActionI.java 
* @Package ah.its.wrokflow.action 
* @Description: 数据字典rest 访问类
* @author lil@cychina.cn
* @date 2016年4月8日 下午5:42:27 
* @version V1.0   
 */
public interface DictionaryActionI {
	/** 
	* @Description: 根据传入的KEYS获取所有字典数据	
	* @param @param keys 之间用,隔开
	* @param @return 设定文件 
	* @return Map 返回类型 
	* @throws 
	*/
	public JSONObject getAllDictionaryByKeys(String keys);
	
	
	
	/** 
	* @Description: 根据当前用户的部门信息获取其下属所有的机构信息
	* @param @param curOrgId
	* @param @return 设定文件 
	* @return JSONObject 返回类型 
	* @throws 
	*/
	public JSONArray getOrgs(String curOrgId);
	
	
	
	/** 
	* @Description: 级联操作
	* @param @param key 级联KEY 
	* @param @param parentCode 父类编码 针对大数据量数据
	* @param @return    设定文件 
	* @return JSONObject    返回类型 
	* @throws 
	*/
	public JSONObject getCascadeData(String key,String parentCode);



	/** 
	* @Description: 根据部门获取下属部门
	* @param @param curOrgId
	* @param @return    设定文件 
	* @return JSONArray    返回类型 
	* @throws 
	*/
	public JSONArray getOrgChildren(String curOrgId);



	/** 
	* @Description: 获取菜单，如果menuId为空则获取所有的
	* @param @param curOrgId
	* @param @return    设定文件 
	* @return JSONArray    返回类型 
	* @throws 
	*/
	public JSONArray getMenus(String menuId);



	/** 
	* @Description: 获取部门树信息
	* @param @param groupId
	* @param @return    设定文件 
	* @return JSONArray    返回类型 
	* @throws 
	*/
	public JSONArray getGroupTrees(String groupId);
	
}
