package ah.its.workFlow.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.util.StringUtils;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.alibaba.fastjson.JSONArray;

/**
* @Title: DictionaryUtil.java 
* @Package ah.its.workFlow.util 
* @Description: 数据字典初始化，配置文件为 
* @author lil@cychina.cn
* @date 2016年4月8日 上午9:26:06 
* @version V1.0   
 */
public class DictionaryUtil {
	
	
	private static Logger logger = Logger.getLogger(DictionaryUtil.class);
	
	private static DataSource  dataSource;
	
	private static String   path;
	
	/** 
	* @Description: 每一个ID 对应一个下拉列表文件信息
	* @param @param config    获取spring容器里的datasource
	* @return void    返回类型 
	* @throws 
	*/
	public static void initAll(ServletConfig config,String  pathinfo){
		 path = pathinfo;
		 ApplicationContext springBeanFactory = WebApplicationContextUtils.getWebApplicationContext(config.getServletContext());
		 dataSource = (DataSource) springBeanFactory.getBean("dataSource");
		 Map  map =  JacksonXmlUtil.convertXMLtoMap(pathinfo);//把XML转化为MAP
		 for (Object key : map.keySet()) {
			if(key != null){
				try{
					queryDataList(key,map.get(key));//单个查询处理
				}catch(Exception e){
					logger.error("数据字典："+key+"--出现异常！");
				}
			}
		 }
	}
	/** 
	* @Title: queryDataList 
	* @Description:  
	* @param @param object    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	private static void queryDataList(Object key,Object sql) {
         if(!StringUtils.isEmpty(sql)){ //sql不为空，执行查询
        	List list =  DBUtil.queryData(sql.toString(),dataSource);
        	if(list.size()>0){
        		RedisPoolUtil.put(key.toString(), JSONArray.toJSONString(list));
        	}
         }		
	}
	/* (non-Javadoc)
	 * @see com.cy.cache.service.DataDictionaryManagerI#updateMemoryCache(java.lang.String[])
	 * 手动更新缓存
	 */
	public void updateMemoryCache(String[] keys) {
		for(String  key  : keys){
			if(!StringUtils.isEmpty(key)){
				Map  map =  JacksonXmlUtil.convertXMLtoMap(path);//把XML转化为MAP
				String  sql = map.get(key)==null?"":map.get(key).toString();
				if(!StringUtils.isEmpty(sql)){
					RedisPoolUtil.del(key.toString());
					queryDataList(key,sql);
				}
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see com.cy.cache.service.DataDictionaryManagerI#queryMemoryCache(java.lang.String[])
	 * 根据传入的KEY 返回LIST 
	 */
	public Map queryMemoryCache(String[] keys) {
		// TODO Auto-generated method stub
		Map map  = new  HashMap();
		for(String  key  : keys){
			if(!StringUtils.isEmpty(key)){
				Object obj =  RedisPoolUtil.get(key.toString());
				if(obj!=null){
					List<CacheDao>  list  = JSONArray.parseArray(obj.toString(), CacheDao.class);
					map.put(key,list);
				}
			}
		}
		return map;
	}
}
