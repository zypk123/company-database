package ah.its.wrokflow.action.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ah.its.workFlow.util.RedisPoolUtil;
import ah.its.wrokflow.action.DictionaryActionI;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
* @Title: DictionaryActionImpl.java 
* @Package ah.its.wrokflow.action.impl
* @Description: 数据字典用法
* @author lil@cychina.cn
* @date 2016年4月12日 上午10:16:03 
* @version V1.0   
 */
@RestController
@RequestMapping("/workFlow/dictionaryService")
public class DictionaryActionImpl implements DictionaryActionI {

	@Override
	@RequestMapping(value = "/getAllDictionaryByKeys")
	public JSONObject getAllDictionaryByKeys(@RequestParam("keys") String keys) {
		JSONObject   jobj  = new JSONObject();
		if(StringUtils.isNotEmpty(keys)){
			String  tmp[]  = keys.split(",");
			for(String key:tmp){
				jobj.put(key, getDictionaryData(key));
			}
			return jobj;
		}else{
			jobj.put("info", "请出传入key值");
			return jobj;
		}
	}

	/** 
	* @Description: 根据KEYh值获取缓存中的数据
	* @param @param key
	* @param @return    设定文件 
	* @return Object    返回类型 
	* @throws 
	*/
	private JSONArray getDictionaryData(String key) {
		String array = RedisPoolUtil.get(key);
		return JSONArray.parseArray(array);
	}

	@Override
	@RequestMapping(value = "/getOrgChildren")
	public JSONArray getOrgChildren(@RequestParam("curOrgId") String curOrgId) {
		JSONObject retrunObj = new JSONObject();
		//获取所有的部门信息
		JSONArray array = getDictionaryData("orgGrid");
		//不做递归，异步加载
		JSONArray arrays =getChildrenArray(curOrgId,array);
		return arrays;
	}
	
	@Override
	@RequestMapping(value = "/getOrgs")
	public JSONArray getOrgs(@RequestParam("curOrgId") String curOrgId) {
		JSONObject retrunObj = new JSONObject();
		//获取所有的部门信息
		JSONArray array = getDictionaryData("orgGrid");
		//过滤LIST，获取本部门以及以下所有的 部门
		for(int i=0;i<array.size();i++){
			JSONObject obj = array.getJSONObject(i);
			if(curOrgId.equals(obj.getString("value"))){
				retrunObj = obj;
				break;
			}
		}
		//不做递归，异步加载
		JSONArray arrays =getChildrenArray(retrunObj.getString("value"),array);
		if(arrays.size()>0){
			retrunObj.put("isLeaf",true);
			retrunObj.put("children",arrays);
		}
		array.clear();
		array.add(retrunObj);
		return array;
	}
	
	@Override
	@RequestMapping(value = "/getGroupTrees")
	public JSONArray getGroupTrees(@RequestParam(value="groupId",required=false) String groupId) {
		JSONArray newArray = new JSONArray();
		//groupId 去掉最后的000
		groupId = groupId.replaceAll("0*$","");
		//获取所有的部门信息
		JSONArray array = getDictionaryData("orgGrid");
		//过滤LIST，获取本部门以及以下所有的 部门
		for(int i=0;i<array.size();i++){
			//isParent = true
			JSONObject obj = array.getJSONObject(i);
			String   id = obj.getString("value");
			if(id.startsWith(groupId)){
				if(obj.get("parentCode")==null){
					obj.put("pId",0);
				}else{
					obj.put("pId",obj.get("parentCode"));
				}
				obj.put("id",obj.get("value"));
				if(groupId.equals(obj.get("value"))){
					obj.put("open",true);
				}
				newArray.add(obj);
			}
		}
		return newArray;
	}
	
	@Override
	@RequestMapping(value = "/getMenus")
	public JSONArray getMenus(@RequestParam(value="menuId",required=false) String menuId) {
		JSONArray newArray = new JSONArray();
		//获取所有的部门信息
		JSONArray array = getDictionaryData("menuIds");
		//过滤LIST，获取本部门以及以下所有的 部门
		for(int i=0;i<array.size();i++){
			JSONObject obj = array.getJSONObject(i);
			if(obj.get("parentCode")==null){
				obj.put("pId",0);
			}else{
				obj.put("pId",obj.get("parentCode"));
			}
			obj.put("id",obj.get("value"));
			newArray.add(obj);
		}
		return newArray;
	}
	
	private JSONArray getChildrenArray(String parentCode,JSONArray tmparray) {
		JSONArray array = new JSONArray();
		for(int i=0;i<tmparray.size();i++){
			JSONObject obj = tmparray.getJSONObject(i);
			if(parentCode.equals(obj.getString("parentCode"))){
				if(isLeaf(obj.getString("value"),tmparray)){
					obj.put("isLeaf",true);
					obj.put("collapsed",true);
				}else{
					obj.put("isLeaf",false);
				}
				array.add(obj);
			}
		}
		return array;
	}
	
	private boolean isLeaf(String parentCode,JSONArray tmparray) {
		boolean flag=false;
		for(int i=0;i<tmparray.size();i++){
			JSONObject obj = tmparray.getJSONObject(i);
			if(parentCode.equals(obj.getString("parentCode"))){
				flag=true;
				break;
			}
		}
		return flag;
	}

	@Override
	@RequestMapping(value = "/getCascadeData")
	public JSONObject getCascadeData(@RequestParam("key") String key,@RequestParam("parentCode") String parentCode) {
		JSONArray arrays = JSONArray.parseArray(RedisPoolUtil.get(key));
		JSONArray newArrays= new JSONArray();
		for(Object obj : arrays){
			String tmp  = (String) ((JSONObject)obj).get("parentCode");
			if(StringUtils.isNotEmpty(tmp)){
				if(tmp.contains(parentCode)){
					newArrays.add(obj);
				}
			}
		}
		JSONObject json = new JSONObject();
		json.put(key, newArrays);
		return json;
	}

}
