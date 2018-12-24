package cy.its.service.convertFlow.service;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cy.its.service.util.DBUtil;

/**
* @Title: CacheManager.java 
* @Package cy.its.service.section.module 
* @Description:缓存管理，单列模式
* @author lil@cychina.cn
* @date 2015年11月4日 下午6:46:17 
* @version V1.0   
 */
public class CacheManager {
	
	public  static long   cuurnKey = 0;
	//区间数据缓存
	private static   Map<String,String>  keys=null;
	
	
	private static String  sql = " select  a.site_code  siteCode,direction_type directionType,"
			+ "section_id  sectionId  from  t_sys_section  t    "
			+ "inner join  t_sys_site  a on a.site_id = t.site_id ";
	
	public  static  String  getSectionByKey(String key){
		getSectionMap();
		String  value   =  keys.get(key);
		if(value == null){
			value = "";
		}
		return value;
	}
	
	public  static  Map  getSectionMap(){
		if(keys == null){
			initSectionist();
		}
		return keys;
	}
	
	//重新刷新缓存
	public static void initSectionist(){
		synchronized(CacheManager.class){
			keys  = new  HashMap<String, String>();
			JSONArray Lists = DBUtil.queryJSONData(sql);
			for(Object  obj: Lists){
				JSONObject bean  = (JSONObject)obj;
				keys.put(bean.getString("SITECODE")+":"+bean.getString("DIRECTIONTYPE"),bean.getString("SECTIONID"));
			}
		}
	}
}
